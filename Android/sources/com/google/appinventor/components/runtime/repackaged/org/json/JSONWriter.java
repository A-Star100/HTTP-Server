package com.google.appinventor.components.runtime.repackaged.org.json;

import java.io.IOException;
import java.io.Writer;

public class JSONWriter {
    private static final int maxdepth = 200;
    private boolean comma = false;
    protected char mode = 'i';
    private final JSONObject[] stack = new JSONObject[200];
    private int top = 0;
    protected Writer writer;

    public JSONWriter(Writer w) {
        this.writer = w;
    }

    private JSONWriter append(String string) throws JSONException {
        if (string != null) {
            char c = this.mode;
            if (c == 'o' || c == 'a') {
                try {
                    if (this.comma && c == 'a') {
                        this.writer.write(44);
                    }
                    this.writer.write(string);
                    if (this.mode == 'o') {
                        this.mode = 'k';
                    }
                    this.comma = true;
                    return this;
                } catch (IOException e) {
                    throw new JSONException((Throwable) e);
                }
            } else {
                throw new JSONException("Value out of sequence.");
            }
        } else {
            throw new JSONException("Null pointer");
        }
    }

    public JSONWriter array() throws JSONException {
        char c = this.mode;
        if (c == 'i' || c == 'o' || c == 'a') {
            push((JSONObject) null);
            append("[");
            this.comma = false;
            return this;
        }
        throw new JSONException("Misplaced array.");
    }

    private JSONWriter end(char mode2, char c) throws JSONException {
        if (this.mode != mode2) {
            throw new JSONException(mode2 == 'a' ? "Misplaced endArray." : "Misplaced endObject.");
        }
        pop(mode2);
        try {
            this.writer.write(c);
            this.comma = true;
            return this;
        } catch (IOException e) {
            throw new JSONException((Throwable) e);
        }
    }

    public JSONWriter endArray() throws JSONException {
        return end('a', ']');
    }

    public JSONWriter endObject() throws JSONException {
        return end('k', '}');
    }

    public JSONWriter key(String string) throws JSONException {
        if (string == null) {
            throw new JSONException("Null key.");
        } else if (this.mode == 'k') {
            try {
                this.stack[this.top - 1].putOnce(string, Boolean.TRUE);
                if (this.comma) {
                    this.writer.write(44);
                }
                this.writer.write(JSONObject.quote(string));
                this.writer.write(58);
                this.comma = false;
                this.mode = 'o';
                return this;
            } catch (IOException e) {
                throw new JSONException((Throwable) e);
            }
        } else {
            throw new JSONException("Misplaced key.");
        }
    }

    public JSONWriter object() throws JSONException {
        if (this.mode == 'i') {
            this.mode = 'o';
        }
        char c = this.mode;
        if (c == 'o' || c == 'a') {
            append("{");
            push(new JSONObject());
            this.comma = false;
            return this;
        }
        throw new JSONException("Misplaced object.");
    }

    private void pop(char c) throws JSONException {
        int i = this.top;
        if (i > 0) {
            JSONObject[] jSONObjectArr = this.stack;
            char c2 = 'a';
            if ((jSONObjectArr[i + -1] == null ? 'a' : 'k') == c) {
                int i2 = i - 1;
                this.top = i2;
                if (i2 == 0) {
                    c2 = 'd';
                } else if (jSONObjectArr[i2 - 1] != null) {
                    c2 = 'k';
                }
                this.mode = c2;
                return;
            }
            throw new JSONException("Nesting error.");
        }
        throw new JSONException("Nesting error.");
    }

    private void push(JSONObject jo) throws JSONException {
        int i = this.top;
        if (i < 200) {
            this.stack[i] = jo;
            this.mode = jo == null ? 'a' : 'k';
            this.top = i + 1;
            return;
        }
        throw new JSONException("Nesting too deep.");
    }

    public JSONWriter value(boolean b) throws JSONException {
        return append(b ? "true" : "false");
    }

    public JSONWriter value(double d) throws JSONException {
        return value((Object) new Double(d));
    }

    public JSONWriter value(long l) throws JSONException {
        return append(Long.toString(l));
    }

    public JSONWriter value(Object object) throws JSONException {
        return append(JSONObject.valueToString(object));
    }
}
