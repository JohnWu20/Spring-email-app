package org.asnworks.apis.springemailexample.controller;

import org.asnworks.apis.springemailexample.model.Login;
import org.asnworks.apis.springemailexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName RegisterController
 * Description:
 * Author: Jialiang Wu
 *
 * @Date 2019-12-13 13:45
 * @Version 1.0
 **/

@RestController
@RequestMapping("/**")
public class RegisterController {
    private UserService userService;

    @Autowired
    public RegisterController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody Login user){
        if(!validate(user.getUserAddress()) || userService.isRegistered(user.getUserAddress())){
//            return ResponseEntity.ok(null);
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("ERROR");
        }
        else{
            userService.add(user);
            return ResponseEntity.ok("SUCCESS");
        }
    }
    // varify an email address
    public  final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public  boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
}