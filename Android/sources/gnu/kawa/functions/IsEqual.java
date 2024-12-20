package gnu.kawa.functions;

import gnu.expr.Language;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure2;

public class IsEqual extends Procedure2 {
    Language language;

    public IsEqual(Language language2, String name) {
        this.language = language2;
        setName(name);
    }

    public static boolean numberEquals(Number num1, Number num2) {
        boolean exact1 = Arithmetic.isExact(num1);
        boolean exact2 = Arithmetic.isExact(num2);
        if (!exact1 || !exact2) {
            return exact1 == exact2 && num1.equals(num2);
        }
        return NumberCompare.$Eq(num1, num2);
    }

    public static boolean apply(Object arg1, Object arg2) {
        Object x1;
        Object x2;
        if (arg1 == arg2) {
            return true;
        }
        if (arg1 == null || arg2 == null) {
            return false;
        }
        if ((arg1 instanceof Number) && (arg2 instanceof Number)) {
            return numberEquals((Number) arg1, (Number) arg2);
        }
        if (arg1 instanceof CharSequence) {
            if (!(arg2 instanceof CharSequence)) {
                return false;
            }
            CharSequence seq1 = (CharSequence) arg1;
            CharSequence seq2 = (CharSequence) arg2;
            int len1 = seq1.length();
            if (len1 != seq2.length()) {
                return false;
            }
            int i = len1;
            do {
                i--;
                if (i < 0) {
                    return true;
                }
            } while (seq1.charAt(i) == seq2.charAt(i));
            return false;
        } else if (arg1 instanceof FVector) {
            if (!(arg2 instanceof FVector)) {
                return false;
            }
            FVector vec1 = (FVector) arg1;
            FVector vec2 = (FVector) arg2;
            int n = vec1.size;
            if (vec2.data == null || vec2.size != n) {
                return false;
            }
            Object[] data1 = vec1.data;
            Object[] data2 = vec2.data;
            int i2 = n;
            do {
                i2--;
                if (i2 < 0) {
                    return true;
                }
            } while (apply(data1[i2], data2[i2]));
            return false;
        } else if (!(arg1 instanceof LList)) {
            return arg1.equals(arg2);
        } else {
            if (!(arg1 instanceof Pair) || !(arg2 instanceof Pair)) {
                return false;
            }
            Pair pair1 = (Pair) arg1;
            Object obj = arg2;
            while (true) {
                Pair pair2 = (Pair) obj;
                if (!apply(pair1.getCar(), pair2.getCar())) {
                    return false;
                }
                x1 = pair1.getCdr();
                x2 = pair2.getCdr();
                if (x1 == x2) {
                    return true;
                }
                if (x1 == null || x2 == null) {
                    return false;
                }
                if ((x1 instanceof Pair) && (x2 instanceof Pair)) {
                    pair1 = (Pair) x1;
                    obj = x2;
                }
            }
            return apply(x1, x2);
        }
    }

    public Object apply2(Object arg1, Object arg2) {
        return this.language.booleanObject(apply(arg1, arg2));
    }
}
