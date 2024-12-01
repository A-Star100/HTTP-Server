package appinventor.ai_sonicforces207.p260_servidor_web;

import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.KIO4_SimpleWebServer.KIO4_SimpleWebServer;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.AppInventorCompatActivity;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.FilePicker;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.Web;
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
import kawa.standard.throw_name;

/* compiled from: Screen1.yail */
public class Screen1 extends Form implements Runnable {
    static final SimpleSymbol Lit0 = ((SimpleSymbol) new SimpleSymbol("Screen1").readResolve());
    static final SimpleSymbol Lit1 = ((SimpleSymbol) new SimpleSymbol("getMessage").readResolve());
    static final SimpleSymbol Lit10 = ((SimpleSymbol) new SimpleSymbol("ShowListsAsJson").readResolve());
    static final SimpleSymbol Lit100 = ((SimpleSymbol) new SimpleSymbol("send-error").readResolve());
    static final SimpleSymbol Lit101 = ((SimpleSymbol) new SimpleSymbol("dispatchEvent").readResolve());
    static final SimpleSymbol Lit102 = ((SimpleSymbol) new SimpleSymbol("dispatchGenericEvent").readResolve());
    static final SimpleSymbol Lit103 = ((SimpleSymbol) new SimpleSymbol("lookup-handler").readResolve());
    static final SimpleSymbol Lit104;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12 = ((SimpleSymbol) new SimpleSymbol("Sizing").readResolve());
    static final SimpleSymbol Lit13 = ((SimpleSymbol) new SimpleSymbol("Theme").readResolve());
    static final SimpleSymbol Lit14 = ((SimpleSymbol) new SimpleSymbol("Title").readResolve());
    static final SimpleSymbol Lit15 = ((SimpleSymbol) new SimpleSymbol("VersionCode").readResolve());
    static final SimpleSymbol Lit16 = ((SimpleSymbol) new SimpleSymbol("VersionName").readResolve());
    static final SimpleSymbol Lit17 = ((SimpleSymbol) new SimpleSymbol("Screen1$Initialize").readResolve());
    static final SimpleSymbol Lit18 = ((SimpleSymbol) new SimpleSymbol("Initialize").readResolve());
    static final FString Lit19 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit2 = ((SimpleSymbol) new SimpleSymbol("*the-null-value*").readResolve());
    static final SimpleSymbol Lit20 = ((SimpleSymbol) new SimpleSymbol("HorizontalArrangement1").readResolve());
    static final SimpleSymbol Lit21 = ((SimpleSymbol) new SimpleSymbol("AlignVertical").readResolve());
    static final IntNum Lit22 = IntNum.make(2);
    static final SimpleSymbol Lit23 = ((SimpleSymbol) new SimpleSymbol("Width").readResolve());
    static final IntNum Lit24 = IntNum.make(-2);
    static final FString Lit25 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit26 = new FString("com.google.appinventor.components.runtime.TextBox");
    static final SimpleSymbol Lit27 = ((SimpleSymbol) new SimpleSymbol("CampoDeTexto1").readResolve());
    static final SimpleSymbol Lit28 = ((SimpleSymbol) new SimpleSymbol("Hint").readResolve());
    static final FString Lit29 = new FString("com.google.appinventor.components.runtime.TextBox");
    static final SimpleSymbol Lit3 = ((SimpleSymbol) new SimpleSymbol("AlignHorizontal").readResolve());
    static final FString Lit30 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit31 = ((SimpleSymbol) new SimpleSymbol("Botón3").readResolve());
    static final SimpleSymbol Lit32 = ((SimpleSymbol) new SimpleSymbol("Text").readResolve());
    static final FString Lit33 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit34 = ((SimpleSymbol) new SimpleSymbol("KIO4_SimpleWebServer1").readResolve());
    static final SimpleSymbol Lit35 = ((SimpleSymbol) new SimpleSymbol("ServeText").readResolve());
    static final PairWithPosition Lit36;
    static final SimpleSymbol Lit37 = ((SimpleSymbol) new SimpleSymbol("Notifier1").readResolve());
    static final SimpleSymbol Lit38 = ((SimpleSymbol) new SimpleSymbol("ShowMessageDialog").readResolve());
    static final SimpleSymbol Lit39 = ((SimpleSymbol) new SimpleSymbol("Device1").readResolve());
    static final IntNum Lit4 = IntNum.make(3);
    static final SimpleSymbol Lit40 = ((SimpleSymbol) new SimpleSymbol("GetIpAddress").readResolve());
    static final PairWithPosition Lit41;
    static final PairWithPosition Lit42;
    static final PairWithPosition Lit43;
    static final PairWithPosition Lit44;
    static final SimpleSymbol Lit45 = ((SimpleSymbol) new SimpleSymbol("Botón3$Click").readResolve());
    static final SimpleSymbol Lit46 = ((SimpleSymbol) new SimpleSymbol("Click").readResolve());
    static final FString Lit47 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit48 = ((SimpleSymbol) new SimpleSymbol("HorizontalArrangement2").readResolve());
    static final FString Lit49 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit5 = ((SimpleSymbol) new SimpleSymbol("number").readResolve());
    static final FString Lit50 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit51 = ((SimpleSymbol) new SimpleSymbol("Botón5").readResolve());
    static final FString Lit52 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit53 = ((SimpleSymbol) new SimpleSymbol("ShowChooseDialog").readResolve());
    static final PairWithPosition Lit54;
    static final SimpleSymbol Lit55 = ((SimpleSymbol) new SimpleSymbol("Botón5$Click").readResolve());
    static final FString Lit56 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit57 = ((SimpleSymbol) new SimpleSymbol("Button1").readResolve());
    static final FString Lit58 = new FString("com.google.appinventor.components.runtime.Button");
    static final PairWithPosition Lit59;
    static final SimpleSymbol Lit6 = ((SimpleSymbol) new SimpleSymbol("AppName").readResolve());
    static final SimpleSymbol Lit60 = ((SimpleSymbol) new SimpleSymbol("Button1$Click").readResolve());
    static final FString Lit61 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit62 = ((SimpleSymbol) new SimpleSymbol("advanced").readResolve());
    static final FString Lit63 = new FString("com.google.appinventor.components.runtime.Button");
    static final PairWithPosition Lit64;
    static final SimpleSymbol Lit65 = ((SimpleSymbol) new SimpleSymbol("advanced$Click").readResolve());
    static final FString Lit66 = new FString("com.google.appinventor.components.runtime.FilePicker");
    static final SimpleSymbol Lit67 = ((SimpleSymbol) new SimpleSymbol("FilePicker1").readResolve());
    static final SimpleSymbol Lit68 = ((SimpleSymbol) new SimpleSymbol("Visible").readResolve());
    static final FString Lit69 = new FString("com.google.appinventor.components.runtime.FilePicker");
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit70 = ((SimpleSymbol) new SimpleSymbol("FilePicker1$AfterPicking").readResolve());
    static final SimpleSymbol Lit71 = ((SimpleSymbol) new SimpleSymbol("AfterPicking").readResolve());
    static final FString Lit72 = new FString("com.KIO4_SimpleWebServer.KIO4_SimpleWebServer");
    static final FString Lit73 = new FString("com.KIO4_SimpleWebServer.KIO4_SimpleWebServer");
    static final FString Lit74 = new FString("com.google.appinventor.components.runtime.Notifier");
    static final FString Lit75 = new FString("com.google.appinventor.components.runtime.Notifier");
    static final SimpleSymbol Lit76 = ((SimpleSymbol) new SimpleSymbol("Notifier1$AfterTextInput").readResolve());
    static final SimpleSymbol Lit77 = ((SimpleSymbol) new SimpleSymbol("AfterTextInput").readResolve());
    static final SimpleSymbol Lit78 = ((SimpleSymbol) new SimpleSymbol("Notifier1$TextInputCanceled").readResolve());
    static final SimpleSymbol Lit79 = ((SimpleSymbol) new SimpleSymbol("TextInputCanceled").readResolve());
    static final SimpleSymbol Lit8 = ((SimpleSymbol) new SimpleSymbol("Icon").readResolve());
    static final SimpleSymbol Lit80 = ((SimpleSymbol) new SimpleSymbol("$choice").readResolve());
    static final PairWithPosition Lit81;
    static final SimpleSymbol Lit82 = ((SimpleSymbol) new SimpleSymbol("StopServer").readResolve());
    static final SimpleSymbol Lit83 = ((SimpleSymbol) new SimpleSymbol("Notifier1$AfterChoosing").readResolve());
    static final SimpleSymbol Lit84 = ((SimpleSymbol) new SimpleSymbol("AfterChoosing").readResolve());
    static final FString Lit85 = new FString("com.google.appinventor.components.runtime.Web");
    static final SimpleSymbol Lit86 = ((SimpleSymbol) new SimpleSymbol("Web1").readResolve());
    static final FString Lit87 = new FString("com.google.appinventor.components.runtime.Web");
    static final FString Lit88 = new FString("com.gordonlu.device.Device");
    static final FString Lit89 = new FString("com.gordonlu.device.Device");
    static final SimpleSymbol Lit9 = ((SimpleSymbol) new SimpleSymbol("ScreenOrientation").readResolve());
    static final SimpleSymbol Lit90 = ((SimpleSymbol) new SimpleSymbol("get-simple-name").readResolve());
    static final SimpleSymbol Lit91 = ((SimpleSymbol) new SimpleSymbol("android-log-form").readResolve());
    static final SimpleSymbol Lit92 = ((SimpleSymbol) new SimpleSymbol("add-to-form-environment").readResolve());
    static final SimpleSymbol Lit93 = ((SimpleSymbol) new SimpleSymbol("lookup-in-form-environment").readResolve());
    static final SimpleSymbol Lit94 = ((SimpleSymbol) new SimpleSymbol("is-bound-in-form-environment").readResolve());
    static final SimpleSymbol Lit95 = ((SimpleSymbol) new SimpleSymbol("add-to-global-var-environment").readResolve());
    static final SimpleSymbol Lit96 = ((SimpleSymbol) new SimpleSymbol("add-to-events").readResolve());
    static final SimpleSymbol Lit97 = ((SimpleSymbol) new SimpleSymbol("add-to-components").readResolve());
    static final SimpleSymbol Lit98 = ((SimpleSymbol) new SimpleSymbol("add-to-global-vars").readResolve());
    static final SimpleSymbol Lit99 = ((SimpleSymbol) new SimpleSymbol("add-to-form-do-after-creation").readResolve());
    public static Screen1 Screen1;
    static final ModuleMethod lambda$Fn1 = null;
    static final ModuleMethod lambda$Fn10 = null;
    static final ModuleMethod lambda$Fn11 = null;
    static final ModuleMethod lambda$Fn12 = null;
    static final ModuleMethod lambda$Fn13 = null;
    static final ModuleMethod lambda$Fn14 = null;
    static final ModuleMethod lambda$Fn15 = null;
    static final ModuleMethod lambda$Fn16 = null;
    static final ModuleMethod lambda$Fn17 = null;
    static final ModuleMethod lambda$Fn18 = null;
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

