package appinventor.ai_sonicforces207.p260_servidor_web;

import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.AppInventorCompatActivity;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.errors.StopBlocksExecution;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.RetValManager;
import com.google.appinventor.components.runtime.util.RuntimeErrorAlert;
import com.google.youngandroid.runtime;
import com.gordonlu.device.Device;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.reflect.SlotSet;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lang.Promise;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.require;
import kawa.standard.throw_name;

/* compiled from: Advanced.yail */
public class Advanced extends Form implements Runnable {
    public static Advanced Advanced;
    static final SimpleSymbol Lit0 = ((SimpleSymbol) new SimpleSymbol("Advanced").readResolve());
    static final SimpleSymbol Lit1 = ((SimpleSymbol) new SimpleSymbol("getMessage").readResolve());
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11 = ((SimpleSymbol) new SimpleSymbol("Sizing").readResolve());
    static final SimpleSymbol Lit12 = ((SimpleSymbol) new SimpleSymbol("Theme").readResolve());
    static final SimpleSymbol Lit13 = ((SimpleSymbol) new SimpleSymbol("Title").readResolve());
    static final FString Lit14 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit15 = ((SimpleSymbol) new SimpleSymbol("revealip").readResolve());
    static final SimpleSymbol Lit16 = ((SimpleSymbol) new SimpleSymbol("Text").readResolve());
    static final FString Lit17 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit18 = ((SimpleSymbol) new SimpleSymbol("Notifier1").readResolve());
    static final SimpleSymbol Lit19 = ((SimpleSymbol) new SimpleSymbol("ShowMessageDialog").readResolve());
    static final SimpleSymbol Lit2 = ((SimpleSymbol) new SimpleSymbol("*the-null-value*").readResolve());
    static final SimpleSymbol Lit20 = ((SimpleSymbol) new SimpleSymbol("Device1").readResolve());
    static final SimpleSymbol Lit21 = ((SimpleSymbol) new SimpleSymbol("GetIpAddress").readResolve());
    static final PairWithPosition Lit22;
    static final PairWithPosition Lit23;
    static final PairWithPosition Lit24;
    static final PairWithPosition Lit25;
    static final SimpleSymbol Lit26 = ((SimpleSymbol) new SimpleSymbol("revealip$Click").readResolve());
    static final SimpleSymbol Lit27 = ((SimpleSymbol) new SimpleSymbol("Click").readResolve());
    static final FString Lit28 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit29 = ((SimpleSymbol) new SimpleSymbol("revealapp_private").readResolve());
    static final SimpleSymbol Lit3 = ((SimpleSymbol) new SimpleSymbol("AlignHorizontal").readResolve());
    static final FString Lit30 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit31 = ((SimpleSymbol) new SimpleSymbol("ApplicationSpecificDirectory").readResolve());
    static final PairWithPosition Lit32;
    static final SimpleSymbol Lit33 = ((SimpleSymbol) new SimpleSymbol("revealapp_private$Click").readResolve());
    static final FString Lit34 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit35 = ((SimpleSymbol) new SimpleSymbol("revealapp_cache").readResolve());
    static final FString Lit36 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit37 = ((SimpleSymbol) new SimpleSymbol("CacheDirectory").readResolve());
    static final PairWithPosition Lit38;
    static final SimpleSymbol Lit39 = ((SimpleSymbol) new SimpleSymbol("revealapp_cache$Click").readResolve());
    static final IntNum Lit4 = IntNum.make(3);
    static final FString Lit40 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit41 = ((SimpleSymbol) new SimpleSymbol("emulator").readResolve());
    static final FString Lit42 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit43 = ((SimpleSymbol) new SimpleSymbol("IsEmulator").readResolve());
    static final PairWithPosition Lit44;
    static final PairWithPosition Lit45;
    static final PairWithPosition Lit46;
    static final SimpleSymbol Lit47 = ((SimpleSymbol) new SimpleSymbol("emulator$Click").readResolve());
    static final FString Lit48 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit49 = ((SimpleSymbol) new SimpleSymbol("Button1").readResolve());
    static final SimpleSymbol Lit5 = ((SimpleSymbol) new SimpleSymbol("number").readResolve());
    static final FString Lit50 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit51 = ((SimpleSymbol) new SimpleSymbol("Button1$Click").readResolve());
    static final FString Lit52 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit53 = ((SimpleSymbol) new SimpleSymbol("Label2").readResolve());
    static final SimpleSymbol Lit54 = ((SimpleSymbol) new SimpleSymbol("FontItalic").readResolve());
    static final SimpleSymbol Lit55 = ((SimpleSymbol) new SimpleSymbol("HTMLFormat").readResolve());
    static final FString Lit56 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit57 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit58 = ((SimpleSymbol) new SimpleSymbol("Label3").readResolve());
    static final FString Lit59 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit6 = ((SimpleSymbol) new SimpleSymbol("AppName").readResolve());
    static final FString Lit60 = new FString("com.gordonlu.device.Device");
    static final FString Lit61 = new FString("com.gordonlu.device.Device");
    static final FString Lit62 = new FString("com.google.appinventor.components.runtime.Notifier");
    static final FString Lit63 = new FString("com.google.appinventor.components.runtime.Notifier");
    static final SimpleSymbol Lit64 = ((SimpleSymbol) new SimpleSymbol("get-simple-name").readResolve());
    static final SimpleSymbol Lit65 = ((SimpleSymbol) new SimpleSymbol("android-log-form").readResolve());
    static final SimpleSymbol Lit66 = ((SimpleSymbol) new SimpleSymbol("add-to-form-environment").readResolve());
    static final SimpleSymbol Lit67 = ((SimpleSymbol) new SimpleSymbol("lookup-in-form-environment").readResolve());
    static final SimpleSymbol Lit68 = ((SimpleSymbol) new SimpleSymbol("is-bound-in-form-environment").readResolve());
    static final SimpleSymbol Lit69 = ((SimpleSymbol) new SimpleSymbol("add-to-global-var-environment").readResolve());
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit70 = ((SimpleSymbol) new SimpleSymbol("add-to-events").readResolve());
    static final SimpleSymbol Lit71 = ((SimpleSymbol) new SimpleSymbol("add-to-components").readResolve());
    static final SimpleSymbol Lit72 = ((SimpleSymbol) new SimpleSymbol("add-to-global-vars").readResolve());
    static final SimpleSymbol Lit73 = ((SimpleSymbol) new SimpleSymbol("add-to-form-do-after-creation").readResolve());
    static final SimpleSymbol Lit74 = ((SimpleSymbol) new SimpleSymbol("send-error").readResolve());
    static final SimpleSymbol Lit75 = ((SimpleSymbol) new SimpleSymbol("dispatchEvent").readResolve());
    static final SimpleSymbol Lit76 = ((SimpleSymbol) new SimpleSymbol("dispatchGenericEvent").readResolve());
    static final SimpleSymbol Lit77 = ((SimpleSymbol) new SimpleSymbol("lookup-handler").readResolve());
    static final SimpleSymbol Lit78;
    static final SimpleSymbol Lit8 = ((SimpleSymbol) new SimpleSymbol("ScreenOrientation").readResolve());
    static final SimpleSymbol Lit9 = ((SimpleSymbol) new SimpleSymbol("ShowListsAsJson").readResolve());
    static final ModuleMethod lambda$Fn1 = null;
    static final ModuleMethod lambda$Fn10 = null;
    static final ModuleMethod lambda$Fn11 = null;
    static final ModuleMethod lambda$Fn12 = null;
    static final ModuleMethod lambda$Fn13 = null;
    static final ModuleMethod lambda$Fn14 = null;
    static final ModuleMethod lambda$Fn15 = null;
    static final ModuleMethod lambda$Fn16 = null;
    static final ModuleMethod lambda$Fn2 = null;
    static final ModuleMethod lambda$Fn3 = null;
    static final ModuleMethod lambda$Fn4 = null;
    static final ModuleMethod lambda$Fn5 = null;
    static final ModuleMethod lambda$Fn6 = null;
    static final ModuleMethod lambda$Fn7 = null;
    static final ModuleMethod lambda$Fn8 = null;
    static final ModuleMethod lambda$Fn9 = null;
    public Boolean $Stdebug$Mnform$St;
    public final ModuleMethod $define;
    public Button Button1;
    public final ModuleMethod Button1$Click;
    public Device Device1;
    public Label Label2;
    public Label Label3;
    public Notifier Notifier1;
    public final ModuleMethod add$Mnto$Mncomponents;
    public final ModuleMethod add$Mnto$Mnevents;
    public final ModuleMethod add$Mnto$Mnform$Mndo$Mnafter$Mncreation;
    public final ModuleMethod add$Mnto$Mnform$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvar$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvars;
    public final ModuleMethod android$Mnlog$Mnform;
    public LList components$Mnto$Mncreate;
    public final ModuleMethod dispatchEvent;
    public final ModuleMethod dispatchGenericEvent;
    public Button emulator;
    public final ModuleMethod emulator$Click;
    public LList events$Mnto$Mnregister;
    public LList form$Mndo$Mnafter$Mncreation;
    public Environment form$Mnenvironment;
    public Symbol form$Mnname$Mnsymbol;
    public final ModuleMethod get$Mnsimple$Mnname;
    public Environment global$Mnvar$Mnenvironment;
    public LList global$Mnvars$Mnto$Mncreate;
    public final ModuleMethod is$Mnbound$Mnin$Mnform$Mnenvironment;
    public final ModuleMethod lookup$Mnhandler;
    public final ModuleMethod lookup$Mnin$Mnform$Mnenvironment;
    public final ModuleMethod onCreate;
    public final ModuleMethod process$Mnexception;
    public Button revealapp_cache;
    public final ModuleMethod revealapp_cache$Click;
    public Button revealapp_private;
    public final ModuleMethod revealapp_private$Click;
    public Button revealip;
    public final ModuleMethod revealip$Click;
    public final ModuleMethod send$Mnerror;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("any").readResolve();
        Lit78 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_TEXT).readResolve();
        Lit7 = simpleSymbol2;
        Lit46 = PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, LList.Empty, "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 221713), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 221708), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 221702);
        Lit45 = PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, LList.Empty, "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 221554), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 221549), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 221543);
        Lit44 = PairWithPosition.make(simpleSymbol, PairWithPosition.make(simpleSymbol, LList.Empty, "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 221326), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 221321);
        Lit38 = PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, LList.Empty, "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 184568), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 184563), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 184557);
        Lit32 = PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, LList.Empty, "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 147718), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 147713), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 147707);
        Lit25 = PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, LList.Empty, "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 110992), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 110987), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 110981);
        Lit24 = PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, LList.Empty, "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 110943), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 110938), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 110933), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 110927);
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN).readResolve();
        Lit10 = simpleSymbol3;
        Lit23 = PairWithPosition.make(simpleSymbol3, LList.Empty, "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 110913);
        Lit22 = PairWithPosition.make(simpleSymbol3, LList.Empty, "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Advanced.yail", 110813);
    }

    public Advanced() {
        ModuleInfo.register(this);
        frame frame2 = new frame();
        frame2.$main = this;
        this.get$Mnsimple$Mnname = new ModuleMethod(frame2, 1, Lit64, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.onCreate = new ModuleMethod(frame2, 2, "onCreate", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.android$Mnlog$Mnform = new ModuleMethod(frame2, 3, Lit65, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnform$Mnenvironment = new ModuleMethod(frame2, 4, Lit66, 8194);
        this.lookup$Mnin$Mnform$Mnenvironment = new ModuleMethod(frame2, 5, Lit67, 8193);
        this.is$Mnbound$Mnin$Mnform$Mnenvironment = new ModuleMethod(frame2, 7, Lit68, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnglobal$Mnvar$Mnenvironment = new ModuleMethod(frame2, 8, Lit69, 8194);
        this.add$Mnto$Mnevents = new ModuleMethod(frame2, 9, Lit70, 8194);
        this.add$Mnto$Mncomponents = new ModuleMethod(frame2, 10, Lit71, 16388);
        this.add$Mnto$Mnglobal$Mnvars = new ModuleMethod(frame2, 11, Lit72, 8194);
        this.add$Mnto$Mnform$Mndo$Mnafter$Mncreation = new ModuleMethod(frame2, 12, Lit73, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.send$Mnerror = new ModuleMethod(frame2, 13, Lit74, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.process$Mnexception = new ModuleMethod(frame2, 14, "process-exception", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.dispatchEvent = new ModuleMethod(frame2, 15, Lit75, 16388);
        this.dispatchGenericEvent = new ModuleMethod(frame2, 16, Lit76, 16388);
        this.lookup$Mnhandler = new ModuleMethod(frame2, 17, Lit77, 8194);
        ModuleMethod moduleMethod = new ModuleMethod(frame2, 18, (Object) null, 0);
        moduleMethod.setProperty("source-location", "/tmp/runtime1488391760221600366.scm:634");
        lambda$Fn1 = moduleMethod;
        this.$define = new ModuleMethod(frame2, 19, "$define", 0);
        lambda$Fn2 = new ModuleMethod(frame2, 20, (Object) null, 0);
        lambda$Fn3 = new ModuleMethod(frame2, 21, (Object) null, 0);
        lambda$Fn4 = new ModuleMethod(frame2, 22, (Object) null, 0);
        this.revealip$Click = new ModuleMethod(frame2, 23, Lit26, 0);
        lambda$Fn5 = new ModuleMethod(frame2, 24, (Object) null, 0);
        lambda$Fn6 = new ModuleMethod(frame2, 25, (Object) null, 0);
        this.revealapp_private$Click = new ModuleMethod(frame2, 26, Lit33, 0);
        lambda$Fn7 = new ModuleMethod(frame2, 27, (Object) null, 0);
        lambda$Fn8 = new ModuleMethod(frame2, 28, (Object) null, 0);
        this.revealapp_cache$Click = new ModuleMethod(frame2, 29, Lit39, 0);
        lambda$Fn9 = new ModuleMethod(frame2, 30, (Object) null, 0);
        lambda$Fn10 = new ModuleMethod(frame2, 31, (Object) null, 0);
        this.emulator$Click = new ModuleMethod(frame2, 32, Lit47, 0);
        lambda$Fn11 = new ModuleMethod(frame2, 33, (Object) null, 0);
        lambda$Fn12 = new ModuleMethod(frame2, 34, (Object) null, 0);
        this.Button1$Click = new ModuleMethod(frame2, 35, Lit51, 0);
        lambda$Fn13 = new ModuleMethod(frame2, 36, (Object) null, 0);
        lambda$Fn14 = new ModuleMethod(frame2, 37, (Object) null, 0);
        lambda$Fn15 = new ModuleMethod(frame2, 38, (Object) null, 0);
        lambda$Fn16 = new ModuleMethod(frame2, 39, (Object) null, 0);
    }

    public Object lookupInFormEnvironment(Symbol symbol) {
        return lookupInFormEnvironment(symbol, Boolean.FALSE);
    }

    public void run() {
        CallContext instance = CallContext.getInstance();
        Consumer consumer = instance.consumer;
        instance.consumer = VoidConsumer.instance;
        try {
            run(instance);
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        ModuleBody.runCleanup(instance, th, consumer);
    }

    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
        Object find = require.find("com.google.youngandroid.runtime");
        try {
            ((Runnable) find).run();
            this.$Stdebug$Mnform$St = Boolean.FALSE;
            SimpleSymbol simpleSymbol = Lit0;
            this.form$Mnenvironment = Environment.make(misc.symbol$To$String(simpleSymbol));
            FString stringAppend = strings.stringAppend(misc.symbol$To$String(simpleSymbol), "-global-vars");
            this.global$Mnvar$Mnenvironment = Environment.make(stringAppend == null ? null : stringAppend.toString());
            Advanced = null;
            this.form$Mnname$Mnsymbol = simpleSymbol;
            this.events$Mnto$Mnregister = LList.Empty;
            this.components$Mnto$Mncreate = LList.Empty;
            this.global$Mnvars$Mnto$Mncreate = LList.Empty;
            this.form$Mndo$Mnafter$Mncreation = LList.Empty;
            Object find2 = require.find("com.google.youngandroid.runtime");
            try {
                ((Runnable) find2).run();
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit3, Lit4, Lit5);
                    SimpleSymbol simpleSymbol2 = Lit6;
                    SimpleSymbol simpleSymbol3 = Lit7;
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, "p260_servidor_web", simpleSymbol3);
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit8, "unspecified", simpleSymbol3);
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit9, Boolean.FALSE, Lit10);
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit11, "Responsive", simpleSymbol3);
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit12, "Classic", simpleSymbol3);
                    Values.writeValues(runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit13, "Advanced", simpleSymbol3), $result);
                } else {
                    addToFormDoAfterCreation(new Promise(lambda$Fn2));
                }
                this.revealip = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit14, Lit15, lambda$Fn3), $result);
                } else {
                    addToComponents(simpleSymbol, Lit17, Lit15, lambda$Fn4);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    runtime.addToCurrentFormEnvironment(Lit26, this.revealip$Click);
                } else {
                    addToFormEnvironment(Lit26, this.revealip$Click);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "revealip", "Click");
                } else {
                    addToEvents(Lit15, Lit27);
                }
                this.revealapp_private = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit28, Lit29, lambda$Fn5), $result);
                } else {
                    addToComponents(simpleSymbol, Lit30, Lit29, lambda$Fn6);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    runtime.addToCurrentFormEnvironment(Lit33, this.revealapp_private$Click);
                } else {
                    addToFormEnvironment(Lit33, this.revealapp_private$Click);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "revealapp_private", "Click");
                } else {
                    addToEvents(Lit29, Lit27);
                }
                this.revealapp_cache = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit34, Lit35, lambda$Fn7), $result);
                } else {
                    addToComponents(simpleSymbol, Lit36, Lit35, lambda$Fn8);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    runtime.addToCurrentFormEnvironment(Lit39, this.revealapp_cache$Click);
                } else {
                    addToFormEnvironment(Lit39, this.revealapp_cache$Click);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "revealapp_cache", "Click");
                } else {
                    addToEvents(Lit35, Lit27);
                }
                this.emulator = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit40, Lit41, lambda$Fn9), $result);
                } else {
                    addToComponents(simpleSymbol, Lit42, Lit41, lambda$Fn10);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    runtime.addToCurrentFormEnvironment(Lit47, this.emulator$Click);
                } else {
                    addToFormEnvironment(Lit47, this.emulator$Click);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "emulator", "Click");
                } else {
                    addToEvents(Lit41, Lit27);
                }
                this.Button1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit48, Lit49, lambda$Fn11), $result);
                } else {
                    addToComponents(simpleSymbol, Lit50, Lit49, lambda$Fn12);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    runtime.addToCurrentFormEnvironment(Lit51, this.Button1$Click);
                } else {
                    addToFormEnvironment(Lit51, this.Button1$Click);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "Button1", "Click");
                } else {
                    addToEvents(Lit49, Lit27);
                }
                this.Label2 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit52, Lit53, lambda$Fn13), $result);
                } else {
                    addToComponents(simpleSymbol, Lit56, Lit53, lambda$Fn14);
                }
                this.Label3 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit57, Lit58, lambda$Fn15), $result);
                } else {
                    addToComponents(simpleSymbol, Lit59, Lit58, lambda$Fn16);
                }
                this.Device1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit60, Lit20, Boolean.FALSE), $result);
                } else {
                    addToComponents(simpleSymbol, Lit61, Lit20, Boolean.FALSE);
                }
                this.Notifier1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit62, Lit18, Boolean.FALSE), $result);
                } else {
                    addToComponents(simpleSymbol, Lit63, Lit18, Boolean.FALSE);
                }
                runtime.initRuntime();
            } catch (ClassCastException e) {
                throw new WrongType(e, "java.lang.Runnable.run()", 1, find2);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "java.lang.Runnable.run()", 1, find);
        }
    }

    static Object lambda3() {
        SimpleSymbol simpleSymbol = Lit0;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit3, Lit4, Lit5);
        SimpleSymbol simpleSymbol2 = Lit6;
        SimpleSymbol simpleSymbol3 = Lit7;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, "p260_servidor_web", simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit8, "unspecified", simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit9, Boolean.FALSE, Lit10);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit11, "Responsive", simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit12, "Classic", simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit13, "Advanced", simpleSymbol3);
    }

    static Object lambda4() {
        return runtime.setAndCoerceProperty$Ex(Lit15, Lit16, "Reveal IPV4 and IPV6 address", Lit7);
    }

    static Object lambda5() {
        return runtime.setAndCoerceProperty$Ex(Lit15, Lit16, "Reveal IPV4 and IPV6 address", Lit7);
    }

    public Object revealip$Click() {
        runtime.setThisForm();
        SimpleSymbol simpleSymbol = Lit18;
        SimpleSymbol simpleSymbol2 = Lit19;
        ModuleMethod moduleMethod = strings.string$Mnappend;
        SimpleSymbol simpleSymbol3 = Lit20;
        SimpleSymbol simpleSymbol4 = Lit21;
        return runtime.callComponentMethod(simpleSymbol, simpleSymbol2, LList.list3(runtime.callYailPrimitive(moduleMethod, LList.list4("IPV4 address: ", runtime.callComponentMethod(simpleSymbol3, simpleSymbol4, LList.list1(Boolean.TRUE), Lit22), " IPV6 address: ", runtime.callComponentMethod(simpleSymbol3, simpleSymbol4, LList.list1(Boolean.FALSE), Lit23)), Lit24, "join"), "IP address:", "OK"), Lit25);
    }

    static Object lambda6() {
        return runtime.setAndCoerceProperty$Ex(Lit29, Lit16, "Reveal Application-Specific directory file path", Lit7);
    }

    static Object lambda7() {
        return runtime.setAndCoerceProperty$Ex(Lit29, Lit16, "Reveal Application-Specific directory file path", Lit7);
    }

    public Object revealapp_private$Click() {
        runtime.setThisForm();
        return runtime.callComponentMethod(Lit18, Lit19, LList.list3(runtime.callComponentMethod(Lit20, Lit31, LList.Empty, LList.Empty), "Application-specific directory file path (this will work in file browser)", "OK"), Lit32);
    }

    static Object lambda8() {
        return runtime.setAndCoerceProperty$Ex(Lit35, Lit16, "Reveal Application-Specific cache directory file path", Lit7);
    }

    static Object lambda9() {
        return runtime.setAndCoerceProperty$Ex(Lit35, Lit16, "Reveal Application-Specific cache directory file path", Lit7);
    }

    public Object revealapp_cache$Click() {
        runtime.setThisForm();
        return runtime.callComponentMethod(Lit18, Lit19, LList.list3(runtime.callComponentMethod(Lit20, Lit37, LList.Empty, LList.Empty), "Application-specific directory file path (this will work in file browser)", "OK"), Lit38);
    }

    static Object lambda10() {
        return runtime.setAndCoerceProperty$Ex(Lit41, Lit16, "Check if app is being run on emulator", Lit7);
    }

    static Object lambda11() {
        return runtime.setAndCoerceProperty$Ex(Lit41, Lit16, "Check if app is being run on emulator", Lit7);
    }

    public Object emulator$Click() {
        PairWithPosition pairWithPosition;
        Pair pair;
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        runtime.setThisForm();
        if (runtime.callYailPrimitive(runtime.yail$Mnequal$Qu, LList.list2(runtime.callComponentMethod(Lit20, Lit43, LList.Empty, LList.Empty), Boolean.TRUE), Lit44, "=") != Boolean.FALSE) {
            simpleSymbol2 = Lit18;
            simpleSymbol = Lit19;
            pair = LList.list3("True/Is an emulator (This means you may not be able to serve HTTP properly via this app)", "Emulator Check Results", "OK");
            pairWithPosition = Lit45;
        } else {
            simpleSymbol2 = Lit18;
            simpleSymbol = Lit19;
            pair = LList.list3("False/Not an emulator", "Emulator Check Results", "OK");
            pairWithPosition = Lit46;
        }
        return runtime.callComponentMethod(simpleSymbol2, simpleSymbol, pair, pairWithPosition);
    }

    static Object lambda12() {
        return runtime.setAndCoerceProperty$Ex(Lit49, Lit16, "Go back", Lit7);
    }

    static Object lambda13() {
        return runtime.setAndCoerceProperty$Ex(Lit49, Lit16, "Go back", Lit7);
    }

    public Object Button1$Click() {
        runtime.setThisForm();
        return runtime.callYailPrimitive(runtime.close$Mnscreen, LList.Empty, LList.Empty, "close screen");
    }

    static Object lambda14() {
        SimpleSymbol simpleSymbol = Lit53;
        SimpleSymbol simpleSymbol2 = Lit54;
        Boolean bool = Boolean.TRUE;
        SimpleSymbol simpleSymbol3 = Lit10;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, bool, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit55, Boolean.TRUE, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit16, "These features can help with compatibility issues and", Lit7);
    }

    static Object lambda15() {
        SimpleSymbol simpleSymbol = Lit53;
        SimpleSymbol simpleSymbol2 = Lit54;
        Boolean bool = Boolean.TRUE;
        SimpleSymbol simpleSymbol3 = Lit10;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, bool, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit55, Boolean.TRUE, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit16, "These features can help with compatibility issues and", Lit7);
    }

    static Object lambda16() {
        SimpleSymbol simpleSymbol = Lit58;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit54, Boolean.TRUE, Lit10);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit16, "and help you learn about Android apps as well.", Lit7);
    }

    static Object lambda17() {
        SimpleSymbol simpleSymbol = Lit58;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit54, Boolean.TRUE, Lit10);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit16, "and help you learn about Android apps as well.", Lit7);
    }

    /* compiled from: Advanced.yail */
    public class frame extends ModuleBody {
        Advanced $main;

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 1:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 2:
                    if (!(obj instanceof Advanced)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 3:
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
                case 7:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 12:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 13:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 14:
                    if (!(obj instanceof Advanced)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                default:
                    return super.match1(moduleMethod, obj, callContext);
            }
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 4:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 5:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 8:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 9:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 11:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 17:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                default:
                    return super.match2(moduleMethod, obj, obj2, callContext);
            }
        }

        public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 10:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.value4 = obj4;
                    callContext.proc = moduleMethod;
                    callContext.pc = 4;
                    return 0;
                case 15:
                    if (!(obj instanceof Advanced)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    if (!(obj2 instanceof Component)) {
                        return -786430;
                    }
                    callContext.value2 = obj2;
                    if (!(obj3 instanceof String)) {
                        return -786429;
                    }
                    callContext.value3 = obj3;
                    if (!(obj4 instanceof String)) {
                        return -786428;
                    }
                    callContext.value4 = obj4;
                    callContext.proc = moduleMethod;
                    callContext.pc = 4;
                    return 0;
                case 16:
                    if (!(obj instanceof Advanced)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    if (!(obj2 instanceof Component)) {
                        return -786430;
                    }
                    callContext.value2 = obj2;
                    if (!(obj3 instanceof String)) {
                        return -786429;
                    }
                    callContext.value3 = obj3;
                    callContext.value4 = obj4;
                    callContext.proc = moduleMethod;
                    callContext.pc = 4;
                    return 0;
                default:
                    return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
            }
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            switch (moduleMethod.selector) {
                case 1:
                    return this.$main.getSimpleName(obj);
                case 2:
                    try {
                        this.$main.onCreate((Bundle) obj);
                        return Values.empty;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "onCreate", 1, obj);
                    }
                case 3:
                    this.$main.androidLogForm(obj);
                    return Values.empty;
                case 5:
                    try {
                        return this.$main.lookupInFormEnvironment((Symbol) obj);
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "lookup-in-form-environment", 1, obj);
                    }
                case 7:
                    try {
                        return this.$main.isBoundInFormEnvironment((Symbol) obj) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "is-bound-in-form-environment", 1, obj);
                    }
                case 12:
                    this.$main.addToFormDoAfterCreation(obj);
                    return Values.empty;
                case 13:
                    this.$main.sendError(obj);
                    return Values.empty;
                case 14:
                    this.$main.processException(obj);
                    return Values.empty;
                default:
                    return super.apply1(moduleMethod, obj);
            }
        }

        public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
            boolean z = true;
            switch (moduleMethod.selector) {
                case 10:
                    this.$main.addToComponents(obj, obj2, obj3, obj4);
                    return Values.empty;
                case 15:
                    try {
                        try {
                            try {
                                try {
                                    return this.$main.dispatchEvent((Component) obj, (String) obj2, (String) obj3, (Object[]) obj4) ? Boolean.TRUE : Boolean.FALSE;
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "dispatchEvent", 4, obj4);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "dispatchEvent", 3, obj3);
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "dispatchEvent", 2, obj2);
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "dispatchEvent", 1, obj);
                    }
                case 16:
                    Advanced advanced = this.$main;
                    try {
                        Component component = (Component) obj;
                        try {
                            String str = (String) obj2;
                            try {
                                if (obj3 == Boolean.FALSE) {
                                    z = false;
                                }
                                try {
                                    advanced.dispatchGenericEvent(component, str, z, (Object[]) obj4);
                                    return Values.empty;
                                } catch (ClassCastException e5) {
                                    throw new WrongType(e5, "dispatchGenericEvent", 4, obj4);
                                }
                            } catch (ClassCastException e6) {
                                throw new WrongType(e6, "dispatchGenericEvent", 3, obj3);
                            }
                        } catch (ClassCastException e7) {
                            throw new WrongType(e7, "dispatchGenericEvent", 2, obj2);
                        }
                    } catch (ClassCastException e8) {
                        throw new WrongType(e8, "dispatchGenericEvent", 1, obj);
                    }
                default:
                    return super.apply4(moduleMethod, obj, obj2, obj3, obj4);
            }
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            switch (moduleMethod.selector) {
                case 4:
                    try {
                        this.$main.addToFormEnvironment((Symbol) obj, obj2);
                        return Values.empty;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "add-to-form-environment", 1, obj);
                    }
                case 5:
                    try {
                        return this.$main.lookupInFormEnvironment((Symbol) obj, obj2);
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "lookup-in-form-environment", 1, obj);
                    }
                case 8:
                    try {
                        this.$main.addToGlobalVarEnvironment((Symbol) obj, obj2);
                        return Values.empty;
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "add-to-global-var-environment", 1, obj);
                    }
                case 9:
                    this.$main.addToEvents(obj, obj2);
                    return Values.empty;
                case 11:
                    this.$main.addToGlobalVars(obj, obj2);
                    return Values.empty;
                case 17:
                    return this.$main.lookupHandler(obj, obj2);
                default:
                    return super.apply2(moduleMethod, obj, obj2);
            }
        }

        public Object apply0(ModuleMethod moduleMethod) {
            switch (moduleMethod.selector) {
                case 18:
                    return Advanced.lambda2();
                case 19:
                    this.$main.$define();
                    return Values.empty;
                case 20:
                    return Advanced.lambda3();
                case 21:
                    return Advanced.lambda4();
                case 22:
                    return Advanced.lambda5();
                case 23:
                    return this.$main.revealip$Click();
                case 24:
                    return Advanced.lambda6();
                case 25:
                    return Advanced.lambda7();
                case 26:
                    return this.$main.revealapp_private$Click();
                case 27:
                    return Advanced.lambda8();
                case 28:
                    return Advanced.lambda9();
                case 29:
                    return this.$main.revealapp_cache$Click();
                case 30:
                    return Advanced.lambda10();
                case 31:
                    return Advanced.lambda11();
                case 32:
                    return this.$main.emulator$Click();
                case 33:
                    return Advanced.lambda12();
                case 34:
                    return Advanced.lambda13();
                case 35:
                    return this.$main.Button1$Click();
                case 36:
                    return Advanced.lambda14();
                case 37:
                    return Advanced.lambda15();
                case 38:
                    return Advanced.lambda16();
                case 39:
                    return Advanced.lambda17();
                default:
                    return super.apply0(moduleMethod);
            }
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 18:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 19:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                default:
                    return super.match0(moduleMethod, callContext);
            }
        }
    }

    public String getSimpleName(Object object) {
        return object.getClass().getSimpleName();
    }

    public void onCreate(Bundle icicle) {
        AppInventorCompatActivity.setClassicModeFromYail(true);
        super.onCreate(icicle);
    }

    public void androidLogForm(Object message) {
    }

    public void addToFormEnvironment(Symbol name, Object object) {
        androidLogForm(Format.formatToString(0, "Adding ~A to env ~A with value ~A", name, this.form$Mnenvironment, object));
        this.form$Mnenvironment.put(name, object);
    }

    public Object lookupInFormEnvironment(Symbol name, Object default$Mnvalue) {
        Environment environment = this.form$Mnenvironment;
        boolean x = true & ((environment == null ? 1 : 0) + 1);
        if (!x ? !x : !environment.isBound(name)) {
            return default$Mnvalue;
        }
        return this.form$Mnenvironment.get(name);
    }

    public boolean isBoundInFormEnvironment(Symbol name) {
        return this.form$Mnenvironment.isBound(name);
    }

    public void addToGlobalVarEnvironment(Symbol name, Object object) {
        androidLogForm(Format.formatToString(0, "Adding ~A to env ~A with value ~A", name, this.global$Mnvar$Mnenvironment, object));
        this.global$Mnvar$Mnenvironment.put(name, object);
    }

    public void addToEvents(Object component$Mnname, Object event$Mnname) {
        this.events$Mnto$Mnregister = lists.cons(lists.cons(component$Mnname, event$Mnname), this.events$Mnto$Mnregister);
    }

    public void addToComponents(Object container$Mnname, Object component$Mntype, Object component$Mnname, Object init$Mnthunk) {
        this.components$Mnto$Mncreate = lists.cons(LList.list4(container$Mnname, component$Mntype, component$Mnname, init$Mnthunk), this.components$Mnto$Mncreate);
    }

    public void addToGlobalVars(Object var, Object val$Mnthunk) {
        this.global$Mnvars$Mnto$Mncreate = lists.cons(LList.list2(var, val$Mnthunk), this.global$Mnvars$Mnto$Mncreate);
    }

    public void addToFormDoAfterCreation(Object thunk) {
        this.form$Mndo$Mnafter$Mncreation = lists.cons(thunk, this.form$Mndo$Mnafter$Mncreation);
    }

    public void sendError(Object error) {
        RetValManager.sendError(error == null ? null : error.toString());
    }

    public void processException(Object ex) {
        Object apply1 = Scheme.applyToArgs.apply1(GetNamedPart.getNamedPart.apply2(ex, Lit1));
        RuntimeErrorAlert.alert(this, apply1 == null ? null : apply1.toString(), ex instanceof YailRuntimeError ? ((YailRuntimeError) ex).getErrorType() : "Runtime Error", "End Application");
    }

    public boolean dispatchEvent(Component componentObject, String registeredComponentName, String eventName, Object[] args) {
        SimpleSymbol registeredObject = misc.string$To$Symbol(registeredComponentName);
        if (!isBoundInFormEnvironment(registeredObject)) {
            EventDispatcher.unregisterEventForDelegation(this, registeredComponentName, eventName);
            return false;
        } else if (lookupInFormEnvironment(registeredObject) != componentObject) {
            return false;
        } else {
            boolean x = true;
            try {
                Scheme.apply.apply2(lookupHandler(registeredComponentName, eventName), LList.makeList(args, 0));
                return true;
            } catch (StopBlocksExecution e) {
                if (throw_name.throwName.apply1(e) != Boolean.FALSE) {
                    return true;
                }
                return false;
            } catch (PermissionException e2) {
                PermissionException exception = e2;
                exception.printStackTrace();
                if (this != componentObject) {
                    x = false;
                }
                if (!x ? !x : !IsEqual.apply(eventName, "PermissionNeeded")) {
                    PermissionDenied(componentObject, eventName, exception.getPermissionNeeded());
                    return false;
                }
                processException(exception);
                return false;
            } catch (Throwable th) {
                Throwable exception2 = th;
                androidLogForm(exception2.getMessage());
                exception2.printStackTrace();
                processException(exception2);
                return false;
            }
        }
    }

    public void dispatchGenericEvent(Component componentObject, String eventName, boolean notAlreadyHandled, Object[] args) {
        boolean x = false;
        Object handler = lookupInFormEnvironment(misc.string$To$Symbol(strings.stringAppend("any$", getSimpleName(componentObject), "$", eventName)));
        if (handler != Boolean.FALSE) {
            try {
                Scheme.apply.apply2(handler, lists.cons(componentObject, lists.cons(notAlreadyHandled ? Boolean.TRUE : Boolean.FALSE, LList.makeList(args, 0))));
            } catch (StopBlocksExecution e) {
                StopBlocksExecution exception = e;
            } catch (PermissionException e2) {
                PermissionException exception2 = e2;
                exception2.printStackTrace();
                if (this == componentObject) {
                    x = true;
                }
                if (!x ? !x : !IsEqual.apply(eventName, "PermissionNeeded")) {
                    PermissionDenied(componentObject, eventName, exception2.getPermissionNeeded());
                } else {
                    processException(exception2);
                }
            } catch (Throwable th) {
                Throwable exception3 = th;
                androidLogForm(exception3.getMessage());
                exception3.printStackTrace();
                processException(exception3);
            }
        }
    }

    public Object lookupHandler(Object componentName, Object eventName) {
        String str = null;
        String obj = componentName == null ? null : componentName.toString();
        if (eventName != null) {
            str = eventName.toString();
        }
        return lookupInFormEnvironment(misc.string$To$Symbol(EventDispatcher.makeFullEventName(obj, str)));
    }

    public void $define() {
        int i;
        Object arg0;
        Object arg02;
        Object arg03;
        WrongType wrongType;
        Object var;
        Object apply1;
        Object component$Mnname;
        Language.setDefaults(Scheme.getInstance());
        try {
            run();
        } catch (Exception exception) {
            Exception exception2 = exception;
            androidLogForm(exception2.getMessage());
            processException(exception2);
        }
        Advanced = this;
        addToFormEnvironment(Lit0, this);
        Object obj = this.events$Mnto$Mnregister;
        Object components = obj;
        Object arg04 = obj;
        while (true) {
            i = -2;
            if (arg04 == LList.Empty) {
                try {
                    break;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "arg0", -2, arg0);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "arg0", -2, arg02);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "lookup-in-form-environment", 0, apply1);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "add-to-form-environment", 0, component$Mnname);
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "arg0", i, arg03);
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "add-to-global-var-environment", 0, var);
                } catch (YailRuntimeError exception3) {
                    Object obj2 = components;
                    processException(exception3);
                    return;
                }
            } else {
                try {
                    Pair arg05 = (Pair) arg04;
                    Object event$Mninfo = arg05.getCar();
                    Object apply12 = lists.car.apply1(event$Mninfo);
                    String str = null;
                    String obj3 = apply12 == null ? null : apply12.toString();
                    Object apply13 = lists.cdr.apply1(event$Mninfo);
                    if (apply13 != null) {
                        str = apply13.toString();
                    }
                    EventDispatcher.registerEventForDelegation(this, obj3, str);
                    arg04 = arg05.getCdr();
                } catch (ClassCastException e7) {
                    throw new WrongType(e7, "arg0", -2, arg04);
                }
            }
        }
        components = lists.reverse(this.components$Mnto$Mncreate);
        addToGlobalVars(Lit2, lambda$Fn1);
        arg0 = lists.reverse(this.form$Mndo$Mnafter$Mncreation);
        while (arg0 != LList.Empty) {
            Pair arg06 = (Pair) arg0;
            misc.force(arg06.getCar());
            arg0 = arg06.getCdr();
            i = -2;
        }
        Object var$Mnval = null;
        arg02 = components;
        while (arg02 != LList.Empty) {
            Pair arg07 = (Pair) arg02;
            Object component$Mninfo = arg07.getCar();
            Object apply14 = lists.caddr.apply1(component$Mninfo);
            lists.cadddr.apply1(component$Mninfo);
            Object component$Mntype = lists.cadr.apply1(component$Mninfo);
            apply1 = lists.car.apply1(component$Mninfo);
            component$Mnname = apply14;
            Object obj4 = apply1;
            Object component$Mnobject = Invoke.make.apply2(component$Mntype, lookupInFormEnvironment((Symbol) apply1));
            SlotSet.set$Mnfield$Ex.apply3(this, component$Mnname, component$Mnobject);
            addToFormEnvironment((Symbol) component$Mnname, component$Mnobject);
            arg02 = arg07.getCdr();
            Pair pair = arg07;
            var$Mnval = component$Mninfo;
            i = -2;
        }
        arg03 = lists.reverse(this.global$Mnvars$Mnto$Mncreate);
        while (arg03 != LList.Empty) {
            Pair arg08 = (Pair) arg03;
            var$Mnval = arg08.getCar();
            var = lists.car.apply1(var$Mnval);
            addToGlobalVarEnvironment((Symbol) var, Scheme.applyToArgs.apply1(lists.cadr.apply1(var$Mnval)));
            arg03 = arg08.getCdr();
            Pair pair2 = arg08;
        }
        Object component$Mndescriptors = components;
        Object arg09 = component$Mndescriptors;
        while (arg09 != LList.Empty) {
            try {
                Pair arg010 = (Pair) arg09;
                Object component$Mninfo2 = arg010.getCar();
                lists.caddr.apply1(component$Mninfo2);
                Object obj5 = var$Mnval;
                var$Mnval = lists.cadddr.apply1(component$Mninfo2);
                if (var$Mnval != Boolean.FALSE) {
                    Scheme.applyToArgs.apply1(var$Mnval);
                }
                arg09 = arg010.getCdr();
            } catch (ClassCastException e8) {
                wrongType = new WrongType(e8, "arg0", i, arg09);
                throw wrongType;
            }
        }
        Object arg011 = component$Mndescriptors;
        while (arg011 != LList.Empty) {
            try {
                Pair arg012 = (Pair) arg011;
                Object component$Mninfo3 = arg012.getCar();
                Object apply15 = lists.caddr.apply1(component$Mninfo3);
                lists.cadddr.apply1(component$Mninfo3);
                Object obj6 = var$Mnval;
                var$Mnval = apply15;
                callInitialize(SlotGet.field.apply2(this, var$Mnval));
                arg011 = arg012.getCdr();
            } catch (ClassCastException e9) {
                wrongType = new WrongType(e9, "arg0", i, arg011);
                throw wrongType;
            }
        }
        Object obj7 = components;
    }

    public static SimpleSymbol lambda1symbolAppend$V(Object[] argsArray) {
        Object symbols = LList.makeList(argsArray, 0);
        Apply apply = Scheme.apply;
        ModuleMethod moduleMethod = strings.string$Mnappend;
        Object result = LList.Empty;
        Object arg0 = symbols;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                Object cdr = arg02.getCdr();
                Object car = arg02.getCar();
                try {
                    result = Pair.make(misc.symbol$To$String((Symbol) car), result);
                    arg0 = cdr;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "symbol->string", 1, car);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "arg0", -2, arg0);
            }
        }
        Object apply2 = apply.apply2(moduleMethod, LList.reverseInPlace(result));
        try {
            return misc.string$To$Symbol((CharSequence) apply2);
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "string->symbol", 1, apply2);
        }
    }

    static Object lambda2() {
        return null;
    }
}
