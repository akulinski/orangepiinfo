package com.albert.orangepiinfo.sessionmanagment.events;

import com.albert.orangepiinfo.sessionmanagment.callbacks.CheckEventCallback;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class CheckSessionEvent extends ApplicationEvent {

    @Getter
    private String sessiondId;

    @Getter
    CheckEventCallback checkEventCallback;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public CheckSessionEvent(Object source, String sessiondId, CheckEventCallback checkEventCallback) {
        super(source);
        this.sessiondId = sessiondId;
        this.checkEventCallback = checkEventCallback;
    }
}
