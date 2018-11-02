package com.albert.orangepiinfo.core.beans.services;

import com.albert.orangepiinfo.core.beans.Entities.UserEntity;
import com.albert.orangepiinfo.core.beans.repositories.UserRepository;
import com.albert.orangepiinfo.requestresponsemodel.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public boolean validate(LoginModel loginModel) {

        UserEntity userEntity = null;

        userEntity = userRepository.getUserEntityByUsernameAndPassword(loginModel.getUsername(), loginModel.getPassword());

        if (userEntity != null) {
            return true;
        }

        return false;
    }
}
