package com.albert.orangepiinfo.core.beans.controllers;

import com.albert.orangepiinfo.requestresponsemodel.LoginModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("login",new LoginModel());
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute LoginModel loginModel){

        System.out.println(loginModel.toString());

        return "homepage";
    }
}
