package org.asnworks.apis.springemailexample.controller;


import org.asnworks.apis.springemailexample.model.Login;
import org.asnworks.apis.springemailexample.service.TokenService;
import org.asnworks.apis.springemailexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName LoginController
 * Description:
 * Author: Jialiang Wu
 *
 * @Date 2019-12-12 20:52
 * @Version 1.0
 **/

@RestController
@RequestMapping("/**")
public class LoginController {
    private UserService userService;
    private TokenService tokenService;

    @Autowired
    public LoginController(UserService userService, TokenService tokenService){
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody Login user){
        if(userService.isAuth(user.getUserAddress(), user.getPassWord())){
            String token = tokenService.assignToken(user);
            return ResponseEntity.ok(token);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ERROR");
        }
    }
}