package com.albert.orangepiinfo.sessionmanagment.callbacks.implementations;

import com.albert.orangepiinfo.sessionmanagment.callbacks.CheckEventCallback;

public class CheckEventCallbackImpl implements CheckEventCallback {

    private boolean value;

    @Override
    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean getValue() {
        return this.value;
    }
}
