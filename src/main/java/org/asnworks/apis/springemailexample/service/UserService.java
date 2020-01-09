package org.asnworks.apis.springemailexample.service;


import org.asnworks.apis.springemailexample.model.Email;
import org.asnworks.apis.springemailexample.model.Login;
import org.asnworks.apis.springemailexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * ClassName UserSource
 * Description:
 * Author: Jialiang Wu
 *
 * @Date 2019-12-12 21:06
 * @Version 1.0
 **/

@Service
public class UserService {
    private UserRepository repository;


    @Autowired // annotation a constructor
    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public boolean isAuth(String emailAddress, String password){
        return repository.existsAllByPassWordAndUserAddress(password, emailAddress);
    }
    public boolean isRegistered(String emailAddress){
        if(repository.existsAllByUserAddress(emailAddress)){
            return true;
        }
        else{
            return false;
        }
    }

    public void add(Login user){
        repository.save(toUser(user));
    }

    private Login toUser(Login user){
        Login newUser = new Login();
        newUser.setPassWord(user.getPassWord());
        newUser.setUserAddress(user.getUserAddress());
        newUser.setUserEmailID(getEmailID());
        return newUser;
    }

    private String getEmailID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}