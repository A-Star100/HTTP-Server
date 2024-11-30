package com.google.appinventor.components.runtime.repackaged.org.json;

import java.util.HashMap;

public class XMLTokener extends JSONTokener {
    public static final HashMap entity;

    static {
        HashMap hashMap = new HashMap(8);
        entity = hashMap;
        hashMap.put("amp", XML.AMP);
        hashMap.put("apos", XML.APOS);
        hashMap.put("gt", XML.GT);
        hashMap.put("lt", XML.LT);
        hashMap.put("quot", XML.QUOT);
    }

    public XMLTokener(String s) {
        super(s);
    }

    public String nextCDATA() throws JSONException {
        StringBuffer sb = new StringBuffer();
        while (true) {
            char c = next();
            if (!end()) {
                sb.append(c);
                int i = sb.length() - 3;
                if (i >= 0 && sb.charAt(i) == ']' && sb.charAt(i + 1) == ']' && sb.charAt(i + 2) == '>') {
                    sb.setLength(i);
                    return sb.toString();
                }
            } else {
                throw syntaxError("Unclosed CDATA");
            }
        }
    }

    public Object nextContent() throws JSONException {
        char c;
        do {
            c = next();
        } while (Character.isWhitespace(c));
        if (c == 0) {
            return null;
        }
        if (c == '<') {
            return XML.LT;
        }
        StringBuffer sb = new StringBuffer();
        while (c != '<' && c != 0) {
            if (c == '&') {
                sb.append(nextEntity(c));
            } else {
                sb.append(c);
            }
            c = next();
        }
        back();
        return sb.toString().trim();
    }

