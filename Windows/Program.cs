using System;
using System.IO;
using System.Net;
using System.Text;
using System.Web;

namespace FileServerApp
{
    class Program
    {
        static void Main(string[] args)
        {
            // Ask the user for the port number
            Console.WriteLine("Enter the port number you want to use (e.g., 8080): ");
            string portInput = Console.ReadLine();
            int port;

            // Validate the port number
            if (!int.TryParse(portInput, out port) || port <= 0 || port > 65535)
            {
                Console.WriteLine("Invalid port number. Using default port 8080.");
                port = 8080; // default port if invalid input
            }

            // Ask the user for the directory path to serve
            Console.WriteLine("Enter the full path of the directory to serve (e.g., C:\\Users\\YourUsername\\Documents): ");
            string directoryToServe = Console.ReadLine();

            // Ensure the directory exists
            if (!Directory.Exists(directoryToServe))
            {
                Console.WriteLine($"Directory does not exist: {directoryToServe}");
                return; // Exit if the directory doesn't exist
            }

            // Set up HttpListener to serve files over HTTP
            HttpListener listener = new HttpListener();
            listener.Prefixes.Add($"http://localhost:{port}/"); // Use the user-specified port
            listener.Start();
            Console.WriteLine($"Listening for requests on http://localhost:{port}/" +
                $"Press Ctrl+C to exit");

            // Handle incoming requests
            while (true)
            {
                // Wait for an incoming request
                HttpListenerContext context = listener.GetContext();
                HttpListenerRequest request = context.Request;
                HttpListenerResponse response = context.Response;

                // Decode the URL to handle special characters like parentheses
                string relativePath = HttpUtility.UrlDecode(request.Url.AbsolutePath.Substring(1)); // Remove the leading '/'
                string fullPath = Path.Combine(directoryToServe, relativePath);

                // If the requested path is empty (root directory), show directory listing
                if (string.IsNullOrEmpty(relativePath))
                {
                    // Create and serve an HTML directory listing
                    ServeDirectoryListing(directoryToServe, response);
                }
                else
                {
                    // If the file exists, serve it
                    if (File.Exists(fullPath))
                    {
                        try
                        {
                            byte[] fileBytes = File.ReadAllBytes(fullPath);
                            response.ContentType = GetContentType(Path.GetExtension(fullPath));
                            response.ContentLength64 = fileBytes.Length;
                            response.OutputStream.Write(fileBytes, 0, fileBytes.Length);
                        }
                        catch (Exception ex)
                        {
                            Console.WriteLine($"Error serving file: {ex.Message}");
                            response.StatusCode = 500; // Internal server error
                        }
                    }
                    else
                    {
                        // File not found, return 404
                        Console.WriteLine("File not found.");
                        response.StatusCode = 404;
                        byte[] errorMessage = Encoding.UTF8.GetBytes("File not found.");
                        response.ContentLength64 = errorMessage.Length;
                        response.OutputStream.Write(errorMessage, 0, errorMessage.Length);
                    }
                }

                // Close the response
                response.OutputStream.Close();
            }
        }

        // Helper function to generate and serve the directory listing as HTML
        static void ServeDirectoryListing(string directoryPath, HttpListenerResponse response)
        {
            StringBuilder htmlContent = new StringBuilder();
            htmlContent.AppendLine("<html><body><h1>Directory Listing</h1><ul>");

            try
            {
                // Get all files and directories in the specified directory
                string[] directories = Directory.GetDirectories(directoryPath);
                string[] files = Directory.GetFiles(directoryPath);

                // Add directories to the listing
                foreach (string dir in directories)
                {
                    string dirName = Path.GetFileName(dir);
                    htmlContent.AppendLine($"<li><a href=\"/{HttpUtility.UrlEncode(dirName)}\">{dirName}/</a></li>");
                }

                // Add files to the listing
                foreach (string file in files)
                {
                    string fileName = Path.GetFileName(file);
                    htmlContent.AppendLine($"<li><a href=\"/{HttpUtility.UrlEncode(fileName)}\">{fileName}</a></li>");
                }
            }
            catch (Exception ex)
            {
                htmlContent.AppendLine($"<li>Error listing directory: {ex.Message}</li>");
            }

            htmlContent.AppendLine("</ul></body></html>");

            byte[] responseBytes = Encoding.UTF8.GetBytes(htmlContent.ToString());
            response.ContentType = "text/html";
            response.ContentLength64 = responseBytes.Length;
            response.OutputStream.Write(responseBytes, 0, responseBytes.Length);
        }

        // Helper function to determine the content type of the file based on its extension
        static string GetContentType(string extension)
        {
            switch (extension.ToLower())
            {
                case ".html":
                    return "text/html";
                case ".css":
                    return "text/css";
                case ".js":
                    return "application/javascript";
                case ".jpg":
                case ".jpeg":
                    return "image/jpeg";
                case ".png":
                    return "image/png";
                case ".gif":
                    return "image/gif";
                case ".txt":
                    return "text/plain";
                case ".json":
                    return "application/json";
                case ".mp3":
                    return "audio/mpeg";
                case ".ogg":
                    return "audio/ogg";
                case ".wav":
                    return "audio/wav";
                case ".mp4":
                    return "video/mp4";
                case ".webm":
                    return "video/webm";
                case ".avi":
                    return "video/x-msvideo";
                case ".mov":
                    return "video/quicktime";
                case ".mpd":
                    return "application/dash+xml";
                case ".m3u8": // HLS playlist file
                    return "application/vnd.apple.mpegurl";
                case ".ts": // HLS video segment
                    return "video/MP2T";
                default:
                    return "application/octet-stream"; // Default content type
            }
        }
    }
}
