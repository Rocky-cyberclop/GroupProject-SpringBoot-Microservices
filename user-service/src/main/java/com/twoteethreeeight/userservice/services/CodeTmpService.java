package com.twoteethreeeight.userservice.services;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;

import com.twoteethreeeight.userservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.twoteethreeeight.userservice.repositories.UserRepository;


@Service
public class CodeTmpService {
    private static final String CHARACTERS = "0123456789";

    @Autowired
    private UserRepository userRepository;

    // khoi tao code
    public String initHasCode() {
        // sử dụng thư viện  là một lớp trong gói java.security
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }
        return code.toString();
    }

    public LocalDateTime initExpiratedTime() {
        return LocalDateTime.now().plusMinutes(5);
    }

    // tao moi code voi thoi gian hop le
    public String generateCodeTmp(String email) {
        User user = userRepository.findByEmail(email);
        String code = initHasCode();
        LocalDateTime expirationTime = initExpiratedTime();
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setIsRegister(true);
            user.setCodeAuthenticate(code, expirationTime);
        }
        else if(userRepository.existsByEmail(email)){
            user.setCodeAuthenticate(code, expirationTime);
            user.setIsRegister(false);
        }

        userRepository.save(user);
        return code;
    }

    public boolean isExpirated(String email) {

        User user = userRepository.findByEmail(email);
        LocalDateTime expiratedTime = user.getCodeAuthenticate().getExpiration();
        return LocalDateTime.now().isAfter(expiratedTime);
    }

    // kiem tra code
    public boolean validateCode(String email, String responseCode) {
        User user = userRepository.findByEmail(email);
        String codeTmp = user.getCodeAuthenticate().getCode();
        // kiem tra codetmp nhap vao
        if (codeTmp != null && codeTmp.equals(responseCode) && !isExpirated(email)) {
            // neu dung xoa code da luu va tra ve true
//            user.setCodeAuthenticate(null, null);
//            userRepository.save(user);
            return true;
        }
        return false;
    }


}
