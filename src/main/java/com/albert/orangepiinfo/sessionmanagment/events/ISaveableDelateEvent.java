package com.albert.orangepiinfo.sessionmanagment.events;

import com.albert.orangepiinfo.sessionmanagment.sessionstoring.ISavable;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class ISaveableDelateEvent extends ApplicationEvent {

    @Getter
    private ISavable toDel;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ISaveableDelateEvent(Object source, ISavable toDel) {
        super(source);
        this.toDel = toDel;
    }
}
