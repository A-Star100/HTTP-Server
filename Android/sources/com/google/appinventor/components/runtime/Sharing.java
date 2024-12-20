package com.google.appinventor.components.runtime;

import android.content.Intent;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.NanoHTTPD;
import com.google.appinventor.components.runtime.util.NougatUtil;
import java.io.File;

@SimpleObject
@DesignerComponent(category = ComponentCategory.SOCIAL, description = "Sharing is a non-visible component that enables sharing files and/or messages between your app and other apps installed on a device. The component will display a list of the installed apps that can handle the information provided, and will allow the user to choose one to share the content with, for instance a mail app, a social network app, a texting app, and so on.<br>The file path can be taken directly from other components such as the Camera or the ImagePicker, but can also be specified directly to read from storage. The default behaviour is to share files from the private data directory associated with your app. If the file path starts with a slash (/), the the file relative to / is shared.<br>Be aware that different devices treat storage differently, so a few things to try if, for instance, you have a file called arrow.gif in the folder <code>Appinventor/assets</code>, would be: <ul><li><code>\"file:///sdcard/Appinventor/assets/arrow.gif\"</code></li> or <li><code>\"/storage/Appinventor/assets/arrow.gif\"</code></li></ul>", iconName = "images/sharing.png", nonVisible = true, version = 1)
@UsesPermissions(permissionNames = "android.permission.READ_EXTERNAL_STORAGE")
public class Sharing extends AndroidNonvisibleComponent {
    public Sharing(ComponentContainer container) {
        super(container.$form());
    }

    @SimpleFunction(description = "Shares a message through any capable application installed on the phone by displaying a list of the available apps and allowing the user to choose one from the list. The selected app will open with the message inserted on it.")
    public void ShareMessage(String message) {
        Intent shareIntent = new Intent("android.intent.action.SEND");
        shareIntent.putExtra("android.intent.extra.TEXT", message);
        shareIntent.setType(NanoHTTPD.MIME_PLAINTEXT);
        this.form.startActivity(shareIntent);
    }

    @SimpleFunction(description = "Shares a file through any capable application installed on the phone by displaying a list of the available apps and allowing the user to choose one from the list. The selected app will open with the file inserted on it.")
    public void ShareFile(String file) {
        ShareFileWithMessage(file, "");
    }

    @SimpleFunction(description = "Shares both a file and a message through any capable application installed on the phone by displaying a list of available apps and allowing the user to  choose one from the list. The selected app will open with the file and message inserted on it.")
    public void ShareFileWithMessage(String file, String message) {
        if (!file.startsWith("file://")) {
            if (!file.startsWith("/")) {
                file = this.form.getDefaultPath(file);
            } else {
                file = "file://" + file;
            }
        }
        File imageFile = new File(Uri.parse(file).getPath());
        if (imageFile.isFile()) {
            String type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(file.substring(file.lastIndexOf(".") + 1).toLowerCase());
            if (type == null) {
                type = NanoHTTPD.MIME_DEFAULT_BINARY;
            }
            Uri shareableUri = NougatUtil.getPackageUri(this.form, imageFile);
            Intent shareIntent = new Intent("android.intent.action.SEND");
            shareIntent.putExtra("android.intent.extra.STREAM", shareableUri);
            shareIntent.setFlags(1);
            shareIntent.setType(type);
            if (message.length() > 0) {
                shareIntent.putExtra("android.intent.extra.TEXT", message);
            }
            this.form.startActivity(shareIntent);
            return;
        }
        String eventName = "ShareFile";
        if (message.equals("")) {
            eventName = "ShareFileWithMessage";
        }
        this.form.dispatchErrorOccurredEvent(this, eventName, ErrorMessages.ERROR_FILE_NOT_FOUND_FOR_SHARING, file);
    }
}
