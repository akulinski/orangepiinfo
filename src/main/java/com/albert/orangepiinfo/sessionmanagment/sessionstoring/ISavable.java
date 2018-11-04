package com.albert.orangepiinfo.sessionmanagment.sessionstoring;


import com.albert.orangepiinfo.sessionmanagment.callbacks.OnSessionAddedCallback;
import org.springframework.context.ApplicationEventPublisher;

public interface ISavable {

    String saveSession(ApplicationEventPublisher applicationEventPublisher);
    boolean delateSession();
}
