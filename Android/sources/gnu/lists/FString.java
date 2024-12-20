package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Writer;

public class FString extends SimpleVector implements Comparable, Appendable, CharSeq, Externalizable, Consumable {
    protected static char[] empty = new char[0];
    public char[] data;

    public FString() {
        this.data = empty;
    }

    public FString(int num) {
        this.size = num;
        this.data = new char[num];
    }

    public FString(int num, char value) {
        char[] array = new char[num];
        this.data = array;
        this.size = num;
        while (true) {
            num--;
            if (num >= 0) {
                array[num] = value;
            } else {
                return;
            }
        }
    }

    public FString(char[] values) {
        this.size = values.length;
        this.data = values;
    }

    public FString(String str) {
        char[] charArray = str.toCharArray();
        this.data = charArray;
        this.size = charArray.length;
    }

    public FString(StringBuffer buffer) {
        this(buffer, 0, buffer.length());
    }

    public FString(StringBuffer buffer, int offset, int length) {
        this.size = length;
        char[] cArr = new char[length];
        this.data = cArr;
        if (length > 0) {
            buffer.getChars(offset, offset + length, cArr, 0);
        }
    }

    public FString(char[] buffer, int offset, int length) {
        this.size = length;
        char[] cArr = new char[length];
        this.data = cArr;
        System.arraycopy(buffer, offset, cArr, 0, length);
    }

    public FString(Sequence seq) {
        this.data = new char[seq.size()];
        addAll(seq);
    }

    public FString(CharSeq seq) {
        this(seq, 0, seq.size());
    }

    public FString(CharSeq seq, int offset, int length) {
        char[] data2 = new char[length];
        seq.getChars(offset, offset + length, data2, 0);
        this.data = data2;
        this.size = length;
    }

    public FString(CharSequence seq) {
        this(seq, 0, seq.length());
    }

    public FString(CharSequence seq, int offset, int length) {
        char[] data2 = new char[length];
        int i = length;
        while (true) {
            i--;
            if (i >= 0) {
                data2[i] = seq.charAt(offset + i);
            } else {
                this.data = data2;
                this.size = length;
                return;
            }
        }
    }

    public int length() {
        return this.size;
    }

    public int getBufferLength() {
        return this.data.length;
    }

    public void setBufferLength(int length) {
        char[] cArr = this.data;
        int oldLength = cArr.length;
        if (oldLength != length) {
            char[] tmp = new char[length];
            System.arraycopy(cArr, 0, tmp, 0, oldLength < length ? oldLength : length);
            this.data = tmp;
        }
    }

    public void ensureBufferLength(int sz) {
        char[] cArr = this.data;
        if (sz > cArr.length) {
            char[] d = new char[(sz < 60 ? 120 : sz * 2)];
            System.arraycopy(cArr, 0, d, 0, sz);
            this.data = d;
        }
    }

    /* access modifiers changed from: protected */
    public Object getBuffer() {
        return this.data;
    }

    public final Object getBuffer(int index) {
        return Convert.toObject(this.data[index]);
    }

    public final Object setBuffer(int index, Object value) {
        Object old = Convert.toObject(this.data[index]);
        this.data[index] = Convert.toChar(value);
        return old;
    }

