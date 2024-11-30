package com.google.appinventor.components.runtime.repackaged.org.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

public class JSONTokener {
    private long character;
    private boolean eof;
    private long index;
    private long line;
    private char previous;
    private Reader reader;
    private boolean usePrevious;

    public JSONTokener(Reader reader2) {
        this.reader = reader2.markSupported() ? reader2 : new BufferedReader(reader2);
        this.eof = false;
        this.usePrevious = false;
        this.previous = 0;
        this.index = 0;
        this.character = 1;
        this.line = 1;
    }

    public JSONTokener(InputStream inputStream) throws JSONException {
        this((Reader) new InputStreamReader(inputStream));
    }

    public JSONTokener(String s) {
        this((Reader) new StringReader(s));
    }

    public void back() throws JSONException {
        if (!this.usePrevious) {
            long j = this.index;
            if (j > 0) {
                this.index = j - 1;
                this.character--;
                this.usePrevious = true;
                this.eof = false;
                return;
            }
        }
        throw new JSONException("Stepping back two steps is not supported");
    }

    public static int dehexchar(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return c - '7';
        }
        if (c < 'a' || c > 'f') {
            return -1;
        }
        return c - 'W';
    }

    public boolean end() {
        return this.eof && !this.usePrevious;
    }

    public boolean more() throws JSONException {
        next();
        if (end()) {
            return false;
        }
        back();
        return true;
    }

    public char next() throws JSONException {
        int c;
        if (this.usePrevious) {
            this.usePrevious = false;
            c = this.previous;
        } else {
            try {
                c = this.reader.read();
                if (c <= 0) {
                    this.eof = true;
                    c = 0;
                }
            } catch (IOException exception) {
                throw new JSONException((Throwable) exception);
            }
        }
        long j = 1;
        this.index++;
        if (this.previous == 13) {
            this.line++;
            if (c == 10) {
                j = 0;
            }
            this.character = j;
        } else if (c == 10) {
            this.line++;
            this.character = 0;
        } else {
            this.character++;
        }
        char c2 = (char) c;
        this.previous = c2;
        return c2;
    }

    public char next(char c) throws JSONException {
        char n = next();
        if (n == c) {
            return n;
        }
        throw syntaxError(new StringBuffer().append("Expected '").append(c).append("' and instead saw '").append(n).append("'").toString());
    }

    public String next(int n) throws JSONException {
        if (n == 0) {
            return "";
        }
        char[] chars = new char[n];
        int pos = 0;
        while (pos < n) {
            chars[pos] = next();
            if (!end()) {
                pos++;
            } else {
                throw syntaxError("Substring bounds error");
            }
        }
        return new String(chars);
    }

    public char nextClean() throws JSONException {
        char c;
        do {
            c = next();
            if (c == 0 || c > ' ') {
                return c;
            }
            c = next();
            break;
        } while (c > ' ');
        return c;
    }

    public String nextString(char quote) throws JSONException {
        StringBuffer sb = new StringBuffer();
        while (true) {
            char c = next();
            switch (c) {
                case 0:
                case 10:
                case 13:
                    throw syntaxError("Unterminated string");
                case '\\':
                    char c2 = next();
                    switch (c2) {
                        case '\"':
                        case '\'':
                        case '/':
                        case '\\':
                            sb.append(c2);
                            break;
                        case 'b':
                            sb.append(8);
                            break;
                        case 'f':
                            sb.append(12);
                            break;
                        case 'n':
                            sb.append(10);
                            break;
                        case 'r':
                            sb.append(13);
                            break;
                        case 't':
                            sb.append(9);
                            break;
                        case 'u':
                            sb.append((char) Integer.parseInt(next(4), 16));
                            break;
                        default:
                            throw syntaxError("Illegal escape.");
                    }
                default:
                    if (c != quote) {
                        sb.append(c);
                        break;
                    } else {
                        return sb.toString();
                    }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String nextTo(char r4) throws com.google.appinventor.components.runtime.repackaged.org.json.JSONException {
        /*
            r3 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
        L_0x0005:
            char r1 = r3.next()
            if (r1 == r4) goto L_0x001a
            if (r1 == 0) goto L_0x001a
            r2 = 10
            if (r1 == r2) goto L_0x001a
            r2 = 13
            if (r1 != r2) goto L_0x0016
            goto L_0x001a
        L_0x0016:
            r0.append(r1)
            goto L_0x0005
        L_0x001a:
            if (r1 == 0) goto L_0x001f
            r3.back()
        L_0x001f:
            java.lang.String r2 = r0.toString()
            java.lang.String r2 = r2.trim()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.repackaged.org.json.JSONTokener.nextTo(char):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0020  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String nextTo(java.lang.String r4) throws com.google.appinventor.components.runtime.repackaged.org.json.JSONException {
        /*
            r3 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
        L_0x0005:
            char r1 = r3.next()
            int r2 = r4.indexOf(r1)
            if (r2 >= 0) goto L_0x001e
            if (r1 == 0) goto L_0x001e
            r2 = 10
            if (r1 == r2) goto L_0x001e
            r2 = 13
            if (r1 != r2) goto L_0x001a
            goto L_0x001e
        L_0x001a:
            r0.append(r1)
            goto L_0x0005
        L_0x001e:
            if (r1 == 0) goto L_0x0023
            r3.back()
        L_0x0023:
            java.lang.String r2 = r0.toString()
            java.lang.String r2 = r2.trim()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.repackaged.org.json.JSONTokener.nextTo(java.lang.String):java.lang.String");
    }

    public Object nextValue() throws JSONException {
        char c = nextClean();
        switch (c) {
            case '\"':
            case '\'':
                return nextString(c);
            case '[':
                back();
                return new JSONArray(this);
            case '{':
                back();
                return new JSONObject(this);
            default:
                StringBuffer sb = new StringBuffer();
                while (c >= ' ' && ",:]}/\\\"[{;=#".indexOf(c) < 0) {
                    sb.append(c);
                    c = next();
                }
                back();
                String string = sb.toString().trim();
                if (!"".equals(string)) {
                    return JSONObject.stringToValue(string);
                }
                throw syntaxError("Missing value");
        }
    }

    public char skipTo(char to) throws JSONException {
        char c;
        try {
            long startIndex = this.index;
            long startCharacter = this.character;
            long startLine = this.line;
            this.reader.mark(1000000);
            do {
                c = next();
                if (c == 0) {
                    try {
                        this.reader.reset();
                        this.index = startIndex;
                        this.character = startCharacter;
                        this.line = startLine;
                        return c;
                    } catch (IOException e) {
                        exc = e;
                        throw new JSONException((Throwable) exc);
                    }
                }
            } while (c != to);
            back();
            return c;
        } catch (IOException e2) {
            exc = e2;
            throw new JSONException((Throwable) exc);
        }
    }

    public JSONException syntaxError(String message) {
        return new JSONException(new StringBuffer().append(message).append(toString()).toString());
    }

    public String toString() {
        return new StringBuffer().append(" at ").append(this.index).append(" [character ").append(this.character).append(" line ").append(this.line).append("]").toString();
    }
}
