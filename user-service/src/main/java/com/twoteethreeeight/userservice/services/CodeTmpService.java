package com.twoteethreeeight.userservice.services;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.twoteethreeeight.userservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.twoteethreeeight.userservice.repositories.UserRepository;


@Service
public class CodeTmpService {
    private static final String CHARACTERS = "0123456789";

    @Autowired
    private UserRepository userRepository;

    
   private final StringRedisTemplate redisTemplate ;

    public CodeTmpService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

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
    @Cacheable(value = "codeTmp", key = "#email")
    public String generateCodeTmp(String email) {
        User user = userRepository.findByEmail(email);
        String code = initHasCode();
        redisTemplate.opsForValue().set(email, code, 10, TimeUnit.MINUTES);
        return code;
    }

    public boolean isExpirated(String email) {
//    	 String code = redisTemplate.opsForValue().get(email);
//        return LocalDateTime.now().isAfter(timeExpirate);
        return  true;
    }

    // kiem tra code
    public boolean validateCode(String email, String responseCode) {
    	String code = redisTemplate.opsForValue().get(email);
    	if (responseCode!= null && responseCode.equals(code) ) {
			redisTemplate.delete(email);
			return true;
		}
        return false;
    }


}
