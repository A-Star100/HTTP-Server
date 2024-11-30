#include <microhttpd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include <sys/stat.h>
#include <unistd.h>

#define PAGE_NOT_FOUND "<html><body><h1>404 Not Found</h1></body></html>"
#define PAGE_ERROR "<html><body><h1>500 Internal Server Error</h1></body></html>"

// Global variables for port and directory
char *base_directory = NULL;

// Function to expand the tilde (~) to the user's home directory
char* expand_tilde(const char *path) {
    if (path[0] == '~') {
        const char *home = getenv("HOME");
        if (home) {
            size_t len = strlen(home) + strlen(path);
            char *expanded_path = malloc(len);
            snprintf(expanded_path, len, "%s%s", home, path + 1);
            return expanded_path;
        }
    }
    return strdup(path);
}

// Get the MIME type based on file extension
const char *get_mime_type(const char *path) {
    const char *ext = strrchr(path, '.');
    if (!ext) return "application/octet-stream";  // Default MIME type for unknown file types

    // Check for common web formats
    if (strcmp(ext, ".html") == 0) return "text/html";
    if (strcmp(ext, ".css") == 0) return "text/css";
    if (strcmp(ext, ".js") == 0) return "application/javascript";
    if (strcmp(ext, ".jpg") == 0 || strcmp(ext, ".jpeg") == 0) return "image/jpeg";
    if (strcmp(ext, ".png") == 0) return "image/png";
    if (strcmp(ext, ".gif") == 0) return "image/gif";
    if (strcmp(ext, ".txt") == 0) return "text/plain";

    // Video formats
    if (strcmp(ext, ".mp4") == 0) return "video/mp4";
    if (strcmp(ext, ".webm") == 0) return "video/webm";
    if (strcmp(ext, ".avi") == 0) return "video/x-msvideo";
    if (strcmp(ext, ".mov") == 0) return "video/quicktime";
    if (strcmp(ext, ".mkv") == 0) return "video/x-matroska";
    if (strcmp(ext, ".flv") == 0) return "video/x-flv";
    if (strcmp(ext, ".ogv") == 0) return "video/ogg";

    // Audio formats
    if (strcmp(ext, ".mp3") == 0) return "audio/mpeg";
    if (strcmp(ext, ".ogg") == 0) return "audio/ogg";
    if (strcmp(ext, ".wav") == 0) return "audio/wav";
    if (strcmp(ext, ".aac") == 0) return "audio/aac";
    if (strcmp(ext, ".m4a") == 0) return "audio/mp4";
    if (strcmp(ext, ".flac") == 0) return "audio/flac";
    if (strcmp(ext, ".opus") == 0) return "audio/opus";

    // MPEG-DASH formats
    if (strcmp(ext, ".mpd") == 0) return "application/dash+xml";

    // HLS formats
    if (strcmp(ext, ".m3u8") == 0) return "application/vnd.apple.mpegurl";  // HLS Playlist file
    if (strcmp(ext, ".ts") == 0) return "video/MP2T";  // HLS video segment

    // Default MIME type for unsupported file extensions
    return "application/octet-stream";
}

// Serve a directory listing as HTML
char *generate_directory_listing(const char *path) {
    DIR *dir = opendir(path);
    if (!dir) return NULL;

    char *html = malloc(8192);
    strcpy(html, "<html><body><h1>Directory Listing</h1><ul>");

    struct dirent *entry;
    while ((entry = readdir(dir)) != NULL) {
        // Skip "." and ".."
        if (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0) continue;

        char entry_path[1024];
        snprintf(entry_path, sizeof(entry_path), "%s/%s", path, entry->d_name);
        struct stat statbuf;
        stat(entry_path, &statbuf);

        // Add links to directories and files
        if (S_ISDIR(statbuf.st_mode))
            snprintf(html + strlen(html), 8192 - strlen(html), "<li><a href=\"/%s/\">%s/</a></li>", entry->d_name, entry->d_name);
        else
            snprintf(html + strlen(html), 8192 - strlen(html), "<li><a href=\"/%s\">%s</a></li>", entry->d_name, entry->d_name);
    }

    closedir(dir);
    strcat(html, "</ul></body></html>");
    return html;
}

