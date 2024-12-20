package gnu.text;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

public class LineInputStreamReader extends LineBufferedReader {
    byte[] barr;
    ByteBuffer bbuf;
    char[] carr;
    CharBuffer cbuf = null;
    Charset cset;
    CharsetDecoder decoder;
    InputStream istrm;

    public void setCharset(Charset cset2) {
        this.cset = cset2;
        this.decoder = cset2.newDecoder();
    }

    public void setCharset(String name) {
        Charset cset2 = Charset.forName(name);
        Charset charset = this.cset;
        if (charset == null) {
            setCharset(cset2);
        } else if (!cset2.equals(charset)) {
            throw new RuntimeException("encoding " + name + " does not match previous " + this.cset);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LineInputStreamReader(InputStream in) {
        super((Reader) null);
        Reader reader = null;
        byte[] bArr = new byte[8192];
        this.barr = bArr;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        this.bbuf = wrap;
        wrap.position(this.barr.length);
        this.istrm = in;
    }

    public void close() throws IOException {
        if (this.in != null) {
            this.in.close();
        }
        this.istrm.close();
    }

    private int fillBytes(int remaining) throws IOException {
        InputStream inputStream = this.istrm;
        byte[] bArr = this.barr;
        int n = inputStream.read(bArr, remaining, bArr.length - remaining);
        int i = 0;
        this.bbuf.position(0);
        ByteBuffer byteBuffer = this.bbuf;
        if (n >= 0) {
            i = n;
        }
        byteBuffer.limit(i + remaining);
        return n;
    }

    public void markStart() throws IOException {
    }

    public void resetStart(int pos) throws IOException {
        this.bbuf.position(pos);
    }

    public int getByte() throws IOException {
        if (this.bbuf.hasRemaining() || fillBytes(0) > 0) {
            return this.bbuf.get() & Ev3Constants.Opcode.TST;
        }
        return -1;
    }

    public int fill(int len) throws IOException {
        int count;
        if (this.cset == null) {
            setCharset("UTF-8");
        }
        if (this.buffer != this.carr) {
            this.cbuf = CharBuffer.wrap(this.buffer);
            this.carr = this.buffer;
        }
        this.cbuf.limit(this.pos + len);
        this.cbuf.position(this.pos);
        boolean eof = false;
        while (true) {
            CoderResult cres = this.decoder.decode(this.bbuf, this.cbuf, false);
            count = this.cbuf.position() - this.pos;
            if (count > 0 || !cres.isUnderflow()) {
                break;
            }
            int rem = this.bbuf.remaining();
            if (rem > 0) {
                this.bbuf.compact();
            }
            if (fillBytes(rem) < 0) {
                eof = true;
                break;
            }
        }
        if (count != 0 || !eof) {
            return count;
        }
        return -1;
    }

    public boolean ready() throws IOException {
        return this.pos < this.limit || this.bbuf.hasRemaining() || this.istrm.available() > 0;
    }
}
