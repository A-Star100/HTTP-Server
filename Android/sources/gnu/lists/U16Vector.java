package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class U16Vector extends SimpleVector implements Externalizable, Comparable {
    short[] data;

    public U16Vector() {
        this.data = S16Vector.empty;
    }

    public U16Vector(int size, short value) {
        short[] array = new short[size];
        this.data = array;
        this.size = size;
        while (true) {
            size--;
            if (size >= 0) {
                array[size] = value;
            } else {
                return;
            }
        }
    }

    public U16Vector(int size) {
        this.data = new short[size];
        this.size = size;
    }

    public U16Vector(short[] data2) {
        this.data = data2;
        this.size = data2.length;
    }

    public U16Vector(Sequence seq) {
        this.data = new short[seq.size()];
        addAll(seq);
    }

    public int getBufferLength() {
        return this.data.length;
    }

    public void setBufferLength(int length) {
        short[] sArr = this.data;
        int oldLength = sArr.length;
        if (oldLength != length) {
            short[] tmp = new short[length];
            System.arraycopy(sArr, 0, tmp, 0, oldLength < length ? oldLength : length);
            this.data = tmp;
        }
    }

    /* access modifiers changed from: protected */
    public Object getBuffer() {
        return this.data;
    }

    public final short shortAt(int index) {
        if (index <= this.size) {
            return this.data[index];
        }
        throw new IndexOutOfBoundsException();
    }

    public final short shortAtBuffer(int index) {
        return this.data[index];
    }

    public final int intAtBuffer(int index) {
        return this.data[index] & 65535;
    }

    public final Object get(int index) {
        if (index <= this.size) {
            return Convert.toObjectUnsigned(this.data[index]);
        }
        throw new IndexOutOfBoundsException();
    }

    public final Object getBuffer(int index) {
        return Convert.toObjectUnsigned(this.data[index]);
    }

    public Object setBuffer(int index, Object value) {
        short[] sArr = this.data;
        short old = sArr[index];
        sArr[index] = Convert.toShortUnsigned(value);
        return Convert.toObjectUnsigned(old);
    }

    public final void setShortAt(int index, short value) {
        if (index <= this.size) {
            this.data[index] = value;
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    public final void setShortAtBuffer(int index, short value) {
        this.data[index] = value;
    }

    /* access modifiers changed from: protected */
    public void clearBuffer(int start, int count) {
        while (true) {
            count--;
            if (count >= 0) {
                this.data[start] = 0;
                start++;
            } else {
                return;
            }
        }
    }

    public int getElementKind() {
        return 19;
    }

    public String getTag() {
        return "u16";
    }

    public boolean consumeNext(int ipos, Consumer out) {
        int index = ipos >>> 1;
        if (index >= this.size) {
            return false;
        }
        out.writeInt(this.data[index] & 65535);
        return true;
    }

    public void consumePosRange(int iposStart, int iposEnd, Consumer out) {
        if (!out.ignoring()) {
            int end = iposEnd >>> 1;
            if (end > this.size) {
                end = this.size;
            }
            for (int i = iposStart >>> 1; i < end; i++) {
                out.writeInt(this.data[i] & 65535);
            }
        }
    }

    public int compareTo(Object obj) {
        return compareToInt(this, (U16Vector) obj);
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        int size = this.size;
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            out.writeShort(this.data[i]);
        }
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        int size = in.readInt();
        short[] data2 = new short[size];
        for (int i = 0; i < size; i++) {
            data2[i] = in.readShort();
        }
        this.data = data2;
        this.size = size;
    }
}
