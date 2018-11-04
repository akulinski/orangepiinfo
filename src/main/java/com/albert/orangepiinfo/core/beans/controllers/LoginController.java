package com.albert.orangepiinfo.core.beans.controllers;

import com.albert.orangepiinfo.core.beans.Entities.UserEntity;
import com.albert.orangepiinfo.core.beans.repositories.UserRepository;
import com.albert.orangepiinfo.core.beans.services.CookieValidator;
import com.albert.orangepiinfo.core.beans.services.LoginService;
import com.albert.orangepiinfo.requestresponsemodel.LoginModel;
import com.albert.orangepiinfo.sessionmanagment.callbacks.CheckEventCallback;
import com.albert.orangepiinfo.sessionmanagment.callbacks.implementations.CheckEventCallbackImpl;
import com.albert.orangepiinfo.sessionmanagment.events.CheckSessionEvent;
import com.albert.orangepiinfo.sessionmanagment.sessionstoring.SessionStore;
import com.albert.orangepiinfo.util.processmanagment.ProcessWrapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ProcessWrapperFactory processWrapperFactory;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private CookieValidator cookieValidator;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("login", new LoginModel());
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute LoginModel loginModel, Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws UnsupportedEncodingException {

        logger.debug(loginModel.toString());

        String validation = loginService.validate(loginModel);

        if (!validation.equals("")) {
            UserEntity userEntity = userRepository.getUserEntityByUsernameAndPassword(loginModel.getUsername(), loginModel.getPassword());
            model.addAttribute("userEntity", userEntity);

            Cookie cookie = new Cookie("sesionid", URLEncoder.encode(validation, "UTF-8"));
            cookie.setPath(httpServletRequest.getContextPath());
            httpServletResponse.addCookie(cookie);

            httpServletRequest.setAttribute("sesionid", URLEncoder.encode(validation, "UTF-8"));

            return "userhome";
        }

        try {
            httpServletResponse.sendRedirect("/login");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "login";
    }

    @GetMapping(value = "/getCommand", produces = "application/json")
    public ResponseEntity<String> getCommand(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        String sessionid = null;
        if (httpServletRequest.getSession().getAttribute("sessionid") != null) {
            sessionid = httpServletRequest.getSession().getAttribute("sessionid").toString();
        }

        CheckEventCallback checkEventCallback = new CheckEventCallbackImpl();
        applicationEventPublisher.publishEvent(new CheckSessionEvent(this, sessionid, checkEventCallback));


        if (checkEventCallback.getValue() || cookieValidator.validate(httpServletRequest.getCookies())) {
            String retValue = processWrapperFactory.getWrapper("ipconfig").execCommand();
            return new ResponseEntity<String>(retValue, HttpStatus.ACCEPTED);
        }else{
            httpServletResponse.sendRedirect("login");
        }

        return null;
    }
}
