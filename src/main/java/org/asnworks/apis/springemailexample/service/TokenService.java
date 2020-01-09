package org.asnworks.apis.springemailexample.service;

import org.asnworks.apis.springemailexample.model.Login;
import org.asnworks.apis.springemailexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * ClassName TokenMapping
 * Description:
 * Author: Jialiang Wu
 *
 * @Date 2019-12-13 19:26
 * @Version 1.0
 **/
@Service
public class TokenService {

    private UserRepository repository;
    private Map<String, String> tokenMap;

    @Autowired
    TokenService(UserRepository repository){
        this.repository = repository;
        this.tokenMap = new HashMap<>();
    }

    public boolean checkToken(String token){
        if(token == null || token.length() == 0){
            return false;
        }
        return tokenMap.containsKey(token);
    }

    public String assignToken(Login user){
        String userToken = getToken();
        tokenMap.put(userToken, user.getUserAddress());
        return userToken;
    }

    public String getAddress(String token){
        return tokenMap.get(token);
    }

    private String getToken(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }


}