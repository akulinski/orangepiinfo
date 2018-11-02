package com.albert.orangepiinfo.core.beans.controllers;

import com.albert.orangepiinfo.core.beans.Entities.UserEntity;
import com.albert.orangepiinfo.core.beans.repositories.UserRepository;
import com.albert.orangepiinfo.core.beans.services.LoginService;
import com.albert.orangepiinfo.requestresponsemodel.LoginModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("login", new LoginModel());
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute LoginModel loginModel, Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        logger.debug(loginModel.toString());

        if(loginService.validate(loginModel)){
            UserEntity userEntity = userRepository.getUserEntityByUsernameAndPassword(loginModel.getUsername(),loginModel.getPassword());
            model.addAttribute("userEntity",userEntity);

            Cookie cookie = new Cookie("sesionid","sesionid");
            cookie.setPath(httpServletRequest.getContextPath());
            httpServletResponse.addCookie(cookie);

            httpServletRequest.setAttribute("sesionid","sesionid");
            
            return "userhome";
        }

        try {
            httpServletResponse.sendRedirect("/login");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
