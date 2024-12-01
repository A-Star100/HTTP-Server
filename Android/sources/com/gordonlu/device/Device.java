package com.gordonlu.device;

import android.app.Activity;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.os.EnvironmentCompat;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import gnu.expr.Declaration;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@DesignerComponent(category = ComponentCategory.EXTENSION, description = "A non-visible extension that gets some information or parse data of the user's device. ", helpUrl = "https://sites.google.com/view/appinventor-aicode/my-extensions/device-extension", iconName = "images/phoneip.png", nonVisible = true, version = 24)
@UsesLibraries(libraries = "")
@SimpleObject(external = true)
@UsesPermissions(permissionNames = "android.permission.READ_PRIVILEGED_PHONE_STATE, android.permission.BLUETOOTH_ADMIN, android.permission.WRITE_SETTINGS, android.permission.CHANGE_WIFI_STATE, android.permission.MODIFY_AUDIO_SETTINGS, android.permission.READ_PHONE_STATE, android.permission.ACCESS_NETWORK_STATE, android.permission.ACCESS_WIFI_STATE,android.permission.INTERNET, android.permission.CHANGE_WIFI_STATE, android.permission.ACCESS_COARSE_LOCATION")
public class Device extends AndroidNonvisibleComponent {
    private Activity activity;
    private AudioManager audioManager;
    private Context context;

    public Device(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.activity = componentContainer.$context();
        this.context = componentContainer.$context();
    }

