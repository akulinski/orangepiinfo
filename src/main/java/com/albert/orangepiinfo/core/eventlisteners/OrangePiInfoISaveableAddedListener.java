package com.albert.orangepiinfo.core.eventlisteners;

import com.albert.orangepiinfo.sessionmanagment.events.ISavableAddEvent;
import com.albert.orangepiinfo.sessionmanagment.sessionstoring.ISavable;
import com.albert.orangepiinfo.sessionmanagment.sessionstoring.SessionStore;
import com.albert.orangepiinfo.sessionmanagment.sessionstringgeneration.SessionStringFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrangePiInfoISaveableAddedListener implements ApplicationListener<ISavableAddEvent> {

    @Autowired
    private SessionStore sessionStore;

    @Autowired
    private SessionStringFactory sessionStringFactory;

    @Override
    public void onApplicationEvent(ISavableAddEvent event) {

        String id = sessionStringFactory.generateSession();
        event.getOnSessionAddedCallback().getSessionId(id);
        sessionStore.saveISaveable(id,event.getToSave());

        for (Map.Entry<String, ISavable> entry:
                sessionStore.getSaveableHashMap().entrySet()) {
            System.out.println("------------------------------------------------------>"+entry.getKey());
        }

    }
}
