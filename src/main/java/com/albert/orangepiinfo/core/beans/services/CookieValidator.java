package com.albert.orangepiinfo.core.beans.services;

import com.albert.orangepiinfo.sessionmanagment.callbacks.CheckEventCallback;
import com.albert.orangepiinfo.sessionmanagment.callbacks.implementations.CheckEventCallbackImpl;
import com.albert.orangepiinfo.sessionmanagment.events.CheckSessionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

@Component
public class CookieValidator {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    private Logger logger = LoggerFactory.getLogger(CookieValidator.class);

    public boolean validate(Cookie[] cookies) {

        for (Cookie cookie :
                cookies) {
            if (cookie.getName().equals("sesionid")) {
                CheckEventCallback checkEventCallback = new CheckEventCallbackImpl();

                applicationEventPublisher.publishEvent(new CheckSessionEvent(this, cookie.getValue(), checkEventCallback));

                if (checkEventCallback.getValue()) {
                    return true;
                }
            }
        }

        return false;
    }
}