    public final Object get(int index) {
        if (index < this.size) {
            return Convert.toObject(this.data[index]);
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public final char charAt(int index) {
        if (index < this.size) {
            return this.data[index];
        }
        throw new StringIndexOutOfBoundsException(index);
    }

    public final char charAtBuffer(int index) {
        return this.data[index];
    }

    public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
        if (srcBegin < 0 || srcBegin > srcEnd) {
            throw new StringIndexOutOfBoundsException(srcBegin);
        } else if (srcEnd > this.size) {
            throw new StringIndexOutOfBoundsException(srcEnd);
        } else if ((dstBegin + srcEnd) - srcBegin > dst.length) {
            throw new StringIndexOutOfBoundsException(dstBegin);
        } else if (srcBegin < srcEnd) {
            System.arraycopy(this.data, srcBegin, dst, dstBegin, srcEnd - srcBegin);
        }
    }

    public void getChars(int srcBegin, int srcEnd, StringBuffer dst) {
        if (srcBegin < 0 || srcBegin > srcEnd) {
            throw new StringIndexOutOfBoundsException(srcBegin);
        } else if (srcEnd > this.size) {
            throw new StringIndexOutOfBoundsException(srcEnd);
        } else if (srcBegin < srcEnd) {
            dst.append(this.data, srcBegin, srcEnd - srcBegin);
        }
    }

    public void getChars(StringBuffer dst) {
        dst.append(this.data, 0, this.size);
    }

    public char[] toCharArray() {
        int val_length = this.data.length;
        int seq_length = this.size;
        if (seq_length == val_length) {
            return this.data;
        }
        char[] tmp = new char[seq_length];
        System.arraycopy(this.data, 0, tmp, 0, seq_length);
        return tmp;
    }

    public void shift(int srcStart, int dstStart, int count) {
        char[] cArr = this.data;
        System.arraycopy(cArr, srcStart, cArr, dstStart, count);
    }

    public FString copy(int start, int end) {
        char[] copy = new char[(end - start)];
        char[] src = this.data;
        for (int i = start; i < end; i++) {
            copy[i - start] = src[i];
        }
        return new FString(copy);
    }

    public boolean addAll(FString s) {
        int newSize = this.size + s.size;
        if (this.data.length < newSize) {
            setBufferLength(newSize);
        }
        System.arraycopy(s.data, 0, this.data, this.size, s.size);
        this.size = newSize;
        if (s.size > 0) {
            return true;
        }
        return false;
    }

    public boolean addAll(CharSequence s) {
        int ssize = s.length();
        int newSize = this.size + ssize;
        if (this.data.length < newSize) {
            setBufferLength(newSize);
        }
        if (!(s instanceof FString)) {
            if (!(s instanceof String)) {
                if (!(s instanceof CharSeq)) {
                    int i = ssize;
                    while (true) {
                        i--;
                        if (i < 0) {
                            break;
                        }
                        this.data[this.size + i] = s.charAt(i);
                    }
                } else {
                    ((CharSeq) s).getChars(0, ssize, this.data, this.size);
                }
            } else {
                ((String) s).getChars(0, ssize, this.data, this.size);
            }
        } else {
            System.arraycopy(((FString) s).data, 0, this.data, this.size, ssize);
        }
        this.size = newSize;
        if (ssize > 0) {
            return true;
        }
        return false;
    }

    public void addAllStrings(Object[] args, int startIndex) {
        int total = this.size;
        for (int i = startIndex; i < args.length; i++) {
            total += args[i].length();
        }
        if (this.data.length < total) {
            setBufferLength(total);
        }
        for (int i2 = startIndex; i2 < args.length; i2++) {
            addAll(args[i2]);
        }
    }

    public String toString() {
        return new String(this.data, 0, this.size);
    }

    public String substring(int start, int end) {
        return new String(this.data, start, end - start);
    }

    public CharSequence subSequence(int start, int end) {
        return new FString(this.data, start, end - start);
    }

    public void setCharAt(int index, char ch) {
        if (index < 0 || index >= this.size) {
            throw new StringIndexOutOfBoundsException(index);
        }
        this.data[index] = ch;
    }

    public void setCharAtBuffer(int index, char ch) {
        this.data[index] = ch;
    }

    public final void fill(char ch) {
        char[] d = this.data;
        int i = this.size;
        while (true) {
            i--;
            if (i >= 0) {
                d[i] = ch;
            } else {
                return;
            }
        }
    }

    public void fill(int fromIndex, int toIndex, char value) {
        if (fromIndex < 0 || toIndex > this.size) {
            throw new IndexOutOfBoundsException();
        }
        char[] d = this.data;
        for (int i = fromIndex; i < toIndex; i++) {
            d[i] = value;
        }
    }

    /* access modifiers changed from: protected */
    public void clearBuffer(int start, int count) {
        char[] d = this.data;
        while (true) {
            count--;
            if (count >= 0) {
                d[start] = 0;
                start++;
            } else {
                return;
            }
        }
    }

    public void replace(int where, char[] chars, int start, int count) {
        System.arraycopy(chars, start, this.data, where, count);
    }

    public void replace(int where, String string) {
        string.getChars(0, string.length(), this.data, where);
    }

    public int hashCode() {
        char[] val = this.data;
        int len = this.size;
        int hash = 0;
        for (int i = 0; i < len; i++) {
            hash = (hash * 31) + val[i];
        }
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof FString)) {
            return false;
        }
        char[] str = ((FString) obj).data;
        int n = this.size;
        if (str == null || str.length != n) {
            return false;
        }
        char[] d = this.data;
        int i = n;
        do {
            i--;
            if (i < 0) {
                return true;
            }
        } while (d[i] == str[i]);
        return false;
    }

    public int compareTo(Object obj) {
        FString str2 = (FString) obj;
        char[] cs1 = this.data;
        char[] cs2 = str2.data;
        int n1 = this.size;
        int n2 = str2.size;
        int n = n1 > n2 ? n2 : n1;
        for (int i = 0; i < n; i++) {
            int d = cs1[i] - cs2[i];
            if (d != 0) {
                return d;
            }
        }
        return n1 - n2;
    }

    public int getElementKind() {
        return 29;
    }

    public void consume(Consumer out) {
        char[] cArr = this.data;
        out.write(cArr, 0, cArr.length);
    }

    public boolean consumeNext(int ipos, Consumer out) {
        int index = ipos >>> 1;
        if (index >= this.size) {
            return false;
        }
        out.write((int) this.data[index]);
        return true;
    }

    public void consumePosRange(int iposStart, int iposEnd, Consumer out) {
        if (!out.ignoring()) {
            int i = iposStart >>> 1;
            int end = iposEnd >>> 1;
            if (end > this.size) {
                end = this.size;
            }
            if (end > i) {
                out.write(this.data, i, end - i);
            }
        }
    }

    public FString append(char c) {
        int sz = this.size;
        if (sz >= this.data.length) {
            ensureBufferLength(sz + 1);
        }
        this.data[sz] = c;
        this.size = sz + 1;
        return this;
    }

    public FString append(CharSequence csq) {
        if (csq == null) {
            csq = "null";
        }
        return append(csq, 0, csq.length());
    }

    public FString append(CharSequence csq, int start, int end) {
        if (csq == null) {
            csq = "null";
        }
        int len = end - start;
        int sz = this.size;
        if (sz + len > this.data.length) {
            ensureBufferLength(sz + len);
        }
        char[] d = this.data;
        if (csq instanceof String) {
            ((String) csq).getChars(start, end, d, sz);
        } else if (csq instanceof CharSeq) {
            ((CharSeq) csq).getChars(start, end, d, sz);
        } else {
            int j = sz;
            int i = start;
            while (i < end) {
                d[j] = csq.charAt(i);
                i++;
                j++;
            }
        }
        this.size = sz;
        return this;
    }

    public void writeTo(int start, int count, Appendable dest) throws IOException {
        if (dest instanceof Writer) {
            try {
                ((Writer) dest).write(this.data, start, count);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            dest.append(this, start, start + count);
        }
    }

    public void writeTo(Appendable dest) throws IOException {
        writeTo(0, this.size, dest);
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        int size = this.size;
        out.writeInt(size);
        char[] d = this.data;
        for (int i = 0; i < size; i++) {
            out.writeChar(d[i]);
        }
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        int size = in.readInt();
        char[] data2 = new char[size];
        for (int i = 0; i < size; i++) {
            data2[i] = in.readChar();
        }
        this.data = data2;
        this.size = size;
    }
}
