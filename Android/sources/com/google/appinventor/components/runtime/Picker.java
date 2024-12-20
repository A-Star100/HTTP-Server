package com.google.appinventor.components.runtime;

import android.content.Intent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.runtime.errors.StopBlocksExecution;
import com.google.appinventor.components.runtime.util.AnimationUtil;

@SimpleObject
public abstract class Picker extends ButtonBase implements ActivityResultListener {
    protected final ComponentContainer container;
    protected int requestCode;

    /* access modifiers changed from: protected */
    public abstract Intent getIntent();

    public Picker(ComponentContainer container2) {
        super(container2);
        this.container = container2;
    }

    public void click() {
        if (BeforePicking()) {
            if (this.requestCode == 0) {
                this.requestCode = this.container.$form().registerForActivityResult(this);
            }
            this.container.$context().startActivityForResult(getIntent(), this.requestCode);
            AnimationUtil.ApplyOpenScreenAnimation(this.container.$context(), this.container.$form().OpenScreenAnimation());
        }
    }

    @SimpleFunction(description = "Opens the %type%, as though the user clicked on it.")
    public void Open() {
        click();
    }

    @SimpleEvent
    public boolean BeforePicking() {
        return !(EventDispatcher.dispatchFallibleEvent(this, "BeforePicking", new Object[0]) instanceof StopBlocksExecution);
    }

    @SimpleEvent
    public void AfterPicking() {
        EventDispatcher.dispatchEvent(this, "AfterPicking", new Object[0]);
    }
}
