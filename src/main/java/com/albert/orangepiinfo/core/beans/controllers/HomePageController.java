package com.albert.orangepiinfo.core.beans.controllers;

import com.albert.orangepiinfo.sessionmanagment.callbacks.CheckEventCallback;
import com.albert.orangepiinfo.sessionmanagment.callbacks.implementations.CheckEventCallbackImpl;
import com.albert.orangepiinfo.sessionmanagment.events.CheckSessionEvent;
import com.albert.orangepiinfo.util.processmanagment.ProcessWrapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HomePageController {

    @Autowired
    private ProcessWrapperFactory processWrapperFactory;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String getHomePage(Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        String sessionid = null;
        if(httpServletRequest.getSession().getAttribute("sessionid") != null){
            sessionid = httpServletRequest.getSession().getAttribute("sessionid").toString();
        }

        if(sessionid == null ){

            try {
                httpServletResponse.sendRedirect("/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else{
            CheckEventCallback checkEventCallback = new CheckEventCallbackImpl();
            applicationEventPublisher.publishEvent(new CheckSessionEvent(this,sessionid,checkEventCallback));
            if (checkEventCallback.getValue()){
                System.out.println("Valid Session m8");
            }
        }

        try {
            model.addAttribute("result", processWrapperFactory.getWrapper("ipconfig").execCommand());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "homepage";
    }

}
