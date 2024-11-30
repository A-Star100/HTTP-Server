package androidx.core.util;

import com.android.tools.r8.annotations.SynthesizedClassV2;

public interface Predicate<T> {
    Predicate<T> and(Predicate<? super T> predicate);

    Predicate<T> negate();

    Predicate<T> or(Predicate<? super T> predicate);

    boolean test(T t);

    @SynthesizedClassV2(apiLevel = -2, kind = 10, versionHash = "3f190e5f9a80d0f7670193e4f99ebcadd7b320b2de7ca36da081fdb272840838")
    /* renamed from: androidx.core.util.Predicate$-CC  reason: invalid class name */
    public final /* synthetic */ class CC<T> {
        public static Predicate $default$and(Predicate _this, Predicate other) {
            other.getClass();
            return new Predicate$$ExternalSyntheticLambda4(_this, other);
        }

        public static /* synthetic */ boolean $private$lambda$and$0(Predicate _this, Predicate other, Object t) {
            return _this.test(t) && other.test(t);
        }

        public static Predicate $default$negate(Predicate _this) {
            return new Predicate$$ExternalSyntheticLambda5(_this);
        }

        public static /* synthetic */ boolean $private$lambda$negate$1(Predicate _this, Object t) {
            return !_this.test(t);
        }

        public static Predicate $default$or(Predicate _this, Predicate other) {
            other.getClass();
            return new Predicate$$ExternalSyntheticLambda1(_this, other);
        }

        public static /* synthetic */ boolean $private$lambda$or$2(Predicate _this, Predicate other, Object t) {
            return _this.test(t) || other.test(t);
        }

        public static <T> Predicate<T> isEqual(Object targetRef) {
            if (targetRef == null) {
                return new Predicate$$ExternalSyntheticLambda2();
            }
            return new Predicate$$ExternalSyntheticLambda3(targetRef);
        }

        public static <T> Predicate<T> not(Predicate<? super T> target) {
            target.getClass();
            return target.negate();
        }
    }
}
