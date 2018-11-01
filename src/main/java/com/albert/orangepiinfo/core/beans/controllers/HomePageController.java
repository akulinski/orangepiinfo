package com.albert.orangepiinfo.core.beans.controllers;

import com.albert.orangepiinfo.util.processmanagment.ProcessWrapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    ProcessWrapperFactory processWrapperFactory;

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String getHomePage(Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        if(httpServletRequest.getSession().getAttribute("sesionid") == null ){
            try {
                httpServletResponse.sendRedirect("/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        try {
            model.addAttribute("result", processWrapperFactory.getWrapper("ipconfig").execCommand());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "homepage";
    }
}
