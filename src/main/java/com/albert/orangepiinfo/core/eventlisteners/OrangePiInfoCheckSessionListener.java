package com.albert.orangepiinfo.core.eventlisteners;

import com.albert.orangepiinfo.sessionmanagment.events.CheckSessionEvent;
import com.albert.orangepiinfo.sessionmanagment.sessionstoring.SessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OrangePiInfoCheckSessionListener implements ApplicationListener<CheckSessionEvent> {

    @Autowired
    private SessionStore sessionStore;

    private Logger logger = LoggerFactory.getLogger(OrangePiInfoCheckSessionListener.class);

    @Override
    public void onApplicationEvent(CheckSessionEvent event) {

        if (sessionStore.sessionExists(event.getSessiondId())){
            event.getCheckEventCallback().setValue(true);
        }else{
            event.getCheckEventCallback().setValue(false);
        }
    }
}
