package com.KIO4_SimpleWebServer;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.ComponentContainer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SimpleObject(external = true)
@DesignerComponent(category = ComponentCategory.EXTENSION, description = "Simple web server. Supplies an HTML file or a text. Juan Antonio Villalpando - KIO4.COM ", iconName = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAAEnQAABJ0Ad5mH3gAAAAZdEVYdFNvZnR3YXJlAEFkb2JlIEltYWdlUmVhZHlxyWU8AAABRUlEQVQ4T82TP0/CUBTFj848dlqQQGO6sGNiGCQODhoHPohfw+/A5I6LcXBCVge3DhBiGxBk57Hr/VdLVx30l9Cb3l7Ou+c0Pfgk8AsOrf6YfyIwnFwherjB0EtPGL9yj36TETLrwY9wbb0cEYgcX1PMd1yZFzytgUF4BmyXhcBuiYSra8gtIwL9gAaJmV9JhX/HDG0cB0foYIE32yzzC6mDoCuV0QwqPAgkno5l5KQmoloDMW32uFHhzKd0JeGK3Aoq4E5wWaVKJ/O6449nIDxFH11chLnwSjep9nAulhUVQF1zEL862HH0T6Ll2ia8xnxLDfLfkieKCdig+NXB2NWt31RhyaXsnykEaj3Kgd7ExgLMfUo+JLzhXMr+mW8BXi2mcj+9Q7LvU/JJcTulXDjYPf9MIWCBCSWflg8jwZb5668R+AJ1WXR9Wi8jUwAAAABJRU5ErkJggg==", nonVisible = true, version = 1)
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.ACCESS_NETWORK_STATE, android.permission.READ_EXTERNAL_STORAGE")
public class KIO4_SimpleWebServer extends AndroidNonvisibleComponent implements Component {
    public static final int VERSION = 1;
    private ComponentContainer container;
    private Context context;
    public File dir_files;
    public KIO4_SimpleWebServerCrear salida = new KIO4_SimpleWebServerCrear();

    public KIO4_SimpleWebServer(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        this.context = componentContainer.$context();
    }

    @SimpleFunction(description = "Get Android app directory. Example: /storage/emulated/0/Android/data/<namepackage>/files")
    public String GetAndroidAppDirectory() {
        File externalFilesDir = this.context.getExternalFilesDir((String) null);
        if (!externalFilesDir.exists()) {
            externalFilesDir.mkdirs();
        }
        return externalFilesDir.getPath();
    }

    @SimpleFunction(description = "Get your WiFi IP.")
    public String GetIp() {
        return Formatter.formatIpAddress(((WifiManager) this.context.getSystemService("wifi")).getConnectionInfo().getIpAddress());
    }

    @SimpleFunction(description = "Write an address from an HTML file in SdCard, example: /mnt/sdcard/index.htm. In Android 10+, file in Android app directory: /storage/emulated/0/Android/data/<namepackage>/files")
    public void ServeFile(String str) throws IOException {
        StringBuffer stringBuffer = new StringBuffer("");
        byte[] bArr = new byte[8192];
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(str);
            while (true) {
                try {
                    int read = fileInputStream2.read(bArr);
                    if (read != -1) {
                        stringBuffer.append(new String(bArr, 0, read));
                    } else {
                        String stringBuffer2 = stringBuffer.toString();
                        fileInputStream2.close();
                        this.salida.onCreate(stringBuffer2);
                        return;
                    }
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    fileInputStream.close();
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream.close();
            throw th;
        }
    }

    @SimpleFunction(description = "Write a text.")
    public void ServeText(String str) throws IOException {
        this.salida.onCreate(str);
    }

    @SimpleFunction(description = "Stop server.")
    public void StopServer() {
        this.salida.onDestroy();
    }
}