    @SimpleProperty(description = "Returns the CPU temperature of the device.")
    public static float CpuTemperature() {
        try {
            Process exec = Runtime.getRuntime().exec("cat sys/class/thermal/thermal_zone0/temp");
            exec.waitFor();
            String readLine = new BufferedReader(new InputStreamReader(exec.getInputStream())).readLine();
            if (readLine != null) {
                return Float.parseFloat(readLine) / 1000.0f;
            }
            return 51.0f;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    @SimpleFunction(description = "Returns the relative luminance of a color. This block only supports devices above or Android 8.0.")
    public static float GetLuminanceOfColor(long j) {
        return Color.luminance(j);
    }

    @SimpleFunction(description = "The time at which the build was produced, given in milliseconds since the UNIX epoch.")
    public static long Time() {
        return Build.TIME;
    }

    private String getApplicationInfo(String str, int i, String str2) {
        try {
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo(str, 128);
            return i == 1 ? packageInfo.versionName : i == 2 ? Integer.toString(packageInfo.versionCode) : str2;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return str2;
        }
    }

    private Intent getBatteryStatus() {
        return this.context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    }

    private String getStringAddress(double d, double d2, int i) {
        String postalCode;
        try {
            Address address = new Geocoder(this.context, Locale.getDefault()).getFromLocation(d, d2, 1).get(0);
            if (i == 1) {
                postalCode = address.getAddressLine(0);
            } else if (i == 2) {
                postalCode = address.getLocality();
            } else if (i == 3) {
                postalCode = address.getCountryName();
            } else if (i != 4) {
                return "";
            } else {
                postalCode = address.getPostalCode();
            }
            return postalCode;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private ActivityManager.MemoryInfo memoryInfo() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) this.context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo;
    }

    @SimpleProperty(description = "Returns the Android version of the phone.")
    public String AndroidVersion() {
        return Build.VERSION.SDK_INT == 19 ? "4.4" : Build.VERSION.SDK_INT == 20 ? "4.4W" : Build.VERSION.SDK_INT == 21 ? "5.0" : Build.VERSION.SDK_INT == 22 ? "5.1" : Build.VERSION.SDK_INT == 23 ? "6.0" : Build.VERSION.SDK_INT == 24 ? "7.0" : Build.VERSION.SDK_INT == 25 ? "7.1" : Build.VERSION.SDK_INT == 26 ? "8.0" : Build.VERSION.SDK_INT == 27 ? "8.1" : Build.VERSION.SDK_INT == 28 ? "9" : Build.VERSION.SDK_INT == 29 ? "10" : Build.VERSION.SDK_INT == 30 ? "11" : Build.VERSION.SDK_INT == 31 ? "12" : "UNKNOWN";
    }

    @SimpleFunction(description = "Returns the package name of the current running application.")
    public String AppPackageName() {
        return this.context.getPackageName();
    }

    @SimpleFunction(description = "Returns the last updated time of the application from the given package name.")
    public long ApplicationLastUpdatedTime(String str) {
        try {
            return this.context.getPackageManager().getPackageInfo(str, 128).lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @SimpleFunction(description = "Returns the application name from the given package name, else returns the notFound value.")
    public String ApplicationName(String str, String str2) {
        PackageManager packageManager = this.context.getPackageManager();
        try {
            return (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 128));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return str2;
        }
    }

    @SimpleFunction(description = "Returns an absolute path of the application specific directory.")
    public String ApplicationSpecificDirectory() {
        return this.context.getExternalFilesDir((String) null).getPath();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the base OS build the product is based on.")
    public String BaseOS() {
        return Build.VERSION.BASE_OS;
    }

    @SimpleFunction(description = "Returns the battery capacity.")
    public double BatteryCapacity() {
        try {
            return ((Double) Class.forName("com.android.internal.os.PowerProfile").getMethod("getBatteryCapacity", new Class[0]).invoke(Class.forName("com.android.internal.os.PowerProfile").getConstructor(new Class[]{Context.class}).newInstance(new Object[]{this.context}), new Object[0])).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0d;
        }
    }

    @SimpleProperty(description = "Returns true if the device is charging, else false.")
    public boolean BatteryCharging() {
        int intExtra = getBatteryStatus().getIntExtra("plugged", -1);
        return (intExtra == 1 || intExtra == 2) || intExtra == 4;
    }

    @SimpleFunction(description = "Returns the health value of the phone's battery.")
    public final String BatteryHealth() {
        Intent batteryStatus = getBatteryStatus();
        int i = 0;
        if (batteryStatus != null) {
            i = batteryStatus.getIntExtra("health", 0);
        }
        return i == 1 ? "UNKNOWN" : i == 2 ? "GOOD" : i == 3 ? "OVERHEAT" : i == 4 ? "DEAD" : i == 5 ? "OVER_VOLTAGE" : i == 6 ? "UNSPECIFIED_FAILURE" : i == 7 ? "COLD" : "UNKNOWN";
    }

    @SimpleFunction(description = "Returns the battery level in percentage.")
    public final int BatteryPercentage() {
        Intent batteryStatus = getBatteryStatus();
        if (batteryStatus == null) {
            return 0;
        }
        return (int) ((((float) batteryStatus.getIntExtra("level", -1)) / ((float) batteryStatus.getIntExtra("scale", -1))) * 100.0f);
    }

    @SimpleFunction(description = "Returns the device the phone is plugged to. Can be 'USB', 'AC', 'WIRELESS' or 'UNKNOWN'.")
    public String BatteryPluggedSource() {
        Intent batteryStatus = getBatteryStatus();
        batteryStatus.getIntExtra("plugged", -1);
        String stringExtra = batteryStatus.getStringExtra("plugged");
        return stringExtra == Component.TYPEFACE_SANSSERIF ? "AC" : stringExtra == Component.TYPEFACE_SERIF ? "USB" : stringExtra == "4" ? "WIRELESS" : "UNKNOWN";
    }

    @SimpleFunction(description = "Returns the current status of the phone's battery.")
    public final String BatteryStatus() {
        Intent batteryStatus = getBatteryStatus();
        int i = 0;
        if (batteryStatus != null) {
            i = batteryStatus.getIntExtra(NotificationCompat.CATEGORY_STATUS, 0);
        }
        return i == 1 ? "UNKNOWN" : i == 2 ? "CHARGING" : i == 3 ? "DISCHARGING" : i == 4 ? "NOT_CHARGING" : i == 5 ? "FULL" : "UNKNOWN";
    }

    @SimpleFunction(description = "Returns the battery technology.")
    public String BatteryTechnology() {
        return getBatteryStatus().getStringExtra("technology");
    }

    @SimpleFunction(description = "Returns the current battery voltage.")
    public int BatteryVoltage() {
        Intent batteryStatus = getBatteryStatus();
        if (batteryStatus != null) {
            return batteryStatus.getIntExtra("voltage", 0);
        }
        return 0;
    }

    @SimpleFunction(description = "Returns the name of the underlying board, like goldfish.")
    public String Board() {
        return Build.BOARD;
    }

    @SimpleFunction(description = "Returns the system bootloader version number.")
    public String Bootloader() {
        return Build.BOOTLOADER;
    }

    @SimpleFunction(description = "Returns the consumer-visible brand with which the product/hardware will be associated, if any.")
    public String Brand() {
        return Build.BRAND;
    }

    @SimpleFunction(description = "Returns the absolute path of the cache directory of the application.")
    public String CacheDirectory() {
        return this.activity.getCacheDir().getAbsolutePath();
    }

    @SimpleFunction(description = "Convert a hex color to a integer color. The result is returned as integer. This function does not support shorthand hex values like #000.")
    public int ColorConvertHexToInt(String str) {
        return Color.parseColor(str);
    }

    @SimpleFunction(description = "Convert a integer color to a hex color. The result is returned as string.")
    public String ColorConvertIntToHex(int i) {
        return String.format("#%06X", new Object[]{Integer.valueOf(i & 16777215)});
    }

    @SimpleFunction(description = "Returns the user's current latitude.")
    public double CurrentLatitude() {
        if (getLocation() == null) {
            return 0.0d;
        }
        return getLocation().getLatitude();
    }

    @SimpleFunction(description = "Returns the user's current longitude.")
    public double CurrentLongitude() {
        if (getLocation() == null) {
            return 0.0d;
        }
        return getLocation().getLongitude();
    }

    @SimpleFunction(description = "Returns the ID of the device.")
    public String DeviceId() {
        return Settings.Secure.getString(this.context.getContentResolver(), "android_id");
    }

    @SimpleFunction(description = "Turns off Bluetooth without user interaction.")
    public void DisableBluetooth() {
        BluetoothAdapter.getDefaultAdapter().disable();
    }

    @SimpleFunction(description = "Returns a build ID string meant for displaying to the user.")
    public String Display() {
        return Build.DISPLAY;
    }

    @SimpleFunction(description = "Returns a name for the locale's country that is appropriate for display to the user.")
    public String DisplayCountry() {
        return (Locale.getDefault().getDisplayCountry() == "Hong Kong" || Locale.getDefault().getDisplayCountry() == "Taiwan" || Locale.getDefault().getDisplayCountry() == "Macau") ? "China" : Locale.getDefault().getDisplayCountry();
    }

    @SimpleFunction(description = "Returns the font scale of the display.")
    public float DisplayFontScale() {
        return this.context.getResources().getConfiguration().fontScale;
    }

    @SimpleFunction(description = "Returns the display height of the device.")
    public int DisplayHeight() {
        return this.context.getResources().getDisplayMetrics().heightPixels + NavigationBarHeight();
    }

    @SimpleFunction(description = "Returns a name for the locale's language that is appropriate for display to the user.")
    public String DisplayLanguage() {
        return Locale.getDefault().getDisplayLanguage();
    }

    @SimpleFunction(description = "Returns the physical size of the device.")
    public double DisplayPhysicalSize() {
        int i = this.context.getResources().getDisplayMetrics().widthPixels;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        float f = (float) i;
        return Math.sqrt(Math.pow((double) (f / displayMetrics.xdpi), 2.0d) + Math.pow((double) (f / displayMetrics.ydpi), 2.0d));
    }

    @SimpleFunction(description = "Returns the refresh rate of the display in frames per second.")
    public float DisplayRefreshRate() {
        return ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay().getRefreshRate();
    }

    @SimpleFunction(description = "Returns the rotation of the screen from its 'natural' orientation. The returned value may be Surface.ROTATION_0 (no rotation), Surface.ROTATION_90, Surface.ROTATION_180, or Surface.ROTATION_270.For example, if a device has a naturally tall screen, and the user has turned it on its side to go into a landscape orientation, the value returned here may be either Surface.ROTATION_90 or Surface.ROTATION_270 depending on the direction it was turned. The angle is the rotation of the drawn graphics on the screen, which is the opposite direction of the physical rotation of the device.")
    public int DisplayRotation() {
        switch (((WindowManager) this.context.getSystemService("window")).getDefaultDisplay().getRotation()) {
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return 270;
            default:
                return 0;
        }
    }

    @SimpleFunction(description = "Returns the screen timeout time of the device.")
    public int DisplayScreenTimeout() {
        try {
            return Settings.System.getInt(this.context.getContentResolver(), "screen_off_timeout");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @SimpleFunction(description = "Returns a name for the the locale's script that is appropriate for display to the user.")
    public String DisplayScript() {
        return Locale.getDefault().getDisplayScript();
    }

    @SimpleFunction(description = "Returns the display width of the device.")
    public int DisplayWidth() {
        return this.context.getResources().getDisplayMetrics().widthPixels;
    }

    @SimpleFunction(description = "Turns on Bluetooth without user interaction.")
    public void EnableBluetooth() {
        BluetoothAdapter.getDefaultAdapter().enable();
    }

    @SimpleFunction(description = "Returns the available storage size in bytes.")
    public long ExternalStorageAvailable() {
        if (!IsExternalMemoryAvailable()) {
            return -1111;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
    }

    @SimpleFunction(description = "Returns the total amount of external storage in bytes.")
    public long ExternalStorageTotal() {
        if (!IsExternalMemoryAvailable()) {
            return -1111;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return statFs.getBlockSizeLong() * statFs.getBlockCountLong();
    }

    @SimpleFunction(description = "Returns the used amount of external storage in bytes.")
    public long ExternalStorageUsed() {
        if (!IsExternalMemoryAvailable() || ExternalStorageTotal() <= ExternalStorageAvailable()) {
            return -1111;
        }
        return ExternalStorageTotal() - ExternalStorageAvailable();
    }

    @SimpleFunction(description = "Returns the fingerprint of the device.")
    public String Fingerprint() {
        return Build.FINGERPRINT;
    }

    @SimpleFunction(description = "Attempts to calculate the remaining charging time and triggers the GotChargingTime event.")
    public void GetBatteryChargingTime() {
        long computeChargeTimeRemaining = ((BatteryManager) this.context.getSystemService("batterymanager")).computeChargeTimeRemaining();
        if (this.context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("plugged", -1) == 2) {
        }
        GotChargingTime(computeChargeTimeRemaining);
    }

    @SimpleFunction(description = "Returns the city name from the given latitude and the longitude.")
    public String GetCity(double d, double d2) {
        return getStringAddress(d, d2, 2);
    }

    @SimpleFunction(description = "Returns the name of the country from the given latitude and longitude.")
    public String GetCountryName(double d, double d2) {
        return getStringAddress(d, d2, 3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0016 A[Catch:{ SocketException -> 0x0063 }] */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Returns the IP address.")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String GetIpAddress(boolean r8) {
        /*
            r7 = this;
            r0 = 1
            r1 = 0
            java.util.Enumeration r2 = java.net.NetworkInterface.getNetworkInterfaces()     // Catch:{ SocketException -> 0x0063 }
            java.util.ArrayList r2 = java.util.Collections.list(r2)     // Catch:{ SocketException -> 0x0063 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ SocketException -> 0x0063 }
            java.lang.String r3 = ""
        L_0x0010:
            boolean r4 = r2.hasNext()     // Catch:{ SocketException -> 0x0063 }
            if (r4 == 0) goto L_0x00aa
            java.lang.Object r4 = r2.next()     // Catch:{ SocketException -> 0x0063 }
            java.net.NetworkInterface r4 = (java.net.NetworkInterface) r4     // Catch:{ SocketException -> 0x0063 }
            java.util.Enumeration r4 = r4.getInetAddresses()     // Catch:{ SocketException -> 0x0063 }
            java.util.ArrayList r4 = java.util.Collections.list(r4)     // Catch:{ SocketException -> 0x0063 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ SocketException -> 0x0063 }
        L_0x0028:
            boolean r5 = r4.hasNext()     // Catch:{ SocketException -> 0x0063 }
            if (r5 == 0) goto L_0x0010
            java.lang.Object r5 = r4.next()     // Catch:{ SocketException -> 0x0063 }
            java.net.InetAddress r5 = (java.net.InetAddress) r5     // Catch:{ SocketException -> 0x0063 }
            boolean r6 = r5.isLoopbackAddress()     // Catch:{ SocketException -> 0x0063 }
            if (r6 != 0) goto L_0x0028
            java.lang.String r5 = r5.getHostAddress()     // Catch:{ SocketException -> 0x0063 }
            r6 = 58
            int r6 = r5.indexOf(r6)     // Catch:{ SocketException -> 0x0063 }
            if (r6 >= 0) goto L_0x0048
            r6 = 1
            goto L_0x0049
        L_0x0048:
            r6 = 0
        L_0x0049:
            if (r8 == 0) goto L_0x004f
            if (r6 == 0) goto L_0x0028
            r3 = r5
            goto L_0x0028
        L_0x004f:
            if (r6 != 0) goto L_0x0028
            r3 = 37
            int r3 = r5.indexOf(r3)     // Catch:{ SocketException -> 0x0063 }
            if (r3 >= 0) goto L_0x005e
            java.lang.String r3 = r5.toUpperCase()     // Catch:{ SocketException -> 0x0063 }
            goto L_0x0028
        L_0x005e:
            java.lang.String r3 = r5.substring(r1, r3)     // Catch:{ SocketException -> 0x0063 }
            goto L_0x0028
        L_0x0063:
            r8 = move-exception
            r8.printStackTrace()
            android.content.Context r8 = r7.context
            java.lang.String r2 = "wifi"
            java.lang.Object r8 = r8.getSystemService(r2)
            android.net.wifi.WifiManager r8 = (android.net.wifi.WifiManager) r8
            android.net.wifi.WifiInfo r8 = r8.getConnectionInfo()
            int r8 = r8.getIpAddress()
            r2 = 4
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = r8 & 255(0xff, float:3.57E-43)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r1] = r3
            int r1 = r8 >> 8
            r1 = r1 & 255(0xff, float:3.57E-43)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r2[r0] = r1
            int r0 = r8 >> 16
            r0 = r0 & 255(0xff, float:3.57E-43)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1 = 2
            r2[r1] = r0
            int r8 = r8 >> 24
            r8 = r8 & 255(0xff, float:3.57E-43)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r0 = 3
            r2[r0] = r8
            java.lang.String r8 = "%d.%d.%d.%d"
            java.lang.String r3 = java.lang.String.format(r8, r2)
        L_0x00aa:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gordonlu.device.Device.GetIpAddress(boolean):java.lang.String");
    }

    @SimpleFunction(description = "Returns the MAC address(media access control address) of the device.")
    public String GetMacAddress() {
        return ((WifiManager) this.context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
    }

    @SimpleFunction(description = "Returns the number of processors available to the Java virtual machine.")
    public int GetNumberOfCores() {
        return Runtime.getRuntime().availableProcessors();
    }

    @SimpleFunction(description = "Returns the postal code from the latitude and the longitude.")
    public String GetPostalCode(double d, double d2) {
        return getStringAddress(d, d2, 4);
    }

    @SimpleFunction(description = "Returns the street address from the given latitude and the longitude.")
    public String GetStreetAddress(double d, double d2) {
        return getStringAddress(d, d2, 1);
    }

    @SimpleEvent(description = "Triggered when finished calculating the remaining charging time. Returns -1 when an error is occured.")
    public void GotChargingTime(long j) {
        EventDispatcher.dispatchEvent(this, "GotChargingTime", Long.valueOf(j));
    }

    @SimpleFunction(description = "Returns the name of the hardware (from the kernel command line or /proc).")
    public String Hardware() {
        return Build.HARDWARE;
    }

    @SimpleFunction(description = "Returns to the home screen of the application. Your application is not closed.")
    public void Home() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setFlags(Declaration.PUBLIC_ACCESS);
        intent.addCategory("android.intent.category.HOME");
        this.context.startActivity(intent);
    }

    @SimpleFunction(description = "Returns either a changelist number, or a label like M4-rc20.")
    public String Id() {
        return Build.ID;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the internal value used by the underlying source control to represent this build.")
    public String Incremental() {
        return Build.VERSION.INCREMENTAL;
    }

    @SimpleFunction(description = "Returns the package name of the app that has installled your application.")
    public String InstallationFromAppStore() {
        return this.context.getPackageManager().getInstallerPackageName(this.context.getPackageName());
    }

    @SimpleFunction(description = "Returns a list of all installed applications.")
    public List InstalledApplications() {
        return this.context.getPackageManager().getInstalledApplications(128);
    }

    @SimpleFunction(description = "Returns the available internal memory in bytes.")
    public long InternalMemoryAvailable() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
    }

    @SimpleFunction(description = "Returns the total internal memory size in bytes.")
    public long InternalMemoryTotal() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return statFs.getBlockSizeLong() * statFs.getBlockCountLong();
    }

    @SimpleFunction(description = "Returns the used internal memory in bytes.")
    public long InternalMemoryUsed() {
        if (InternalMemoryTotal() > InternalMemoryAvailable()) {
            return InternalMemoryTotal() - InternalMemoryAvailable();
        }
        return -11111111;
    }

    @SimpleFunction(description = "Checks if ADB debugging is enabled, which might be a sign of hacking your app.")
    public boolean IsAdbDebuggingEnabled() {
        return Settings.Secure.getInt(this.context.getContentResolver(), "adb_enabled", 0) > 0;
    }

    @SimpleFunction(description = "Checks if the airplane mode of the device is on.")
    public boolean IsAirplaneModeEnabled() {
        return Settings.System.getInt(this.context.getContentResolver(), "airplane_mode_on", 0) == 1;
    }

    @SimpleFunction(description = "Indicating whether a battery is present.")
    public boolean IsBatteryPresent() {
        return getBatteryStatus().getExtras() != null && getBatteryStatus().getExtras().getBoolean("present");
    }

    @SimpleFunction(description = "Returns true if Bluetooth is on, else false.")
    public boolean IsBluetoothOn() {
        return true;
    }

    @SimpleFunction(description = "Returns true if the device's camera is available, else false.")
    public boolean IsCameraAvailable() {
        return this.context.getPackageManager().hasSystemFeature("android.hardware.camera.any");
    }

    @SimpleFunction(description = "Returns true if the color is dark, else it returns false, means the color is light. The result is returned as boolean.")
    public boolean IsDarkColor(int i) {
        double red = (double) Color.red(i);
        Double.isNaN(red);
        double green = (double) Color.green(i);
        Double.isNaN(green);
        double d = (red * 0.299d) + (green * 0.587d);
        double blue = (double) Color.blue(i);
        Double.isNaN(blue);
        return 1.0d - ((d + (blue * 0.114d)) / 255.0d) >= 0.5d;
    }

    @SimpleFunction(description = "Checks if 'Automatic Date and Time' is enabled in Settings.")
    public boolean IsDateAndTimeAutomatic() {
        return Settings.Global.getInt(this.context.getContentResolver(), "auto_time", 0) == 1;
    }

    @SimpleFunction(description = "Tests if the email address is valid.")
    public boolean IsEmailAddressVaild(String str) {
        return Patterns.EMAIL_ADDRESS.matcher(str).matches();
    }

    @SimpleFunction(description = "Checks if the app is operating on an emulator.")
    public boolean IsEmulator() {
        return Build.BOARD.toLowerCase().contains("nox") || Build.MANUFACTURER.toLowerCase().contains("Andy") || Build.MANUFACTURER.toLowerCase().contains("MIT") || Build.MANUFACTURER.toLowerCase().contains("nox") || Build.MANUFACTURER.toLowerCase().contains("TiantianVM") || Build.PRODUCT.toLowerCase().contains("sdk_google") || Build.FINGERPRINT.toLowerCase().contains("ttVM_Hdragon") || Build.MODEL.toLowerCase().contains("Droid4X") || Build.PRODUCT.toLowerCase().contains("Andy") || Build.MANUFACTURER.equalsIgnoreCase(EnvironmentCompat.MEDIA_UNKNOWN) || Build.MANUFACTURER.equalsIgnoreCase("Genymotion") || Build.BOOTLOADER.toLowerCase().contains("nox") || Build.BRAND.equalsIgnoreCase("generic") || Build.BRAND.equalsIgnoreCase("generic_x86") || Build.BRAND.equalsIgnoreCase("TTVM") || Build.BRAND.toLowerCase().contains("Andy") || Build.DEVICE.toLowerCase().contains("generic") || Build.DEVICE.toLowerCase().contains("generic_x86") || Build.MODEL.equalsIgnoreCase("sdk") || Build.MODEL.equalsIgnoreCase("google_sdk") || Build.PRODUCT.toLowerCase().contains("google_sdk") || Build.FINGERPRINT.toLowerCase().contains("Andy") || Build.PRODUCT.toLowerCase().contains("nox") || Build.MODEL.contains("Emulator") || Build.PRODUCT.toLowerCase().contains("sdk_x86") || Build.PRODUCT.toLowerCase().contains("Droid4X") || Build.MODEL.toLowerCase().contains("TiantianVM") || Build.FINGERPRINT.toLowerCase().contains("generic_x86_64") || Build.FINGERPRINT.toLowerCase().contains("generic/google_sdk/generic") || Build.FINGERPRINT.toLowerCase().contains("vbox86p") || Build.FINGERPRINT.toLowerCase().contains("generic/vbox86p/vbox86p") || Build.PRODUCT.toLowerCase().contains("vbox86p") || Build.PRODUCT.toLowerCase().contains("ttVM_Hdragon") || Build.MODEL.toLowerCase().contains("Andy") || Build.MODEL.equalsIgnoreCase("Android SDK built for x86_64") || Build.MODEL.equalsIgnoreCase("Android SDK built for x86") || Build.FINGERPRINT.startsWith(EnvironmentCompat.MEDIA_UNKNOWN) || Build.HARDWARE.equalsIgnoreCase("goldfish") || Build.HARDWARE.equalsIgnoreCase("vbox86") || Build.HARDWARE.toLowerCase().contains("nox") || Build.PRODUCT.toLowerCase().contains("sdk") || Build.FINGERPRINT.toLowerCase().contains("generic_x86/sdk_x86/generic_x86") || Build.DEVICE.toLowerCase().contains("Andy") || Build.DEVICE.toLowerCase().contains("ttVM_Hdragon") || Build.DEVICE.toLowerCase().contains("Droid4X") || Build.DEVICE.toLowerCase().contains("nox") || Build.DEVICE.toLowerCase().contains("generic_x86_64") || Build.DEVICE.toLowerCase().contains("vbox86p") || Build.FINGERPRINT.toLowerCase().contains("generic") || Build.FINGERPRINT.toLowerCase().contains("generic/sdk/generic") || Build.HARDWARE.toLowerCase().contains("ttVM_x86") || Build.SERIAL.toLowerCase().contains("nox");
    }

    @SimpleFunction(description = "Checks if external memory is available.")
    public boolean IsExternalMemoryAvailable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    @SimpleFunction(description = "Returns true if the device has a flashlight, else false.")
    public boolean IsFlashAvailable() {
        return this.context.getPackageManager().hasSystemFeature("android.hardware.camera.flash");
    }

    @SimpleFunction(description = "Returns whether the screen has a high dynamic rate. This only works in versions larger or Android 8.")
    public boolean IsHdrCapable() {
        new Configuration();
        return this.context.getResources().getConfiguration().isScreenHdr();
    }

    @SimpleFunction(description = "Tests if the application is installed from Google Play Store.")
    public boolean IsInstalledFromPlayStore() {
        ArrayList arrayList = new ArrayList(Arrays.asList(new String[]{"com.android.vending", "com.google.android.feedback"}));
        String installerPackageName = this.context.getPackageManager().getInstallerPackageName(this.context.getPackageName());
        return installerPackageName != null && arrayList.contains(installerPackageName);
    }

    @SimpleProperty(description = "Returns true if the location services are enabled, else false.")
    public boolean IsLocationServiceEnabled() {
        return Build.VERSION.SDK_INT >= 28 ? ((LocationManager) this.context.getSystemService("location")).isLocationEnabled() : Settings.Secure.getInt(this.context.getContentResolver(), "location_mode", 0) != 0;
    }

    @SimpleFunction(description = "Checks if the night mode of the device is on.")
    public boolean IsNightModeActive() {
        int defaultNightMode = AppCompatDelegate.getDefaultNightMode();
        if (defaultNightMode == 2) {
            return true;
        }
        if (defaultNightMode == 1) {
            return false;
        }
        switch (this.context.getResources().getConfiguration().uiMode & 48) {
            case 0:
            case 16:
                return false;
            case 32:
                return true;
            default:
                return false;
        }
    }

    @SimpleFunction(description = "Return whether the screen has a round shape. Apps may choose to change styling based on this property, such as the alignment or layout of text or informational icons.This block requires devices with Android 6 or higher.")
    public boolean IsScreenRound() {
        new Configuration();
        return this.context.getResources().getConfiguration().isScreenRound();
    }

    @SimpleFunction(description = "Return whether the screen has a wide color gamut and wide color gamut rendering is supported by this device. When true, it implies the screen is colorspace aware but not necessarily color-managed. The final colors may still be changed by the screen depending on user settings.")
    public boolean IsScreenWideColorGamut() {
        new Configuration();
        return this.context.getResources().getConfiguration().isScreenWideColorGamut();
    }

    @SimpleFunction(description = "Checks whether the SD card is available.")
    public boolean IsSdCardAvailable() {
        return Boolean.valueOf(Environment.isExternalStorageRemovable()).booleanValue() && Boolean.valueOf(Environment.getExternalStorageState().equals("mounted")).booleanValue();
    }

    @SimpleFunction(description = "Returns true if the current device supports SMS service.")
    public boolean IsSmsCapable() {
        return ((TelephonyManager) this.context.getSystemService("phone")).isSmsCapable();
    }

    @SimpleFunction(description = "Returns a three-letter abbreviation for this locale's country. If the country matches an ISO 3166-1 alpha-2 code, the corresponding ISO 3166-1 alpha-3 uppercase code is returned. If the locale doesn't specify a country, this will be the empty string.")
    public String Iso3Country() {
        return Locale.getDefault().getISO3Country();
    }

    @SimpleFunction(description = "Returns a three-letter abbreviation of this locale's language. If the language matches an ISO 639-1 two-letter code, the corresponding ISO 639-2/T three-letter lowercase code is returned. The ISO 639-2 language codes can be found on-line, see 'Codes for the Representation of Names of Languages Part 2: Alpha-3 Code'. If the locale specifies a three-letter language, the language is returned as is. If the locale does not specify a language the empty string is returned.")
    public String Iso3Language() {
        return Locale.getDefault().getISO3Language();
    }

    @SimpleFunction(description = "Returns the Kernel version of the device.")
    public String KernelVersion() {
        Process process;
        try {
            process = Runtime.getRuntime().exec("uname -a");
        } catch (IOException e) {
            e.printStackTrace();
            process = null;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.waitFor() == 0 ? process.getInputStream() : process.getErrorStream()));
            String readLine = bufferedReader.readLine();
            bufferedReader.close();
            return readLine;
        } catch (InterruptedException e2) {
            e2.printStackTrace();
            return "Error";
        } catch (IOException e3) {
            e3.printStackTrace();
            return "Error";
        }
    }

    @SimpleFunction(description = "Returns a well-formed IETF BCP 47 language tag representing this locale.")
    public String LanguageTag() {
        return Locale.getDefault().toLanguageTag();
    }

    @SimpleFunction(description = "Returns the manufacturer of the product or hardware.")
    public String Manufacturer() {
        return Build.MANUFACTURER;
    }

    @SimpleFunction(description = "Returns the available RAM on the device in bytes.")
    public long MemoryFree() {
        return memoryInfo().availMem;
    }

    @SimpleFunction(description = "Returns the total RAM on the device in bytes.")
    public long MemoryTotal() {
        return memoryInfo().totalMem;
    }

    @SimpleFunction(description = "Returns the used RAM on the device in bytes.")
    public long MemoryUsed() {
        return memoryInfo().totalMem - memoryInfo().availMem;
    }

    @SimpleFunction(description = "Returns the name of the device.")
    public String ModelName() {
        return Build.MODEL;
    }

    public int NBC() {
        Window window = this.activity.getWindow();
        window.clearFlags(Declaration.PUBLIC_ACCESS);
        window.addFlags(Integer.MIN_VALUE);
        return window.getNavigationBarColor();
    }

    @SimpleFunction(description = "Returns the height of the navigation bar of the device.")
    public int NavigationBarHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) this.context.getSystemService("window");
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        int i2 = displayMetrics.heightPixels;
        if (i2 > i) {
            return i2 - i;
        }
        return 0;
    }

    @SimpleFunction(description = "Returns the number of cameras available on the device.")
    public int NumberOfCameras() {
        return getCameraIds().length;
    }

    @SimpleFunction(description = "Converts a number to a string.")
    public String NumberToString(int i) {
        return Integer.toString(i);
    }

    @SimpleFunction(description = "Returns the name of the overall product.")
    public String Product() {
        return Build.PRODUCT;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the user-visible version string. E.g., '1.0' or '3.4b5' or 'bananas'. This field is an opaque string. Do not assume that its value has any particular structure or that values of RELEASE from different releases can be somehow ordered.")
    public String Release() {
        return Build.VERSION.RELEASE;
    }

    @SimpleProperty(description = "Returns the current development codename, or the string 'REL' if this is a release build.")
    public String SdkCodeName() {
        return Build.VERSION.CODENAME;
    }

    @SimpleProperty(description = "Returns the SDK version of the software currently running on this hardware device.")
    public int SdkVersion() {
        return Build.VERSION.SDK_INT;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the user-visible security patch level. This value represents the date when the device most recently applied a security patch.")
    public String SecurityPatch() {
        return Build.VERSION.SECURITY_PATCH;
    }

    @SimpleFunction(description = "Returns the total amount of sensors on the device.")
    public int SensorAmount() {
        return SensorList().size();
    }

    @SimpleFunction(description = "Returns a list of all sensors on the device.")
    public List<Sensor> SensorList() {
        return ((SensorManager) this.context.getSystemService("sensor")).getSensorList(-1);
    }

    @SimpleFunction(description = "Resets the navigation bar color to the given color. ")
    public void SetNavigationBarColor(int i) {
        Window window = this.activity.getWindow();
        window.clearFlags(Declaration.PUBLIC_ACCESS);
        window.addFlags(Integer.MIN_VALUE);
        window.setNavigationBarColor(i);
    }

    @SimpleFunction(description = "Resets the status bar color to the given color. ")
    public void SetStatusBarColor(int i) {
        Window window = this.activity.getWindow();
        window.clearFlags(Declaration.PUBLIC_ACCESS);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(i);
    }

    @SimpleFunction(description = "Sets the status bar color to the default or transparent color.")
    public void SetStatusBarColorDefault() {
        Window window = this.activity.getWindow();
        window.clearFlags(Declaration.PUBLIC_ACCESS);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(-16743563);
        window.setFlags(512, 512);
    }

    @SimpleFunction(description = "Returns the ISO-3166-1 alpha-2 country code equivalent for the SIM provider's country code.")
    public String SimCountryIso() {
        return ((TelephonyManager) this.context.getSystemService("phone")).getSimCountryIso();
    }

    @SimpleFunction(description = "Returns the MCC+MNC (mobile country code + mobile network code) of the provider of the SIM. 5 or 6 decimal digits.")
    public String SimOperator() {
        return ((TelephonyManager) this.context.getSystemService("phone")).getSimOperator();
    }

    @SimpleFunction(description = "Returns the Service Provider Name (SPN).")
    public String SimOperatorName() {
        return ((TelephonyManager) this.context.getSystemService("phone")).getSimOperatorName();
    }

    @SimpleFunction(description = "Converts a string to a number.")
    public int StringToNumber(String str) {
        return Integer.parseInt(str);
    }

    @SimpleFunction(description = "Returns a list of 32 bit ABIs supported by this device. The most preferred ABI is the first element in the list.")
    public List Supported32BitAbis() {
        return Arrays.asList(Build.SUPPORTED_32_BIT_ABIS);
    }

    @SimpleFunction(description = "Returns a list of 64 bit ABIs supported by this device. The most preferred ABI is the first element in the list.")
    public List Supported64BitAbis() {
        return Arrays.asList(Build.SUPPORTED_64_BIT_ABIS);
    }

    @SimpleFunction(description = "Returns a list of ABIs supported by this device. The most preferred ABI is the first element in the list.")
    public List SupportedAbis() {
        return Arrays.asList(Build.SUPPORTED_ABIS);
    }

    @SimpleFunction(description = "Returns comma-separated tags describing the build, like 'unsigned,debug'.")
    public String Tags() {
        return Build.TAGS;
    }

    @SimpleFunction(description = "Turns a string value into a hexadecimal value.")
    public String TextToHexadecimal(String str) {
        StringBuilder sb = new StringBuilder();
        for (char hexString : str.toCharArray()) {
            sb.append(Integer.toHexString(hexString));
        }
        return sb.toString();
    }

    @SimpleFunction(description = "Returns the type of build, like 'user' or 'eng'.")
    public String Type() {
        return Build.TYPE;
    }

    @SimpleFunction(description = "Returns the version code from the package name.")
    public int VersionCodeFrom(String str, String str2) {
        return Integer.parseInt(getApplicationInfo(str, 2, str2));
    }

    @SimpleFunction(description = "Returns the version name of an app from the package name.")
    public String VersionNameFrom(String str, String str2) {
        return getApplicationInfo(str, 1, str2);
    }

    public String[] getCameraIds() {
        try {
            return ((CameraManager) this.context.getSystemService("camera")).getCameraIdList();
        } catch (CameraAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Location getLocation() {
        String str;
        LocationManager locationManager = (LocationManager) this.context.getSystemService("location");
        if (!(ActivityCompat.checkSelfPermission(this.context, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this.context, "android.permission.ACCESS_COARSE_LOCATION") == 0)) {
            ActivityCompat.requestPermissions((Activity) this.context, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 1);
        }
        Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
        if (lastKnownLocation == null) {
            str = "network";
        } else if (lastKnownLocation != null) {
            return lastKnownLocation;
        } else {
            str = "passive";
        }
        return locationManager.getLastKnownLocation(str);
    }
}
