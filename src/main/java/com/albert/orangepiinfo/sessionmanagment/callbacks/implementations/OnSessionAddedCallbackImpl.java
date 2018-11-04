package com.albert.orangepiinfo.sessionmanagment.callbacks.implementations;

import com.albert.orangepiinfo.sessionmanagment.callbacks.OnSessionAddedCallback;

public class OnSessionAddedCallbackImpl implements OnSessionAddedCallback {

    private String returnedId;

    @Override
    public void getSessionId(String id) {
        returnedId = id;
    }

    public String getReturnedId() {
        return returnedId;
    }


}
