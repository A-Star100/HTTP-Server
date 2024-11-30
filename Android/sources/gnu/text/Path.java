package gnu.text;

import gnu.lists.FString;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class Path {
    public static Path defaultPath;
    private static ThreadLocal<Path> pathLocation = new ThreadLocal<>();
    public static final FilePath userDirPath;

    public abstract long getLastModified();

    public abstract String getPath();

    public abstract String getScheme();

    public abstract boolean isAbsolute();

    public abstract InputStream openInputStream() throws IOException;

    public abstract OutputStream openOutputStream() throws IOException;

    public abstract Path resolve(String str);

    public abstract URL toURL();

    public abstract URI toUri();

    static {
        FilePath valueOf = FilePath.valueOf(new File("."));
        userDirPath = valueOf;
        defaultPath = valueOf;
    }

    protected Path() {
    }

    public static Path currentPath() {
        Path path = pathLocation.get();
        if (path != null) {
            return path;
        }
        return defaultPath;
    }

    public static void setCurrentPath(Path path) {
        pathLocation.set(path);
    }

    public static Path coerceToPathOrNull(Object path) {
        String str;
        if (path instanceof Path) {
            return (Path) path;
        }
        if (path instanceof URL) {
            return URLPath.valueOf((URL) path);
        }
        if (path instanceof URI) {
            return URIPath.valueOf((URI) path);
        }
        if (path instanceof File) {
            return FilePath.valueOf((File) path);
        }
        if (path instanceof FString) {
            str = path.toString();
        } else if (!(path instanceof String)) {
            return null;
        } else {
            str = (String) path;
        }
        if (uriSchemeSpecified(str)) {
            return URIPath.valueOf(str);
        }
        return FilePath.valueOf(str);
    }

    public static Path valueOf(Object arg) {
        Path path = coerceToPathOrNull(arg);
        if (path != null) {
            return path;
        }
        String str = null;
        throw new WrongType((String) null, -4, arg, "path");
    }

    public static URL toURL(String str) {
        try {
            if (!uriSchemeSpecified(str)) {
                Path path = currentPath().resolve(str);
                if (path.isAbsolute()) {
                    return path.toURL();
                }
                str = path.toString();
            }
            return new URL(str);
        } catch (Throwable ex) {
            throw WrappedException.wrapIfNeeded(ex);
        }
    }

    public static int uriSchemeLength(String uri) {
        int len = uri.length();
        int i = 0;
        while (i < len) {
            char ch = uri.charAt(i);
            if (ch == ':') {
                return i;
            }
            if (i != 0) {
                if (!(Character.isLetterOrDigit(ch) || ch == '+' || ch == '-' || ch == '.')) {
                }
                i++;
            } else if (Character.isLetter(ch)) {
                i++;
            }
            return -1;
        }
        return -1;
    }

    public static boolean uriSchemeSpecified(String name) {
        int ulen = uriSchemeLength(name);
        if (ulen == 1 && File.separatorChar == '\\') {
            char drive = name.charAt(0);
            if (drive >= 'a' && drive <= 'z') {
                return false;
            }
            if (drive < 'A' || drive > 'Z') {
                return true;
            }
            return false;
        } else if (ulen > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isDirectory() {
        String str = toString();
        int len = str.length();
        if (len <= 0) {
            return false;
        }
        char last = str.charAt(len - 1);
        if (last == '/' || last == File.separatorChar) {
            return true;
        }
        return false;
    }

    public boolean delete() {
        return false;
    }

    public boolean exists() {
        return getLastModified() != 0;
    }

    public long getContentLength() {
        return -1;
    }

    public String getAuthority() {
        return null;
    }

    public String getUserInfo() {
        return null;
    }

    public String getHost() {
        return null;
    }

    public Path getDirectory() {
        if (isDirectory()) {
            return this;
        }
        return resolve("");
    }

    public Path getParent() {
        return resolve(isDirectory() ? ".." : "");
    }

    public String getLast() {
        String p = getPath();
        if (p == null) {
            return null;
        }
        int len = p.length();
        int end = len;
        int i = len;
        while (true) {
            i--;
            if (i <= 0) {
                return "";
            }
            char c = p.charAt(i);
            if (c == '/' || ((this instanceof FilePath) && c == File.separatorChar)) {
                if (i + 1 != len) {
                    return p.substring(i + 1, end);
                }
                end = i;
            }
        }
    }

    public String getExtension() {
        boolean sawDot;
        String p = getPath();
        if (p == null) {
            return null;
        }
        int i = p.length();
        do {
            i--;
            if (i <= 0) {
                return null;
            }
            char c = p.charAt(i);
            sawDot = false;
            if (c == '.') {
                c = p.charAt(i - 1);
                sawDot = true;
            }
            if (c == '/' || ((this instanceof FilePath) && c == File.separatorChar)) {
                return null;
            }
        } while (!sawDot);
        return p.substring(i + 1);
    }

    public int getPort() {
        return -1;
    }

    public String getQuery() {
        return null;
    }

    public String getFragment() {
        return null;
    }

    public final URI toURI() {
        return toUri();
    }

    public String toURIString() {
        return toUri().toString();
    }

    public Path resolve(Path relative) {
        if (relative.isAbsolute()) {
            return relative;
        }
        return resolve(relative.toString());
    }

    public static InputStream openInputStream(Object uri) throws IOException {
        return valueOf(uri).openInputStream();
    }

    public Reader openReader(boolean ignoreEncodingErrors) throws IOException {
        throw new UnsupportedOperationException();
    }

    public Writer openWriter() throws IOException {
        return new OutputStreamWriter(openOutputStream());
    }

    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        throw new UnsupportedOperationException();
    }

    public static String relativize(String in, String base) throws URISyntaxException, IOException {
        char cb;
        String str = in;
        String baseStr = new URI(base).normalize().toString();
        String inStr = URLPath.valueOf(in).toURI().normalize().toString();
        int baseLen = baseStr.length();
        int inLen = inStr.length();
        int i = 0;
        int sl = 0;
        int colon = 0;
        while (i < baseLen && i < inLen && (cb = baseStr.charAt(i)) == inStr.charAt(i)) {
            if (cb == '/') {
                sl = i;
            }
            if (cb == ':') {
                colon = i;
            }
            i++;
        }
        if (colon <= 0 || (sl <= colon + 2 && baseLen > colon + 2 && baseStr.charAt(colon + 2) == '/')) {
            return in;
        }
        String baseStr2 = baseStr.substring(sl + 1);
        String inStr2 = inStr.substring(sl + 1);
        StringBuilder sbuf = new StringBuilder();
        int length = baseStr2.length();
        int baseLen2 = length;
        int i2 = length;
        while (true) {
            i2--;
            if (i2 < 0) {
                sbuf.append(inStr2);
                return sbuf.toString();
            } else if (baseStr2.charAt(i2) == '/') {
                sbuf.append("../");
            }
        }
    }

    public String getName() {
        return toString();
    }

    public Path getAbsolute() {
        if (this == userDirPath) {
            return resolve("");
        }
        return currentPath().resolve(this);
    }

    public Path getCanonical() {
        return getAbsolute();
    }
}
