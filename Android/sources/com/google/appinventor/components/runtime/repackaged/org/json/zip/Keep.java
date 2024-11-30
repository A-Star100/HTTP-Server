package com.google.appinventor.components.runtime.repackaged.org.json.zip;

abstract class Keep implements None, PostMortem {
    protected int capacity;
    protected int length = 0;
    protected int power = 0;
    protected long[] uses;

    public abstract Object value(int i);

    public Keep(int bits) {
        int i = JSONzip.twos[bits];
        this.capacity = i;
        this.uses = new long[i];
    }

    public static long age(long use) {
        if (use >= 32) {
            return 16;
        }
        return use / 2;
    }

    public int bitsize() {
        while (true) {
            int[] iArr = JSONzip.twos;
            int i = this.power;
            if (iArr[i] >= this.length) {
                return i;
            }
            this.power = i + 1;
        }
    }

    public void tick(int integer) {
        long[] jArr = this.uses;
        jArr[integer] = jArr[integer] + 1;
    }
}
