package com.albert.orangepiinfo.sessionmanagment.events;

import com.albert.orangepiinfo.sessionmanagment.callbacks.OnSessionAddedCallback;
import com.albert.orangepiinfo.sessionmanagment.sessionstoring.ISavable;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class ISavableAddEvent extends ApplicationEvent {

    @Getter
    private ISavable toSave;

    @Getter
    private OnSessionAddedCallback onSessionAddedCallback;

    public ISavableAddEvent(Object source, ISavable toSave, OnSessionAddedCallback onSessionAddedCallback) {
        super(source);
        this.toSave = toSave;
        this.onSessionAddedCallback = onSessionAddedCallback;
    }

}
