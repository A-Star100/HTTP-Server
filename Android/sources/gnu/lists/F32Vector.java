package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class F32Vector extends SimpleVector implements Externalizable, Comparable {
    protected static float[] empty = new float[0];
    float[] data;

    public F32Vector() {
        this.data = empty;
    }

    public F32Vector(int size, float value) {
        float[] array = new float[size];
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

    public F32Vector(int size) {
        this.data = new float[size];
        this.size = size;
    }

    public F32Vector(float[] data2) {
        this.data = data2;
        this.size = data2.length;
    }

    public F32Vector(Sequence seq) {
        this.data = new float[seq.size()];
        addAll(seq);
    }

    public int getBufferLength() {
        return this.data.length;
    }

    public void setBufferLength(int length) {
        float[] fArr = this.data;
        int oldLength = fArr.length;
        if (oldLength != length) {
            float[] tmp = new float[length];
            System.arraycopy(fArr, 0, tmp, 0, oldLength < length ? oldLength : length);
            this.data = tmp;
        }
    }

    /* access modifiers changed from: protected */
    public Object getBuffer() {
        return this.data;
    }

    public final int intAtBuffer(int index) {
        return (int) this.data[index];
    }

    public final float floatAt(int index) {
        if (index < this.size) {
            return this.data[index];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public final float floatAtBuffer(int index) {
        return this.data[index];
    }

    public final Object get(int index) {
        if (index <= this.size) {
            return Convert.toObject(this.data[index]);
        }
        throw new IndexOutOfBoundsException();
    }

    public final Object getBuffer(int index) {
        return Convert.toObject(this.data[index]);
    }

    public final void setFloatAt(int index, float value) {
        if (index <= this.size) {
            this.data[index] = value;
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    public final void setFloatAtBuffer(int index, float value) {
        this.data[index] = value;
    }

    public final Object setBuffer(int index, Object value) {
        Object old = Convert.toObject(this.data[index]);
        this.data[index] = Convert.toFloat(value);
        return old;
    }

    /* access modifiers changed from: protected */
    public void clearBuffer(int start, int count) {
        while (true) {
            count--;
            if (count >= 0) {
                this.data[start] = 0.0f;
                start++;
            } else {
                return;
            }
        }
    }

    public int getElementKind() {
        return 25;
    }

    public String getTag() {
        return "f32";
    }

    public boolean consumeNext(int ipos, Consumer out) {
        int index = ipos >>> 1;
        if (index >= this.size) {
            return false;
        }
        out.writeFloat(this.data[index]);
        return true;
    }

    public void consumePosRange(int iposStart, int iposEnd, Consumer out) {
        if (!out.ignoring()) {
            int end = iposEnd >>> 1;
            for (int i = iposStart >>> 1; i < end; i++) {
                out.writeFloat(this.data[i]);
            }
        }
    }

    public int compareTo(Object obj) {
        F32Vector vec2 = (F32Vector) obj;
        float[] arr1 = this.data;
        float[] arr2 = vec2.data;
        int n1 = this.size;
        int n2 = vec2.size;
        int n = n1 > n2 ? n2 : n1;
        for (int i = 0; i < n; i++) {
            float v1 = arr1[i];
            float v2 = arr2[i];
            if (v1 != v2) {
                return v1 > v2 ? 1 : -1;
            }
        }
        return n1 - n2;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        int size = this.size;
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            out.writeFloat(this.data[i]);
        }
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        int size = in.readInt();
        float[] data2 = new float[size];
        for (int i = 0; i < size; i++) {
            data2[i] = in.readFloat();
        }
        this.data = data2;
        this.size = size;
    }
}
