package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.media.AudioManager;
import android.view.Display;
import android.webkit.WebViewClient;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.Player;
import java.io.IOException;

public class FroyoUtil {
    private FroyoUtil() {
    }

    public static int getRotation(Display display) {
        return display.getRotation();
    }

    public static AudioManager setAudioManager(Activity activity) {
        return (AudioManager) activity.getSystemService("audio");
    }

    public static Object setAudioFocusChangeListener(final Player player) {
        return new AudioManager.OnAudioFocusChangeListener() {
            private boolean playbackFlag = false;

            public void onAudioFocusChange(int focusChange) {
                switch (focusChange) {
                    case -3:
                    case -2:
                        Player player = Player.this;
                        if (player != null && player.playerState == Player.State.PLAYING) {
                            Player.this.pause();
                            this.playbackFlag = true;
                            return;
                        }
                        return;
                    case -1:
                        this.playbackFlag = false;
                        Player.this.OtherPlayerStarted();
                        return;
                    case 1:
                        Player player2 = Player.this;
                        if (player2 != null && this.playbackFlag && player2.playerState == Player.State.PAUSED_BY_EVENT) {
                            Player.this.Start();
                            this.playbackFlag = false;
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public static boolean focusRequestGranted(AudioManager am, Object afChangeListener) {
        if (am.requestAudioFocus((AudioManager.OnAudioFocusChangeListener) afChangeListener, 3, 1) == 1) {
            return true;
        }
        return false;
    }

    public static void abandonFocus(AudioManager am, Object afChangeListener) {
        am.abandonAudioFocus((AudioManager.OnAudioFocusChangeListener) afChangeListener);
    }

    public static WebViewClient getWebViewClient(boolean ignoreErrors, boolean followLinks, Form form, Component component) {
        return new FroyoWebViewClient(followLinks, ignoreErrors, form, component);
    }

    public static void throwIOException(Throwable e) throws IOException {
        throw new IOException(e.toString());
    }
}
