package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class ConstantValueAttr extends Attribute {
    Object value;
    int value_index;

    public Object getValue(ConstantPool cpool) {
        Object obj = this.value;
        if (obj != null) {
            return obj;
        }
        CpoolEntry entry = cpool.getPoolEntry(this.value_index);
        switch (entry.getTag()) {
            case 3:
                this.value = new Integer(((CpoolValue1) entry).value);
                break;
            case 4:
                this.value = new Float(Float.intBitsToFloat(((CpoolValue1) entry).value));
                break;
            case 5:
                this.value = new Long(((CpoolValue2) entry).value);
                break;
            case 6:
                this.value = new Double(Double.longBitsToDouble(((CpoolValue2) entry).value));
                break;
            case 8:
                this.value = ((CpoolString) entry).getString().getString();
                break;
        }
        return this.value;
    }

    public ConstantValueAttr(Object value2) {
        super("ConstantValue");
        this.value = value2;
    }

    public ConstantValueAttr(int index) {
        super("ConstantValue");
        this.value_index = index;
    }

    public void assignConstants(ClassType cl) {
        super.assignConstants(cl);
        if (this.value_index == 0) {
            ConstantPool cpool = cl.getConstants();
            CpoolEntry entry = null;
            Object obj = this.value;
            if (obj instanceof String) {
                entry = cpool.addString((String) obj);
            } else if (obj instanceof Integer) {
                entry = cpool.addInt(((Integer) obj).intValue());
            } else if (obj instanceof Long) {
                entry = cpool.addLong(((Long) obj).longValue());
            } else if (obj instanceof Float) {
                entry = cpool.addFloat(((Float) obj).floatValue());
            } else if (obj instanceof Long) {
                entry = cpool.addDouble(((Double) obj).doubleValue());
            }
            this.value_index = entry.getIndex();
        }
    }

    public final int getLength() {
        return 2;
    }

    public void write(DataOutputStream dstr) throws IOException {
        dstr.writeShort(this.value_index);
    }

    public void print(ClassTypeWriter dst) {
        dst.print("Attribute \"");
        dst.print(getName());
        dst.print("\", length:");
        dst.print(getLength());
        dst.print(", value: ");
        int i = this.value_index;
        if (i == 0) {
            Object value2 = getValue(dst.ctype.constants);
            if (value2 instanceof String) {
                dst.printQuotedString((String) value2);
            } else {
                dst.print(value2);
            }
        } else {
            dst.printOptionalIndex(i);
            dst.ctype.constants.getPoolEntry(this.value_index).print(dst, 1);
        }
        dst.println();
    }
}