    /* renamed from: Botón3  reason: contains not printable characters */
    public Button f0Botn3;

    /* renamed from: Botón3$Click  reason: contains not printable characters */
    public final ModuleMethod f1Botn3$Click;

    /* renamed from: Botón5  reason: contains not printable characters */
    public Button f2Botn5;

    /* renamed from: Botón5$Click  reason: contains not printable characters */
    public final ModuleMethod f3Botn5$Click;
    public Button Button1;
    public final ModuleMethod Button1$Click;
    public TextBox CampoDeTexto1;
    public Device Device1;
    public FilePicker FilePicker1;
    public final ModuleMethod FilePicker1$AfterPicking;
    public HorizontalArrangement HorizontalArrangement1;
    public HorizontalArrangement HorizontalArrangement2;
    public KIO4_SimpleWebServer KIO4_SimpleWebServer1;
    public Notifier Notifier1;
    public final ModuleMethod Notifier1$AfterChoosing;
    public final ModuleMethod Notifier1$AfterTextInput;
    public final ModuleMethod Notifier1$TextInputCanceled;
    public final ModuleMethod Screen1$Initialize;
    public Web Web1;
    public final ModuleMethod add$Mnto$Mncomponents;
    public final ModuleMethod add$Mnto$Mnevents;
    public final ModuleMethod add$Mnto$Mnform$Mndo$Mnafter$Mncreation;
    public final ModuleMethod add$Mnto$Mnform$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvar$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvars;
    public Button advanced;
    public final ModuleMethod advanced$Click;
    public final ModuleMethod android$Mnlog$Mnform;
    public LList components$Mnto$Mncreate;
    public final ModuleMethod dispatchEvent;
    public final ModuleMethod dispatchGenericEvent;
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
    public final ModuleMethod send$Mnerror;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("any").readResolve();
        Lit104 = simpleSymbol;
        Lit81 = PairWithPosition.make(simpleSymbol, PairWithPosition.make(simpleSymbol, LList.Empty, "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Screen1.yail", 462947), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Screen1.yail", 462942);
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_TEXT).readResolve();
        Lit7 = simpleSymbol2;
        Lit64 = PairWithPosition.make(simpleSymbol2, LList.Empty, "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Screen1.yail", 352335);
        Lit59 = PairWithPosition.make(simpleSymbol2, LList.Empty, "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Screen1.yail", 315470);
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN).readResolve();
        Lit11 = simpleSymbol3;
        Lit54 = PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol3, LList.Empty, "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Screen1.yail", 278776), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Screen1.yail", 278771), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Screen1.yail", 278766), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Screen1.yail", 278761), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Screen1.yail", 278755);
        Lit44 = PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, LList.Empty, "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Screen1.yail", 205445), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Screen1.yail", 205440), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Screen1.yail", 205434);
        Lit43 = PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, LList.Empty, "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Screen1.yail", 205378), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Screen1.yail", 205373), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Screen1.yail", 205368), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Screen1.yail", 205363), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Screen1.yail", 205358), "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Screen1.yail", 205352);
        Lit42 = PairWithPosition.make(simpleSymbol3, LList.Empty, "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Screen1.yail", 205293);
        Lit41 = PairWithPosition.make(simpleSymbol3, LList.Empty, "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Screen1.yail", 205143);
        Lit36 = PairWithPosition.make(simpleSymbol2, LList.Empty, "/tmp/1733016886162_4606834975995045888-0/youngandroidproject/../src/appinventor/ai_sonicforces207/p260_servidor_web/Screen1.yail", 204920);
    }

    public Screen1() {
        ModuleInfo.register(this);
        frame frame2 = new frame();
        frame2.$main = this;
        this.get$Mnsimple$Mnname = new ModuleMethod(frame2, 1, Lit90, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.onCreate = new ModuleMethod(frame2, 2, "onCreate", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.android$Mnlog$Mnform = new ModuleMethod(frame2, 3, Lit91, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnform$Mnenvironment = new ModuleMethod(frame2, 4, Lit92, 8194);
        this.lookup$Mnin$Mnform$Mnenvironment = new ModuleMethod(frame2, 5, Lit93, 8193);
        this.is$Mnbound$Mnin$Mnform$Mnenvironment = new ModuleMethod(frame2, 7, Lit94, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnglobal$Mnvar$Mnenvironment = new ModuleMethod(frame2, 8, Lit95, 8194);
        this.add$Mnto$Mnevents = new ModuleMethod(frame2, 9, Lit96, 8194);
        this.add$Mnto$Mncomponents = new ModuleMethod(frame2, 10, Lit97, 16388);
        this.add$Mnto$Mnglobal$Mnvars = new ModuleMethod(frame2, 11, Lit98, 8194);
        this.add$Mnto$Mnform$Mndo$Mnafter$Mncreation = new ModuleMethod(frame2, 12, Lit99, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.send$Mnerror = new ModuleMethod(frame2, 13, Lit100, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.process$Mnexception = new ModuleMethod(frame2, 14, "process-exception", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.dispatchEvent = new ModuleMethod(frame2, 15, Lit101, 16388);
        this.dispatchGenericEvent = new ModuleMethod(frame2, 16, Lit102, 16388);
        this.lookup$Mnhandler = new ModuleMethod(frame2, 17, Lit103, 8194);
        ModuleMethod moduleMethod = new ModuleMethod(frame2, 18, (Object) null, 0);
        moduleMethod.setProperty("source-location", "/tmp/runtime1488391760221600366.scm:634");
        lambda$Fn1 = moduleMethod;
        this.$define = new ModuleMethod(frame2, 19, "$define", 0);
        lambda$Fn2 = new ModuleMethod(frame2, 20, (Object) null, 0);
        this.Screen1$Initialize = new ModuleMethod(frame2, 21, Lit17, 0);
        lambda$Fn3 = new ModuleMethod(frame2, 22, (Object) null, 0);
        lambda$Fn4 = new ModuleMethod(frame2, 23, (Object) null, 0);
        lambda$Fn5 = new ModuleMethod(frame2, 24, (Object) null, 0);
        lambda$Fn6 = new ModuleMethod(frame2, 25, (Object) null, 0);
        lambda$Fn7 = new ModuleMethod(frame2, 26, (Object) null, 0);
        lambda$Fn8 = new ModuleMethod(frame2, 27, (Object) null, 0);
        this.f1Botn3$Click = new ModuleMethod(frame2, 28, Lit45, 0);
        lambda$Fn9 = new ModuleMethod(frame2, 29, (Object) null, 0);
        lambda$Fn10 = new ModuleMethod(frame2, 30, (Object) null, 0);
        lambda$Fn11 = new ModuleMethod(frame2, 31, (Object) null, 0);
        lambda$Fn12 = new ModuleMethod(frame2, 32, (Object) null, 0);
        this.f3Botn5$Click = new ModuleMethod(frame2, 33, Lit55, 0);
        lambda$Fn13 = new ModuleMethod(frame2, 34, (Object) null, 0);
        lambda$Fn14 = new ModuleMethod(frame2, 35, (Object) null, 0);
        this.Button1$Click = new ModuleMethod(frame2, 36, Lit60, 0);
        lambda$Fn15 = new ModuleMethod(frame2, 37, (Object) null, 0);
        lambda$Fn16 = new ModuleMethod(frame2, 38, (Object) null, 0);
        this.advanced$Click = new ModuleMethod(frame2, 39, Lit65, 0);
        lambda$Fn17 = new ModuleMethod(frame2, 40, (Object) null, 0);
        lambda$Fn18 = new ModuleMethod(frame2, 41, (Object) null, 0);
        this.FilePicker1$AfterPicking = new ModuleMethod(frame2, 42, Lit70, 0);
        this.Notifier1$AfterTextInput = new ModuleMethod(frame2, 43, Lit76, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.Notifier1$TextInputCanceled = new ModuleMethod(frame2, 44, Lit78, 0);
        this.Notifier1$AfterChoosing = new ModuleMethod(frame2, 45, Lit83, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
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
        runtime.$instance.run();
        this.$Stdebug$Mnform$St = Boolean.FALSE;
        SimpleSymbol simpleSymbol = Lit0;
        this.form$Mnenvironment = Environment.make(misc.symbol$To$String(simpleSymbol));
        FString stringAppend = strings.stringAppend(misc.symbol$To$String(simpleSymbol), "-global-vars");
        this.global$Mnvar$Mnenvironment = Environment.make(stringAppend == null ? null : stringAppend.toString());
        Screen1 = null;
        this.form$Mnname$Mnsymbol = simpleSymbol;
        this.events$Mnto$Mnregister = LList.Empty;
        this.components$Mnto$Mncreate = LList.Empty;
        this.global$Mnvars$Mnto$Mncreate = LList.Empty;
        this.form$Mndo$Mnafter$Mncreation = LList.Empty;
        runtime.$instance.run();
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            SimpleSymbol simpleSymbol2 = Lit3;
            IntNum intNum = Lit4;
            SimpleSymbol simpleSymbol3 = Lit5;
            runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
            SimpleSymbol simpleSymbol4 = Lit6;
            SimpleSymbol simpleSymbol5 = Lit7;
            runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol4, "HTTP-Server", simpleSymbol5);
            runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit8, "globe_webviewicon.png", simpleSymbol5);
            runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit9, "unspecified", simpleSymbol5);
            runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit10, Boolean.FALSE, Lit11);
            runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit12, "Responsive", simpleSymbol5);
            runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit13, "Classic", simpleSymbol5);
            runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit14, "HTTP-Server", simpleSymbol5);
            runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit15, intNum, simpleSymbol3);
            Values.writeValues(runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit16, "1.2", simpleSymbol5), $result);
        } else {
            addToFormDoAfterCreation(new Promise(lambda$Fn2));
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            runtime.addToCurrentFormEnvironment(Lit17, this.Screen1$Initialize);
        } else {
            addToFormEnvironment(Lit17, this.Screen1$Initialize);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "Screen1", "Initialize");
        } else {
            addToEvents(simpleSymbol, Lit18);
        }
        this.HorizontalArrangement1 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit19, Lit20, lambda$Fn3), $result);
        } else {
            addToComponents(simpleSymbol, Lit25, Lit20, lambda$Fn4);
        }
        this.CampoDeTexto1 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(Lit20, Lit26, Lit27, lambda$Fn5), $result);
        } else {
            addToComponents(Lit20, Lit29, Lit27, lambda$Fn6);
        }
        this.f0Botn3 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(Lit20, Lit30, Lit31, lambda$Fn7), $result);
        } else {
            addToComponents(Lit20, Lit33, Lit31, lambda$Fn8);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            runtime.addToCurrentFormEnvironment(Lit45, this.f1Botn3$Click);
        } else {
            addToFormEnvironment(Lit45, this.f1Botn3$Click);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "Botón3", "Click");
        } else {
            addToEvents(Lit31, Lit46);
        }
        this.HorizontalArrangement2 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit47, Lit48, lambda$Fn9), $result);
        } else {
            addToComponents(simpleSymbol, Lit49, Lit48, lambda$Fn10);
        }
        this.f2Botn5 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(Lit48, Lit50, Lit51, lambda$Fn11), $result);
        } else {
            addToComponents(Lit48, Lit52, Lit51, lambda$Fn12);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            runtime.addToCurrentFormEnvironment(Lit55, this.f3Botn5$Click);
        } else {
            addToFormEnvironment(Lit55, this.f3Botn5$Click);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "Botón5", "Click");
        } else {
            addToEvents(Lit51, Lit46);
        }
        this.Button1 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(Lit48, Lit56, Lit57, lambda$Fn13), $result);
        } else {
            addToComponents(Lit48, Lit58, Lit57, lambda$Fn14);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            runtime.addToCurrentFormEnvironment(Lit60, this.Button1$Click);
        } else {
            addToFormEnvironment(Lit60, this.Button1$Click);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "Button1", "Click");
        } else {
            addToEvents(Lit57, Lit46);
        }
        this.advanced = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit61, Lit62, lambda$Fn15), $result);
        } else {
            addToComponents(simpleSymbol, Lit63, Lit62, lambda$Fn16);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            runtime.addToCurrentFormEnvironment(Lit65, this.advanced$Click);
        } else {
            addToFormEnvironment(Lit65, this.advanced$Click);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "advanced", "Click");
        } else {
            addToEvents(Lit62, Lit46);
        }
        this.FilePicker1 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit66, Lit67, lambda$Fn17), $result);
        } else {
            addToComponents(simpleSymbol, Lit69, Lit67, lambda$Fn18);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            runtime.addToCurrentFormEnvironment(Lit70, this.FilePicker1$AfterPicking);
        } else {
            addToFormEnvironment(Lit70, this.FilePicker1$AfterPicking);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "FilePicker1", "AfterPicking");
        } else {
            addToEvents(Lit67, Lit71);
        }
        this.KIO4_SimpleWebServer1 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit72, Lit34, Boolean.FALSE), $result);
        } else {
            addToComponents(simpleSymbol, Lit73, Lit34, Boolean.FALSE);
        }
        this.Notifier1 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit74, Lit37, Boolean.FALSE), $result);
        } else {
            addToComponents(simpleSymbol, Lit75, Lit37, Boolean.FALSE);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            runtime.addToCurrentFormEnvironment(Lit76, this.Notifier1$AfterTextInput);
        } else {
            addToFormEnvironment(Lit76, this.Notifier1$AfterTextInput);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "Notifier1", "AfterTextInput");
        } else {
            addToEvents(Lit37, Lit77);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            runtime.addToCurrentFormEnvironment(Lit78, this.Notifier1$TextInputCanceled);
        } else {
            addToFormEnvironment(Lit78, this.Notifier1$TextInputCanceled);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "Notifier1", "TextInputCanceled");
        } else {
            addToEvents(Lit37, Lit79);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            runtime.addToCurrentFormEnvironment(Lit83, this.Notifier1$AfterChoosing);
        } else {
            addToFormEnvironment(Lit83, this.Notifier1$AfterChoosing);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "Notifier1", "AfterChoosing");
        } else {
            addToEvents(Lit37, Lit84);
        }
        this.Web1 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit85, Lit86, Boolean.FALSE), $result);
        } else {
            addToComponents(simpleSymbol, Lit87, Lit86, Boolean.FALSE);
        }
        this.Device1 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit88, Lit39, Boolean.FALSE), $result);
        } else {
            addToComponents(simpleSymbol, Lit89, Lit39, Boolean.FALSE);
        }
        runtime.initRuntime();
    }

    static Object lambda3() {
        SimpleSymbol simpleSymbol = Lit0;
        SimpleSymbol simpleSymbol2 = Lit3;
        IntNum intNum = Lit4;
        SimpleSymbol simpleSymbol3 = Lit5;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        SimpleSymbol simpleSymbol4 = Lit6;
        SimpleSymbol simpleSymbol5 = Lit7;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol4, "HTTP-Server", simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit8, "globe_webviewicon.png", simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit9, "unspecified", simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit10, Boolean.FALSE, Lit11);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit12, "Responsive", simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit13, "Classic", simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit14, "HTTP-Server", simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit15, intNum, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit16, "1.2", simpleSymbol5);
    }

    public Object Screen1$Initialize() {
        runtime.setThisForm();
        return runtime.lookupGlobalVarInCurrentFormEnvironment(Lit2, runtime.$Stthe$Mnnull$Mnvalue$St);
    }

    static Object lambda4() {
        SimpleSymbol simpleSymbol = Lit20;
        SimpleSymbol simpleSymbol2 = Lit3;
        IntNum intNum = Lit4;
        SimpleSymbol simpleSymbol3 = Lit5;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit21, Lit22, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit23, Lit24, simpleSymbol3);
    }

    static Object lambda5() {
        SimpleSymbol simpleSymbol = Lit20;
        SimpleSymbol simpleSymbol2 = Lit3;
        IntNum intNum = Lit4;
        SimpleSymbol simpleSymbol3 = Lit5;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit21, Lit22, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit23, Lit24, simpleSymbol3);
    }

    static Object lambda6() {
        return runtime.setAndCoerceProperty$Ex(Lit27, Lit28, "Text to serve here", Lit7);
    }

    static Object lambda7() {
        return runtime.setAndCoerceProperty$Ex(Lit27, Lit28, "Text to serve here", Lit7);
    }

    static Object lambda8() {
        return runtime.setAndCoerceProperty$Ex(Lit31, Lit32, "Serve text", Lit7);
    }

    static Object lambda9() {
        return runtime.setAndCoerceProperty$Ex(Lit31, Lit32, "Serve text", Lit7);
    }

    /* renamed from: Botón3$Click  reason: contains not printable characters */
    public Object m2Botn3$Click() {
        runtime.setThisForm();
        runtime.callComponentMethod(Lit34, Lit35, LList.list1(runtime.get$Mnproperty.apply2(Lit27, Lit32)), Lit36);
        SimpleSymbol simpleSymbol = Lit37;
        SimpleSymbol simpleSymbol2 = Lit38;
        ModuleMethod moduleMethod = strings.string$Mnappend;
        Pair list1 = LList.list1("You can visit ");
        SimpleSymbol simpleSymbol3 = Lit39;
        SimpleSymbol simpleSymbol4 = Lit40;
        LList.chain1(LList.chain4(list1, runtime.callComponentMethod(simpleSymbol3, simpleSymbol4, LList.list1(Boolean.TRUE), Lit41), ":8080 in your browser to access the server! ", "(The IPV6 address ", runtime.callComponentMethod(simpleSymbol3, simpleSymbol4, LList.list1(Boolean.FALSE), Lit42)), " can be used as well to access the server)");
        return runtime.callComponentMethod(simpleSymbol, simpleSymbol2, LList.list3(runtime.callYailPrimitive(moduleMethod, list1, Lit43, "join"), "Successfully started serving!", "OK"), Lit44);
    }

    static Object lambda10() {
        SimpleSymbol simpleSymbol = Lit48;
        SimpleSymbol simpleSymbol2 = Lit3;
        IntNum intNum = Lit4;
        SimpleSymbol simpleSymbol3 = Lit5;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit23, Lit24, simpleSymbol3);
    }

    static Object lambda11() {
        SimpleSymbol simpleSymbol = Lit48;
        SimpleSymbol simpleSymbol2 = Lit3;
        IntNum intNum = Lit4;
        SimpleSymbol simpleSymbol3 = Lit5;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit23, Lit24, simpleSymbol3);
    }

    static Object lambda12() {
        return runtime.setAndCoerceProperty$Ex(Lit51, Lit32, "Stop server", Lit7);
    }

    static Object lambda13() {
        return runtime.setAndCoerceProperty$Ex(Lit51, Lit32, "Stop server", Lit7);
    }

    /* renamed from: Botón5$Click  reason: contains not printable characters */
    public Object m3Botn5$Click() {
        runtime.setThisForm();
        SimpleSymbol simpleSymbol = Lit37;
        SimpleSymbol simpleSymbol2 = Lit53;
        Pair list1 = LList.list1("If you are sure that you want to stop the server, press \"Yes\", or else press \"No\".");
        LList.chain4(list1, "Do you really want to stop the server?", "No", "Yes", Boolean.FALSE);
        return runtime.callComponentMethod(simpleSymbol, simpleSymbol2, list1, Lit54);
    }

    static Object lambda14() {
        return runtime.setAndCoerceProperty$Ex(Lit57, Lit32, "View server in WebViewer", Lit7);
    }

    static Object lambda15() {
        return runtime.setAndCoerceProperty$Ex(Lit57, Lit32, "View server in WebViewer", Lit7);
    }

    public Object Button1$Click() {
        runtime.setThisForm();
        return runtime.callYailPrimitive(runtime.open$Mnanother$Mnscreen, LList.list1("Screen2"), Lit59, "open another screen");
    }

    static Object lambda16() {
        return runtime.setAndCoerceProperty$Ex(Lit62, Lit32, "Advanced...", Lit7);
    }

    static Object lambda17() {
        return runtime.setAndCoerceProperty$Ex(Lit62, Lit32, "Advanced...", Lit7);
    }

    public Object advanced$Click() {
        runtime.setThisForm();
        return runtime.callYailPrimitive(runtime.open$Mnanother$Mnscreen, LList.list1("Advanced"), Lit64, "open another screen");
    }

    static Object lambda18() {
        SimpleSymbol simpleSymbol = Lit67;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit32, "Text for FilePicker1", Lit7);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit68, Boolean.FALSE, Lit11);
    }

    static Object lambda19() {
        SimpleSymbol simpleSymbol = Lit67;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit32, "Text for FilePicker1", Lit7);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit68, Boolean.FALSE, Lit11);
    }

    public Object FilePicker1$AfterPicking() {
        runtime.setThisForm();
        return runtime.lookupGlobalVarInCurrentFormEnvironment(Lit2, runtime.$Stthe$Mnnull$Mnvalue$St);
    }

    public Object Notifier1$AfterTextInput(Object $response) {
        runtime.sanitizeComponentData($response);
        runtime.setThisForm();
        return runtime.lookupGlobalVarInCurrentFormEnvironment(Lit2, runtime.$Stthe$Mnnull$Mnvalue$St);
    }

    public Object Notifier1$TextInputCanceled() {
        runtime.setThisForm();
        return runtime.lookupGlobalVarInCurrentFormEnvironment(Lit2, runtime.$Stthe$Mnnull$Mnvalue$St);
    }

    public Object Notifier1$AfterChoosing(Object $choice) {
        Object obj;
        Object $choice2 = runtime.sanitizeComponentData($choice);
        runtime.setThisForm();
        ModuleMethod moduleMethod = runtime.yail$Mnequal$Qu;
        if ($choice2 instanceof Package) {
            obj = runtime.signalRuntimeError(strings.stringAppend("The variable ", runtime.getDisplayRepresentation(Lit80), " is not bound in the current context"), "Unbound Variable");
        } else {
            obj = $choice2;
        }
        return runtime.callYailPrimitive(moduleMethod, LList.list2(obj, "Yes"), Lit81, "=") != Boolean.FALSE ? runtime.callComponentMethod(Lit34, Lit82, LList.Empty, LList.Empty) : Values.empty;
    }

    /* compiled from: Screen1.yail */
    public class frame extends ModuleBody {
        Screen1 $main;

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 1:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 2:
                    if (!(obj instanceof Screen1)) {
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
                    if (!(obj instanceof Screen1)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 43:
                case 45:
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
                    if (!(obj instanceof Screen1)) {
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
                    if (!(obj instanceof Screen1)) {
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
                case 43:
                    return this.$main.Notifier1$AfterTextInput(obj);
                case 45:
                    return this.$main.Notifier1$AfterChoosing(obj);
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
                    Screen1 screen1 = this.$main;
                    try {
                        Component component = (Component) obj;
                        try {
                            String str = (String) obj2;
                            try {
                                if (obj3 == Boolean.FALSE) {
                                    z = false;
                                }
                                try {
                                    screen1.dispatchGenericEvent(component, str, z, (Object[]) obj4);
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
                    return Screen1.lambda2();
                case 19:
                    this.$main.$define();
                    return Values.empty;
                case 20:
                    return Screen1.lambda3();
                case 21:
                    return this.$main.Screen1$Initialize();
                case 22:
                    return Screen1.lambda4();
                case 23:
                    return Screen1.lambda5();
                case 24:
                    return Screen1.lambda6();
                case 25:
                    return Screen1.lambda7();
                case 26:
                    return Screen1.lambda8();
                case 27:
                    return Screen1.lambda9();
                case 28:
                    return this.$main.m2Botn3$Click();
                case 29:
                    return Screen1.lambda10();
                case 30:
                    return Screen1.lambda11();
                case 31:
                    return Screen1.lambda12();
                case 32:
                    return Screen1.lambda13();
                case 33:
                    return this.$main.m3Botn5$Click();
                case 34:
                    return Screen1.lambda14();
                case 35:
                    return Screen1.lambda15();
                case 36:
                    return this.$main.Button1$Click();
                case 37:
                    return Screen1.lambda16();
                case 38:
                    return Screen1.lambda17();
                case 39:
                    return this.$main.advanced$Click();
                case 40:
                    return Screen1.lambda18();
                case 41:
                    return Screen1.lambda19();
                case 42:
                    return this.$main.FilePicker1$AfterPicking();
                case 44:
                    return this.$main.Notifier1$TextInputCanceled();
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
                case 40:
                case 41:
                case 42:
                case 44:
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
        Screen1 = this;
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