    public Object nextEntity(char ampersand) throws JSONException {
        char c;
        StringBuffer sb = new StringBuffer();
        while (true) {
            c = next();
            if (!Character.isLetterOrDigit(c) && c != '#') {
                break;
            }
            sb.append(Character.toLowerCase(c));
        }
        if (c == ';') {
            String string = sb.toString();
            Object object = entity.get(string);
            return object != null ? object : new StringBuffer().append(ampersand).append(string).append(";").toString();
        }
        throw syntaxError(new StringBuffer().append("Missing ';' in XML entity: &").append(sb).toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x003c A[LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object nextMeta() throws com.google.appinventor.components.runtime.repackaged.org.json.JSONException {
        /*
            r3 = this;
        L_0x0001:
            char r0 = r3.next()
            boolean r1 = java.lang.Character.isWhitespace(r0)
            if (r1 != 0) goto L_0x0001
            switch(r0) {
                case 0: goto L_0x0035;
                case 33: goto L_0x0032;
                case 34: goto L_0x001f;
                case 39: goto L_0x001f;
                case 47: goto L_0x001c;
                case 60: goto L_0x0019;
                case 61: goto L_0x0016;
                case 62: goto L_0x0013;
                case 63: goto L_0x0010;
                default: goto L_0x000e;
            }
        L_0x000e:
            r1 = 0
            goto L_0x003c
        L_0x0010:
            java.lang.Character r1 = com.google.appinventor.components.runtime.repackaged.org.json.XML.QUEST
            return r1
        L_0x0013:
            java.lang.Character r1 = com.google.appinventor.components.runtime.repackaged.org.json.XML.GT
            return r1
        L_0x0016:
            java.lang.Character r1 = com.google.appinventor.components.runtime.repackaged.org.json.XML.EQ
            return r1
        L_0x0019:
            java.lang.Character r1 = com.google.appinventor.components.runtime.repackaged.org.json.XML.LT
            return r1
        L_0x001c:
            java.lang.Character r1 = com.google.appinventor.components.runtime.repackaged.org.json.XML.SLASH
            return r1
        L_0x001f:
            r1 = r0
        L_0x0020:
            char r0 = r3.next()
            if (r0 == 0) goto L_0x002b
            if (r0 != r1) goto L_0x0020
            java.lang.Boolean r2 = java.lang.Boolean.TRUE
            return r2
        L_0x002b:
            java.lang.String r2 = "Unterminated string"
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r2 = r3.syntaxError(r2)
            throw r2
        L_0x0032:
            java.lang.Character r1 = com.google.appinventor.components.runtime.repackaged.org.json.XML.BANG
            return r1
        L_0x0035:
            java.lang.String r1 = "Misshaped meta tag"
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r1 = r3.syntaxError(r1)
            throw r1
        L_0x003c:
            char r0 = r3.next()
            boolean r2 = java.lang.Character.isWhitespace(r0)
            if (r2 == 0) goto L_0x0049
            java.lang.Boolean r2 = java.lang.Boolean.TRUE
            return r2
        L_0x0049:
            switch(r0) {
                case 0: goto L_0x004d;
                case 33: goto L_0x004d;
                case 34: goto L_0x004d;
                case 39: goto L_0x004d;
                case 47: goto L_0x004d;
                case 60: goto L_0x004d;
                case 61: goto L_0x004d;
                case 62: goto L_0x004d;
                case 63: goto L_0x004d;
                default: goto L_0x004c;
            }
        L_0x004c:
            goto L_0x003c
        L_0x004d:
            r3.back()
            java.lang.Boolean r2 = java.lang.Boolean.TRUE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.repackaged.org.json.XMLTokener.nextMeta():java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x005e A[LOOP_START, PHI: r0 
      PHI: (r0v2 'c' char) = (r0v0 'c' char), (r0v3 'c' char) binds: [B:4:0x000e, B:35:0x0070] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object nextToken() throws com.google.appinventor.components.runtime.repackaged.org.json.JSONException {
        /*
            r4 = this;
        L_0x0001:
            char r0 = r4.next()
            boolean r1 = java.lang.Character.isWhitespace(r0)
            if (r1 != 0) goto L_0x0001
            switch(r0) {
                case 0: goto L_0x0057;
                case 33: goto L_0x0054;
                case 34: goto L_0x002a;
                case 39: goto L_0x002a;
                case 47: goto L_0x0027;
                case 60: goto L_0x0020;
                case 61: goto L_0x001d;
                case 62: goto L_0x001a;
                case 63: goto L_0x0017;
                default: goto L_0x000e;
            }
        L_0x000e:
            r1 = 0
            r2 = 0
            java.lang.StringBuffer r3 = new java.lang.StringBuffer
            r3.<init>()
            r2 = r3
            goto L_0x005e
        L_0x0017:
            java.lang.Character r1 = com.google.appinventor.components.runtime.repackaged.org.json.XML.QUEST
            return r1
        L_0x001a:
            java.lang.Character r1 = com.google.appinventor.components.runtime.repackaged.org.json.XML.GT
            return r1
        L_0x001d:
            java.lang.Character r1 = com.google.appinventor.components.runtime.repackaged.org.json.XML.EQ
            return r1
        L_0x0020:
            java.lang.String r1 = "Misplaced '<'"
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r1 = r4.syntaxError(r1)
            throw r1
        L_0x0027:
            java.lang.Character r1 = com.google.appinventor.components.runtime.repackaged.org.json.XML.SLASH
            return r1
        L_0x002a:
            r1 = r0
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
        L_0x0030:
            char r0 = r4.next()
            if (r0 == 0) goto L_0x004d
            if (r0 != r1) goto L_0x003d
            java.lang.String r3 = r2.toString()
            return r3
        L_0x003d:
            r3 = 38
            if (r0 != r3) goto L_0x0049
            java.lang.Object r3 = r4.nextEntity(r0)
            r2.append(r3)
            goto L_0x0030
        L_0x0049:
            r2.append(r0)
            goto L_0x0030
        L_0x004d:
            java.lang.String r3 = "Unterminated string"
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r3 = r4.syntaxError(r3)
            throw r3
        L_0x0054:
            java.lang.Character r1 = com.google.appinventor.components.runtime.repackaged.org.json.XML.BANG
            return r1
        L_0x0057:
            java.lang.String r1 = "Misshaped element"
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r1 = r4.syntaxError(r1)
            throw r1
        L_0x005e:
            r2.append(r0)
            char r0 = r4.next()
            boolean r3 = java.lang.Character.isWhitespace(r0)
            if (r3 == 0) goto L_0x0070
            java.lang.String r3 = r2.toString()
            return r3
        L_0x0070:
            switch(r0) {
                case 0: goto L_0x0083;
                case 33: goto L_0x007b;
                case 34: goto L_0x0074;
                case 39: goto L_0x0074;
                case 47: goto L_0x007b;
                case 60: goto L_0x0074;
                case 61: goto L_0x007b;
                case 62: goto L_0x007b;
                case 63: goto L_0x007b;
                case 91: goto L_0x007b;
                case 93: goto L_0x007b;
                default: goto L_0x0073;
            }
        L_0x0073:
            goto L_0x005e
        L_0x0074:
            java.lang.String r3 = "Bad character in a name"
            com.google.appinventor.components.runtime.repackaged.org.json.JSONException r3 = r4.syntaxError(r3)
            throw r3
        L_0x007b:
            r4.back()
            java.lang.String r3 = r2.toString()
            return r3
        L_0x0083:
            java.lang.String r3 = r2.toString()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.repackaged.org.json.XMLTokener.nextToken():java.lang.Object");
    }

    public boolean skipPast(String to) throws JSONException {
        int offset = 0;
        int length = to.length();
        char[] circle = new char[length];
        for (int i = 0; i < length; i++) {
            char c = next();
            if (c == 0) {
                return false;
            }
            circle[i] = c;
        }
        while (true) {
            int j = offset;
            boolean b = true;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                } else if (circle[j] != to.charAt(i2)) {
                    b = false;
                    break;
                } else {
                    j++;
                    if (j >= length) {
                        j -= length;
                    }
                    i2++;
                }
            }
            if (b) {
                return true;
            }
            char c2 = next();
            if (c2 == 0) {
                return false;
            }
            circle[offset] = c2;
            offset++;
            if (offset >= length) {
                offset -= length;
            }
        }
    }
}