// Handle HTTP requests
static enum MHD_Result handle_request(void *cls, struct MHD_Connection *connection,
                                      const char *url, const char *method, const char *version,
                                      const char *upload_data, size_t *upload_data_size, void **con_cls) {
    char file_path[1024];
    snprintf(file_path, sizeof(file_path), "%s%s", base_directory, url);

    struct stat path_stat;
    if (stat(file_path, &path_stat) != 0) {
        // File or directory not found
        struct MHD_Response *response = MHD_create_response_from_buffer(strlen(PAGE_NOT_FOUND), (void *)PAGE_NOT_FOUND, MHD_RESPMEM_PERSISTENT);
        MHD_add_response_header(response, "Content-Type", "text/html");
        int ret = MHD_queue_response(connection, MHD_HTTP_NOT_FOUND, response);
        MHD_destroy_response(response);
        return ret;
    }

    if (S_ISDIR(path_stat.st_mode)) {
        // Serve directory listing
        char *html = generate_directory_listing(file_path);
        if (!html) {
            struct MHD_Response *response = MHD_create_response_from_buffer(strlen(PAGE_ERROR), (void *)PAGE_ERROR, MHD_RESPMEM_PERSISTENT);
            int ret = MHD_queue_response(connection, MHD_HTTP_INTERNAL_SERVER_ERROR, response);
            MHD_destroy_response(response);
            return ret;
        }
        struct MHD_Response *response = MHD_create_response_from_buffer(strlen(html), html, MHD_RESPMEM_MUST_FREE);
        MHD_add_response_header(response, "Content-Type", "text/html");
        int ret = MHD_queue_response(connection, MHD_HTTP_OK, response);
        MHD_destroy_response(response);
        return ret;
    } else {
        // Serve file
        FILE *file = fopen(file_path, "rb");
        if (!file) {
            struct MHD_Response *response = MHD_create_response_from_buffer(strlen(PAGE_ERROR), (void *)PAGE_ERROR, MHD_RESPMEM_PERSISTENT);
            int ret = MHD_queue_response(connection, MHD_HTTP_INTERNAL_SERVER_ERROR, response);
            MHD_destroy_response(response);
            return ret;
        }

        fseek(file, 0, SEEK_END);
        long file_size = ftell(file);
        rewind(file);

        char *file_data = malloc(file_size);
        fread(file_data, 1, file_size, file);
        fclose(file);

        struct MHD_Response *response = MHD_create_response_from_buffer(file_size, file_data, MHD_RESPMEM_MUST_FREE);
        MHD_add_response_header(response, "Content-Type", get_mime_type(file_path));
        int ret = MHD_queue_response(connection, MHD_HTTP_OK, response);
        MHD_destroy_response(response);
        return ret;
    }
}

int main() {
    int port;
    char dir[1024];

    // Ask for the port
    printf("Enter the port to serve on (e.g., 8080): ");
    scanf("%d", &port);

    // Ask for the directory
    printf("Enter the directory to serve (e.g., ~/Documents): ");
    scanf("%s", dir);

    // Expand the tilde (~) to the home directory
    char *expanded_dir = expand_tilde(dir);

    // Check if directory exists
    if (access(expanded_dir, F_OK) != 0) {
        printf("Directory does not exist: %s\n", expanded_dir);
        return 1;
    }

    base_directory = expanded_dir;

    // Start the server
    struct MHD_Daemon *daemon = MHD_start_daemon(MHD_USE_INTERNAL_POLLING_THREAD, port, NULL, NULL, &handle_request, NULL, MHD_OPTION_END);
    if (!daemon) {
        printf("Failed to start server\n");
        return 1;
    }

    printf("Serving %s on http://localhost:%d\n", base_directory, port);
    printf("Press Ctrl+C to stop the server.\n");

    // Keep the server running
    getchar();
    getchar();

    MHD_stop_daemon(daemon);
    free(base_directory);

    return 0;
}
