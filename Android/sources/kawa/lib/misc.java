package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.GenericProc;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Symbols;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.xml.KNode;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Path;
import kawa.Version;
import kawa.lang.Promise;
import kawa.standard.Scheme;
import kawa.standard.throw_name;

/* compiled from: misc.scm */
public class misc extends ModuleBody {
    public static final misc $instance;
    static final IntNum Lit0 = IntNum.make(4);
    static final IntNum Lit1 = IntNum.make(5);
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final Keyword Lit2 = Keyword.make("setter");
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final SimpleSymbol Lit27;
    static final SimpleSymbol Lit28;
    static final SimpleSymbol Lit3 = ((SimpleSymbol) new SimpleSymbol("misc-error").readResolve());
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod add$Mnprocedure$Mnproperties;
    public static final ModuleMethod base$Mnuri;
    public static final ModuleMethod boolean$Qu;
    public static final ModuleMethod dynamic$Mnwind;
    public static final ModuleMethod environment$Mnbound$Qu;
    public static final ModuleMethod error;
    public static final ModuleMethod force;
    public static final ModuleMethod gentemp;
    public static final ModuleMethod interaction$Mnenvironment;
    static final ModuleMethod lambda$Fn1;
    static final ModuleMethod lambda$Fn2;
    public static final ModuleMethod namespace$Mnprefix;
    public static final ModuleMethod namespace$Mnuri;
    public static final ModuleMethod null$Mnenvironment;
    public static final GenericProc procedure$Mnproperty = null;
    static final ModuleMethod procedure$Mnproperty$Fn3;
    public static final ModuleMethod procedure$Qu;
    public static final ModuleMethod scheme$Mnimplementation$Mnversion;
    public static final ModuleMethod scheme$Mnreport$Mnenvironment;
    public static final ModuleMethod set$Mnprocedure$Mnproperty$Ex;
    public static final ModuleMethod string$Mn$Grsymbol;
    public static final GenericProc symbol$Eq$Qu = null;
    public static final ModuleMethod symbol$Mn$Grstring;
    public static final ModuleMethod symbol$Mnlocal$Mnname;
    public static final ModuleMethod symbol$Mnnamespace;
    public static final ModuleMethod symbol$Mnnamespace$Mnuri;
    public static final ModuleMethod symbol$Mnprefix;
    public static final ModuleMethod symbol$Qu;
    public static final ModuleMethod values;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("add-procedure-properties").readResolve();
        Lit28 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("gentemp").readResolve();
        Lit27 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("base-uri").readResolve();
        Lit26 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("error").readResolve();
        Lit25 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("force").readResolve();
        Lit24 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("dynamic-wind").readResolve();
        Lit23 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("procedure-property").readResolve();
        Lit22 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("set-procedure-property!").readResolve();
        Lit21 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("scheme-implementation-version").readResolve();
        Lit20 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("interaction-environment").readResolve();
        Lit19 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("scheme-report-environment").readResolve();
        Lit18 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("null-environment").readResolve();
        Lit17 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("environment-bound?").readResolve();
        Lit16 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("values").readResolve();
        Lit15 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("procedure?").readResolve();
        Lit14 = simpleSymbol15;
        SimpleSymbol simpleSymbol16 = simpleSymbol;
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol("string->symbol").readResolve();
        Lit13 = simpleSymbol17;
        SimpleSymbol simpleSymbol18 = simpleSymbol2;
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("namespace-prefix").readResolve();
        Lit12 = simpleSymbol19;
        SimpleSymbol simpleSymbol20 = simpleSymbol3;
        SimpleSymbol simpleSymbol21 = (SimpleSymbol) new SimpleSymbol("namespace-uri").readResolve();
        Lit11 = simpleSymbol21;
        SimpleSymbol simpleSymbol22 = simpleSymbol4;
        SimpleSymbol simpleSymbol23 = (SimpleSymbol) new SimpleSymbol("symbol-prefix").readResolve();
        Lit10 = simpleSymbol23;
        SimpleSymbol simpleSymbol24 = simpleSymbol5;
        SimpleSymbol simpleSymbol25 = (SimpleSymbol) new SimpleSymbol("symbol-namespace-uri").readResolve();
        Lit9 = simpleSymbol25;
        SimpleSymbol simpleSymbol26 = simpleSymbol6;
        SimpleSymbol simpleSymbol27 = (SimpleSymbol) new SimpleSymbol("symbol-namespace").readResolve();
        Lit8 = simpleSymbol27;
        SimpleSymbol simpleSymbol28 = simpleSymbol7;
        SimpleSymbol simpleSymbol29 = (SimpleSymbol) new SimpleSymbol("symbol-local-name").readResolve();
        Lit7 = simpleSymbol29;
        SimpleSymbol simpleSymbol30 = simpleSymbol8;
        SimpleSymbol simpleSymbol31 = (SimpleSymbol) new SimpleSymbol("symbol->string").readResolve();
        Lit6 = simpleSymbol31;
        SimpleSymbol simpleSymbol32 = simpleSymbol9;
        SimpleSymbol simpleSymbol33 = (SimpleSymbol) new SimpleSymbol("symbol?").readResolve();
        Lit5 = simpleSymbol33;
        SimpleSymbol simpleSymbol34 = simpleSymbol10;
        SimpleSymbol simpleSymbol35 = (SimpleSymbol) new SimpleSymbol("boolean?").readResolve();
        Lit4 = simpleSymbol35;
        SimpleSymbol simpleSymbol36 = simpleSymbol11;
        misc misc = new misc();
        $instance = misc;
        boolean$Qu = new ModuleMethod(misc, 3, simpleSymbol35, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        symbol$Qu = new ModuleMethod(misc, 4, simpleSymbol33, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        symbol$Mn$Grstring = new ModuleMethod(misc, 5, simpleSymbol31, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod = new ModuleMethod(misc, 6, (Object) null, 8194);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/misc.scm:25");
        lambda$Fn1 = moduleMethod;
        ModuleMethod moduleMethod2 = new ModuleMethod(misc, 7, (Object) null, -4094);
        moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/misc.scm:27");
        lambda$Fn2 = moduleMethod2;
        symbol$Mnlocal$Mnname = new ModuleMethod(misc, 8, simpleSymbol29, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        symbol$Mnnamespace = new ModuleMethod(misc, 9, simpleSymbol27, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        symbol$Mnnamespace$Mnuri = new ModuleMethod(misc, 10, simpleSymbol25, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        symbol$Mnprefix = new ModuleMethod(misc, 11, simpleSymbol23, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        namespace$Mnuri = new ModuleMethod(misc, 12, simpleSymbol21, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        namespace$Mnprefix = new ModuleMethod(misc, 13, simpleSymbol19, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mn$Grsymbol = new ModuleMethod(misc, 14, simpleSymbol17, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        procedure$Qu = new ModuleMethod(misc, 15, simpleSymbol15, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        values = new ModuleMethod(misc, 16, simpleSymbol14, -4096);
        environment$Mnbound$Qu = new ModuleMethod(misc, 17, simpleSymbol13, 8194);
        null$Mnenvironment = new ModuleMethod(misc, 18, simpleSymbol12, 4096);
        scheme$Mnreport$Mnenvironment = new ModuleMethod(misc, 20, simpleSymbol36, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        interaction$Mnenvironment = new ModuleMethod(misc, 21, simpleSymbol34, 0);
        scheme$Mnimplementation$Mnversion = new ModuleMethod(misc, 22, simpleSymbol32, 0);
        set$Mnprocedure$Mnproperty$Ex = new ModuleMethod(misc, 23, simpleSymbol30, 12291);
        procedure$Mnproperty$Fn3 = new ModuleMethod(misc, 24, simpleSymbol28, 12290);
        dynamic$Mnwind = new ModuleMethod(misc, 26, simpleSymbol26, 12291);
        force = new ModuleMethod(misc, 27, simpleSymbol24, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        error = new ModuleMethod(misc, 28, simpleSymbol22, -4095);
        base$Mnuri = new ModuleMethod(misc, 29, simpleSymbol20, 4096);
        gentemp = new ModuleMethod(misc, 31, simpleSymbol18, 0);
        add$Mnprocedure$Mnproperties = new ModuleMethod(misc, 32, simpleSymbol16, -4095);
        misc.run();
    }

    public misc() {
        ModuleInfo.register(this);
    }

    public static Object baseUri() {
        return baseUri((Object) null);
    }

    public static Environment nullEnvironment() {
        return nullEnvironment(Boolean.FALSE);
    }

    public static Object procedureProperty(Procedure procedure, Object obj) {
        return procedureProperty(procedure, obj, Boolean.FALSE);
    }

    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
        GenericProc genericProc = new GenericProc("symbol=?");
        symbol$Eq$Qu = genericProc;
        genericProc.setProperties(new Object[]{lambda$Fn1, lambda$Fn2});
        GenericProc genericProc2 = new GenericProc("procedure-property");
        procedure$Mnproperty = genericProc2;
        Procedure procedure = procedure$Mnproperty$Fn3;
        Procedure procedure$Mnproperty2 = procedure;
        genericProc2.setProperties(new Object[]{Lit2, set$Mnprocedure$Mnproperty$Ex, procedure});
    }

    public static boolean isBoolean(Object x) {
        boolean x2 = x == Boolean.TRUE;
        if (x2) {
            return x2;
        }
        return x == Boolean.FALSE;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 3:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 4:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 5:
                if (!(obj instanceof Symbol)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 8:
                if (!(obj instanceof Symbol)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 9:
                if (!(obj instanceof Symbol)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 10:
                if (!(obj instanceof Symbol)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 11:
                if (!(obj instanceof Symbol)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 12:
                if (!(obj instanceof Namespace)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 13:
                if (!(obj instanceof Namespace)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 14:
                if (!(obj instanceof CharSequence)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 15:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 18:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 20:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 27:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 29:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod, obj, callContext);
        }
    }

    public static boolean isSymbol(Object x) {
        return x instanceof Symbol;
    }

    public static String symbol$To$String(Symbol s) {
        return s.toString();
    }

    static boolean lambda1(Symbol s1, Symbol s2) {
        return Symbol.equals(s1, s2);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 6:
                if (!(obj instanceof Symbol)) {
                    return -786431;
                }
                callContext.value1 = obj;
                if (!(obj2 instanceof Symbol)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 17:
                if (!(obj instanceof Environment)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 24:
                if (!(obj instanceof Procedure)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod, obj, obj2, callContext);
        }
    }

    static boolean lambda2$V(Symbol symbol, Symbol symbol2, Object[] objArr) {
        boolean z = false;
        LList makeList = LList.makeList(objArr, 0);
        boolean equals = Symbol.equals(symbol, symbol2);
        if (!equals) {
            return equals;
        }
        if (Scheme.apply.apply3(symbol$Eq$Qu, symbol2, makeList) != Boolean.FALSE) {
            z = true;
        }
        return z;
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 7:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 16:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 28:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 32:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod, objArr, callContext);
        }
    }

    public static String symbolLocalName(Symbol s) {
        return s.getLocalPart();
    }

    public static Namespace symbolNamespace(Symbol s) {
        return s.getNamespace();
    }

    public static String symbolNamespaceUri(Symbol s) {
        return s.getNamespaceURI();
    }

    public static String symbolPrefix(Symbol s) {
        return s.getPrefix();
    }

    public static CharSequence namespaceUri(Namespace ns) {
        return ns.getName();
    }

    public static CharSequence namespacePrefix(Namespace ns) {
        return ns.getPrefix();
    }

    public static SimpleSymbol string$To$Symbol(CharSequence str) {
        return SimpleSymbol.valueOf(str.toString());
    }

    public static boolean isProcedure(Object x) {
        boolean x2 = x instanceof Procedure;
        return x2 ? x2 : x instanceof LangObjType;
    }

    public static Object values(Object... args) {
        return Values.make(args);
    }

    public static boolean isEnvironmentBound(Environment env, Object sym) {
        return env.isBound(LispLanguage.langSymbolToSymbol(sym));
    }

    public static Environment nullEnvironment(Object version) {
        return Scheme.nullEnvironment;
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 18:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 21:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 22:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 29:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            case 31:
                callContext.proc = moduleMethod;
                callContext.pc = 0;
                return 0;
            default:
                return super.match0(moduleMethod, callContext);
        }
    }

    public static Object schemeReportEnvironment(Object version) {
        if (Scheme.isEqv.apply2(version, Lit0) != Boolean.FALSE) {
            return Scheme.r4Environment;
        }
        if (Scheme.isEqv.apply2(version, Lit1) != Boolean.FALSE) {
            return Scheme.r5Environment;
        }
        return error$V("scheme-report-environment version must be 4 or 5", new Object[0]);
    }

    public static Environment interactionEnvironment() {
        return Environment.user();
    }

    public static String schemeImplementationVersion() {
        return Version.getVersion();
    }

    public static void setProcedureProperty$Ex(Procedure proc, Object key, Object value) {
        proc.setProperty(key, value);
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 23:
                if (!(obj instanceof Procedure)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 24:
                if (!(obj instanceof Procedure)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            case 26:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            default:
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
        }
    }

    public static Object procedureProperty(Procedure proc, Object key, Object obj) {
        return proc.getProperty(key, obj);
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        switch (moduleMethod.selector) {
            case 6:
                try {
                    try {
                        return lambda1((Symbol) obj, (Symbol) obj2) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "lambda", 2, obj2);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "lambda", 1, obj);
                }
            case 17:
                try {
                    return isEnvironmentBound((Environment) obj, obj2) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "environment-bound?", 1, obj);
                }
            case 24:
                try {
                    return procedureProperty((Procedure) obj, obj2);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "procedure-property", 1, obj);
                }
            default:
                return super.apply2(moduleMethod, obj, obj2);
        }
    }

    /* JADX INFO: finally extract failed */
    public static Object dynamicWind(Object before, Object thunk, Object after) {
        Scheme.applyToArgs.apply1(before);
        try {
            Object apply1 = Scheme.applyToArgs.apply1(thunk);
            Scheme.applyToArgs.apply1(after);
            return apply1;
        } catch (Throwable th) {
            Scheme.applyToArgs.apply1(after);
            throw th;
        }
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        switch (moduleMethod.selector) {
            case 23:
                try {
                    setProcedureProperty$Ex((Procedure) obj, obj2, obj3);
                    return Values.empty;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "set-procedure-property!", 1, obj);
                }
            case 24:
                try {
                    return procedureProperty((Procedure) obj, obj2, obj3);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "procedure-property", 1, obj);
                }
            case 26:
                return dynamicWind(obj, obj2, obj3);
            default:
                return super.apply3(moduleMethod, obj, obj2, obj3);
        }
    }

    public static Object force(Object arg) {
        return Promise.force(arg);
    }

    /* compiled from: misc.scm */
    public class frame extends ModuleBody {
        final ModuleMethod lambda$Fn4;
        Object msg;

        public frame() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 2, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/misc.scm:104");
            this.lambda$Fn4 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector != 2) {
                return super.apply1(moduleMethod, obj);
            }
            lambda3(obj);
            return Values.empty;
        }

        /* access modifiers changed from: package-private */
        public void lambda3(Object port) {
            ports.display(this.msg, port);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 2) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object error$V(Object msg, Object[] argsArray) {
        frame frame2 = new frame();
        frame2.msg = msg;
        Object args = LList.makeList(argsArray, 0);
        frame2.msg = ports.callWithOutputString(frame2.lambda$Fn4);
        Object result = LList.Empty;
        Object arg0 = args;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                Object cdr = arg02.getCdr();
                Object arg = arg02.getCar();
                frame0 frame02 = new frame0();
                frame02.arg = arg;
                result = Pair.make(ports.callWithOutputString(frame02.lambda$Fn5), result);
                arg0 = cdr;
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, arg0);
            }
        }
        return Scheme.apply.apply4(throw_name.throwName, Lit3, frame2.msg, LList.reverseInPlace(result));
    }

    /* compiled from: misc.scm */
    public class frame0 extends ModuleBody {
        Object arg;
        final ModuleMethod lambda$Fn5;

        public frame0() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/misc.scm:107");
            this.lambda$Fn5 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector != 1) {
                return super.apply1(moduleMethod, obj);
            }
            lambda4(obj);
            return Values.empty;
        }

        /* access modifiers changed from: package-private */
        public void lambda4(Object port) {
            ports.write(this.arg, port);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 1) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object baseUri(Object node) {
        Path path;
        if (node == null) {
            path = Path.currentPath();
        } else {
            path = ((KNode) node).baseURI();
        }
        Path uri = path;
        return uri == Values.empty ? Boolean.FALSE : uri;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 3:
                return isBoolean(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 4:
                return isSymbol(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 5:
                try {
                    return symbol$To$String((Symbol) obj);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "symbol->string", 1, obj);
                }
            case 8:
                try {
                    return symbolLocalName((Symbol) obj);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "symbol-local-name", 1, obj);
                }
            case 9:
                try {
                    return symbolNamespace((Symbol) obj);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "symbol-namespace", 1, obj);
                }
            case 10:
                try {
                    return symbolNamespaceUri((Symbol) obj);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "symbol-namespace-uri", 1, obj);
                }
            case 11:
                try {
                    return symbolPrefix((Symbol) obj);
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "symbol-prefix", 1, obj);
                }
            case 12:
                try {
                    return namespaceUri((Namespace) obj);
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "namespace-uri", 1, obj);
                }
            case 13:
                try {
                    return namespacePrefix((Namespace) obj);
                } catch (ClassCastException e7) {
                    throw new WrongType(e7, "namespace-prefix", 1, obj);
                }
            case 14:
                try {
                    return string$To$Symbol((CharSequence) obj);
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "string->symbol", 1, obj);
                }
            case 15:
                return isProcedure(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 18:
                return nullEnvironment(obj);
            case 20:
                return schemeReportEnvironment(obj);
            case 27:
                return force(obj);
            case 29:
                return baseUri(obj);
            default:
                return super.apply1(moduleMethod, obj);
        }
    }

    public static Symbol gentemp() {
        return Symbols.gentemp();
    }

    public Object apply0(ModuleMethod moduleMethod) {
        switch (moduleMethod.selector) {
            case 18:
                return nullEnvironment();
            case 21:
                return interactionEnvironment();
            case 22:
                return schemeImplementationVersion();
            case 29:
                return baseUri();
            case 31:
                return gentemp();
            default:
                return super.apply0(moduleMethod);
        }
    }

    public static void addProcedureProperties(GenericProc proc, Object... args) {
        proc.setProperties(args);
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        switch (moduleMethod.selector) {
            case 7:
                Symbol symbol = objArr[0];
                try {
                    Symbol symbol2 = symbol;
                    Symbol symbol3 = objArr[1];
                    try {
                        Symbol symbol4 = symbol3;
                        int length = objArr.length - 2;
                        Object[] objArr2 = new Object[length];
                        while (true) {
                            length--;
                            if (length < 0) {
                                return lambda2$V(symbol2, symbol4, objArr2) ? Boolean.TRUE : Boolean.FALSE;
                            }
                            objArr2[length] = objArr[length + 2];
                        }
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "lambda", 2, (Object) symbol3);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "lambda", 1, (Object) symbol);
                }
            case 16:
                return values(objArr);
            case 28:
                Object obj = objArr[0];
                int length2 = objArr.length - 1;
                Object[] objArr3 = new Object[length2];
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        return error$V(obj, objArr3);
                    }
                    objArr3[length2] = objArr[length2 + 1];
                }
            case 32:
                GenericProc genericProc = objArr[0];
                try {
                    GenericProc genericProc2 = genericProc;
                    int length3 = objArr.length - 1;
                    Object[] objArr4 = new Object[length3];
                    while (true) {
                        length3--;
                        if (length3 < 0) {
                            addProcedureProperties(genericProc2, objArr4);
                            return Values.empty;
                        }
                        objArr4[length3] = objArr[length3 + 1];
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "add-procedure-properties", 1, (Object) genericProc);
                }
            default:
                return super.applyN(moduleMethod, objArr);
        }
    }
}
