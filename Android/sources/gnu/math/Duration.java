package gnu.math;

import gnu.bytecode.Access;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Duration extends Quantity implements Externalizable {
    int months;
    int nanos;
    long seconds;
    public Unit unit;

    public static Duration make(int months2, long seconds2, int nanos2, Unit unit2) {
        Duration d = new Duration();
        d.months = months2;
        d.seconds = seconds2;
        d.nanos = nanos2;
        d.unit = unit2;
        return d;
    }

    public static Duration makeMonths(int months2) {
        Duration d = new Duration();
        d.unit = Unit.month;
        d.months = months2;
        return d;
    }

    public static Duration makeMinutes(int minutes) {
        Duration d = new Duration();
        d.unit = Unit.second;
        d.seconds = (long) (minutes * 60);
        return d;
    }

    public static Duration parse(String str, Unit unit2) {
        Duration d = valueOf(str, unit2);
        if (d != null) {
            return d;
        }
        throw new IllegalArgumentException("not a valid " + unit2.getName() + " duration: '" + str + "'");
    }

    public static Duration parseDuration(String str) {
        return parse(str, Unit.duration);
    }

    public static Duration parseYearMonthDuration(String str) {
        return parse(str, Unit.month);
    }

    public static Duration parseDayTimeDuration(String str) {
        return parse(str, Unit.second);
    }

    public static Duration valueOf(String str, Unit unit2) {
        boolean negative;
        int nanos2;
        int pos;
        int nanos3;
        char ch;
        long part;
        Unit unit3 = unit2;
        String str2 = str.trim();
        int pos2 = 0;
        int len = str2.length();
        if (0 >= len || str2.charAt(0) != '-') {
            negative = false;
        } else {
            negative = true;
            pos2 = 0 + 1;
        }
        if (pos2 + 1 >= len || str2.charAt(pos2) != 'P') {
            return null;
        }
        int months2 = 0;
        long seconds2 = 0;
        long part2 = scanPart(str2, pos2 + 1);
        int pos3 = ((int) part2) >> 16;
        char ch2 = (char) ((int) part2);
        if (unit3 == Unit.second && (ch2 == 'Y' || ch2 == 'M')) {
            return null;
        }
        if (ch2 == 'Y') {
            nanos2 = 0;
            months2 = ((int) (part2 >> 32)) * 12;
            pos3 = ((int) part2) >> 16;
            part2 = scanPart(str2, pos3);
            ch2 = (char) ((int) part2);
        } else {
            nanos2 = 0;
        }
        if (ch2 == 'M') {
            months2 = (int) (((long) months2) + (part2 >> 32));
            pos3 = ((int) part2) >> 16;
            part2 = scanPart(str2, pos3);
            ch2 = (char) ((int) part2);
        }
        if (unit3 == Unit.month && pos3 != len) {
            return null;
        }
        if (ch2 == 'D') {
            if (unit3 == Unit.month) {
                return null;
            }
            seconds2 = ((long) ((int) (part2 >> 32))) * 86400;
            pos3 = ((int) part2) >> 16;
            part2 = scanPart(str2, pos3);
        }
        if (part2 != ((long) (pos << 16))) {
            return null;
        }
        if (pos == len) {
            nanos3 = nanos2;
        } else if (str2.charAt(pos) != 'T' || (pos = pos + 1) == len || unit3 == Unit.month) {
            return null;
        } else {
            long part3 = scanPart(str2, pos);
            char ch3 = (char) ((int) part3);
            if (ch3 == 'H') {
                seconds2 += (long) (((int) (part3 >> 32)) * 3600);
                pos = ((int) part3) >> 16;
                part3 = scanPart(str2, pos);
                ch3 = (char) ((int) part3);
            }
            if (ch3 == 'M') {
                seconds2 += (long) (((int) (part3 >> 32)) * 60);
                pos = ((int) part3) >> 16;
                long part4 = scanPart(str2, pos);
                ch = (char) ((int) part4);
                part = part4;
            } else {
                ch = ch3;
                part = part3;
            }
            if (ch == 'S' || ch == '.') {
                seconds2 += (long) ((int) (part >> 32));
                pos = ((int) part) >> 16;
            }
            if (ch != '.' || pos + 1 >= len || Character.digit(str2.charAt(pos), 10) < 0) {
                nanos3 = nanos2;
            } else {
                int nfrac = 0;
                while (true) {
                    if (pos >= len) {
                        nanos3 = nanos2;
                        break;
                    }
                    int pos4 = pos + 1;
                    ch = str2.charAt(pos);
                    int dig = Character.digit(ch, 10);
                    if (dig < 0) {
                        nanos3 = nanos2;
                        pos = pos4;
                        break;
                    }
                    if (nfrac < 9) {
                        nanos2 = (nanos2 * 10) + dig;
                    } else if (nfrac == 9 && dig >= 5) {
                        nanos2++;
                    }
                    nfrac++;
                    pos = pos4;
                }
                while (true) {
                    int nanos4 = nfrac + 1;
                    if (nfrac >= 9) {
                        break;
                    }
                    nanos3 *= 10;
                    nfrac = nanos4;
                }
                if (ch != 'S') {
                    return null;
                }
            }
        }
        if (pos != len) {
            return null;
        }
        Duration d = new Duration();
        if (negative) {
            months2 = -months2;
            seconds2 = -seconds2;
            nanos3 = -nanos3;
        }
        d.months = months2;
        d.seconds = seconds2;
        d.nanos = nanos3;
        d.unit = unit3;
        return d;
    }

    public Numeric add(Object y, int k) {
        if (y instanceof Duration) {
            return add(this, (Duration) y, k);
        }
        if ((y instanceof DateTime) && k == 1) {
            return DateTime.add((DateTime) y, this, 1);
        }
        throw new IllegalArgumentException();
    }

    public Numeric mul(Object y) {
        if (y instanceof RealNum) {
            return times(this, ((RealNum) y).doubleValue());
        }
        return ((Numeric) y).mulReversed(this);
    }

    public Numeric mulReversed(Numeric x) {
        if (x instanceof RealNum) {
            return times(this, ((RealNum) x).doubleValue());
        }
        throw new IllegalArgumentException();
    }

    public static double div(Duration dur1, Duration dur2) {
        int months1 = dur1.months;
        int months2 = dur2.months;
        double d = (double) dur1.seconds;
        int i = dur1.nanos;
        double d2 = (double) i;
        Double.isNaN(d2);
        Double.isNaN(d);
        double sec1 = d + (d2 * 1.0E-9d);
        double d3 = (double) dur2.seconds;
        double d4 = (double) i;
        Double.isNaN(d4);
        Double.isNaN(d3);
        double sec2 = d3 + (d4 * 1.0E-9d);
        if (months2 == 0 && sec2 == 0.0d) {
            throw new ArithmeticException("divide duration by zero");
        }
        if (months2 == 0) {
            if (months1 == 0) {
                return sec1 / sec2;
            }
        } else if (sec2 == 0.0d && sec1 == 0.0d) {
            double d5 = (double) months1;
            double d6 = (double) months2;
            Double.isNaN(d5);
            Double.isNaN(d6);
            return d5 / d6;
        }
        throw new ArithmeticException("divide of incompatible durations");
    }

    public Numeric div(Object y) {
        if (y instanceof RealNum) {
            double dy = ((RealNum) y).doubleValue();
            if (dy != 0.0d && !Double.isNaN(dy)) {
                return times(this, 1.0d / dy);
            }
            throw new ArithmeticException("divide of duration by 0 or NaN");
        } else if (y instanceof Duration) {
            return new DFloNum(div(this, (Duration) y));
        } else {
            return ((Numeric) y).divReversed(this);
        }
    }

    public static Duration add(Duration x, Duration y, int k) {
        long months2 = ((long) x.months) + (((long) k) * ((long) y.months));
        long nanos2 = (x.seconds * 1000000000) + ((long) x.nanos) + (((long) k) * ((y.seconds * 1000000000) + ((long) y.nanos)));
        Duration d = new Duration();
        d.months = (int) months2;
        d.seconds = (long) ((int) (nanos2 / 1000000000));
        d.nanos = (int) (nanos2 % 1000000000);
        Unit unit2 = x.unit;
        if (unit2 != y.unit || unit2 == Unit.duration) {
            throw new ArithmeticException("cannot add these duration types");
        }
        d.unit = x.unit;
        return d;
    }

    public static Duration times(Duration x, double y) {
        if (x.unit != Unit.duration) {
            double d = (double) x.months;
            Double.isNaN(d);
            double months2 = d * y;
            if (Double.isInfinite(months2) || Double.isNaN(months2)) {
                throw new ArithmeticException("overflow/NaN when multiplying a duration");
            }
            double d2 = (double) ((x.seconds * 1000000000) + ((long) x.nanos));
            Double.isNaN(d2);
            double nanos2 = d2 * y;
            Duration d3 = new Duration();
            d3.months = (int) Math.floor(0.5d + months2);
            d3.seconds = (long) ((int) (nanos2 / 1.0E9d));
            d3.nanos = (int) (nanos2 % 1.0E9d);
            d3.unit = x.unit;
            return d3;
        }
        throw new IllegalArgumentException("cannot multiply general duration");
    }

    public static int compare(Duration x, Duration y) {
        long months2 = ((long) x.months) - ((long) y.months);
        long nanos2 = ((x.seconds * 1000000000) + ((long) x.nanos)) - ((y.seconds * 1000000000) + ((long) y.nanos));
        if (months2 < 0 && nanos2 <= 0) {
            return -1;
        }
        if (months2 > 0 && nanos2 >= 0) {
            return 1;
        }
        if (months2 != 0) {
            return -2;
        }
        if (nanos2 < 0) {
            return -1;
        }
        return nanos2 > 0 ? 1 : 0;
    }

    public int compare(Object obj) {
        if (obj instanceof Duration) {
            return compare(this, (Duration) obj);
        }
        throw new IllegalArgumentException();
    }

    public String toString() {
        StringBuffer sbuf = new StringBuffer();
        int m = this.months;
        long s = this.seconds;
        int n = this.nanos;
        boolean neg = m < 0 || s < 0 || n < 0;
        if (neg) {
            m = -m;
            s = -s;
            n = -n;
            sbuf.append('-');
        }
        sbuf.append('P');
        int y = m / 12;
        if (y != 0) {
            sbuf.append(y);
            sbuf.append('Y');
            m -= y * 12;
        }
        if (m != 0) {
            sbuf.append(m);
            sbuf.append(Access.METHOD_CONTEXT);
        }
        long d = s / 86400;
        if (d != 0) {
            sbuf.append(d);
            sbuf.append('D');
            s -= 86400 * d;
        }
        if (s == 0 && n == 0) {
            if (sbuf.length() == 1) {
                sbuf.append(this.unit == Unit.month ? "0M" : "T0S");
            }
            boolean z = neg;
            int i = y;
        } else {
            sbuf.append('T');
            boolean z2 = neg;
            int i2 = y;
            long hr = s / 3600;
            if (hr != 0) {
                sbuf.append(hr);
                sbuf.append('H');
                s -= 3600 * hr;
            }
            long mn = s / 60;
            if (mn != 0) {
                sbuf.append(mn);
                sbuf.append(Access.METHOD_CONTEXT);
                s -= 60 * mn;
            }
            if (!(s == 0 && n == 0)) {
                sbuf.append(s);
                appendNanoSeconds(n, sbuf);
                sbuf.append('S');
            }
        }
        return sbuf.toString();
    }

    static void appendNanoSeconds(int nanoSeconds, StringBuffer sbuf) {
        if (nanoSeconds != 0) {
            sbuf.append('.');
            int pos = sbuf.length();
            sbuf.append(nanoSeconds);
            int pad = (pos + 9) - sbuf.length();
            while (true) {
                pad--;
                if (pad < 0) {
                    break;
                }
                sbuf.insert(pos, '0');
            }
            int len = pos + 9;
            do {
                len--;
            } while (sbuf.charAt(len) == '0');
            sbuf.setLength(len + 1);
        }
    }

    private static long scanPart(String str, int start) {
        int i = start;
        long val = -1;
        int len = str.length();
        while (i < len) {
            char ch = str.charAt(i);
            i++;
            int dig = Character.digit(ch, 10);
            if (dig >= 0) {
                val = val < 0 ? (long) dig : (10 * val) + ((long) dig);
                if (val > 2147483647L) {
                    return -1;
                }
            } else if (val < 0) {
                return (long) (start << 16);
            } else {
                return (val << 32) | ((long) (i << 16)) | ((long) ch);
            }
        }
        if (val < 0) {
            return (long) (start << 16);
        }
        return -1;
    }

    public int getYears() {
        return this.months / 12;
    }

    public int getMonths() {
        return this.months % 12;
    }

    public int getDays() {
        return (int) (this.seconds / 86400);
    }

    public int getHours() {
        return (int) ((this.seconds / 3600) % 24);
    }

    public int getMinutes() {
        return (int) ((this.seconds / 60) % 60);
    }

    public int getSecondsOnly() {
        return (int) (this.seconds % 60);
    }

    public int getNanoSecondsOnly() {
        return this.nanos;
    }

    public int getTotalMonths() {
        return this.months;
    }

    public long getTotalSeconds() {
        return this.seconds;
    }

    public long getTotalMinutes() {
        return this.seconds / 60;
    }

    public long getNanoSeconds() {
        return (this.seconds * 1000000000) + ((long) this.nanos);
    }

    public boolean isZero() {
        return this.months == 0 && this.seconds == 0 && this.nanos == 0;
    }

    public boolean isExact() {
        return false;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(this.months);
        out.writeLong(this.seconds);
        out.writeInt(this.nanos);
        out.writeObject(this.unit);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.months = in.readInt();
        this.seconds = in.readLong();
        this.nanos = in.readInt();
        this.unit = (Unit) in.readObject();
    }

    public Unit unit() {
        return this.unit;
    }

    public Complex number() {
        throw new Error("number needs to be implemented!");
    }

    public int hashCode() {
        return (this.months ^ ((int) this.seconds)) ^ this.nanos;
    }

    public static boolean equals(Duration x, Duration y) {
        return x.months == y.months && x.seconds == y.seconds && x.nanos == y.nanos;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Duration)) {
            return false;
        }
        return equals(this, (Duration) obj);
    }
}
