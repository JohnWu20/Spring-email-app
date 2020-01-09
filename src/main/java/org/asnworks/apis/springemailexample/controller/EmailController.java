package org.asnworks.apis.springemailexample.controller;

import org.asnworks.apis.springemailexample.model.EmailWithReceivers;
import org.asnworks.apis.springemailexample.model.TokenEmail;
import org.asnworks.apis.springemailexample.service.EmailService;
import org.asnworks.apis.springemailexample.service.TokenService;
import org.asnworks.apis.springemailexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * ClassName EmailSenderController
 * Description:
 * Author: Jialiang Wu
 *
 * @Date 2019-12-13 16:52
 * @Version 1.0
 **/
@RestController
@RequestMapping("/**")
public class EmailController {
    private UserService userService;
    private EmailService emailService;
    private TokenService tokenService;

    @Autowired
    public EmailController(UserService userService, EmailService emailService, TokenService tokenService){
        this.emailService = emailService;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @RequestMapping(path = "/sender", method = RequestMethod.POST)
    public ResponseEntity emailSender(@RequestBody TokenEmail emailInfo) throws IOException, MessagingException {
        if(tokenService.checkToken(emailInfo.getToken())){
            if(emailInfo.getCommand().equals("SEND")){
                EmailWithReceivers curEmail = emailInfo.getEmail();
                //save information to the database
                emailService.add(emailInfo);
                //extract receivers' addresses
//            List<String> receivers = emailInfo.getEmail().getToUsers();
                emailService.emailSender(emailInfo.getEmail().getToUsers(), emailInfo.getEmail().getTitle(), emailInfo.getEmail().getBody());
                return ResponseEntity.ok("SUCCESS");
            }
            else if(emailInfo.getCommand().equals("CHECK")){
                return ResponseEntity.ok(emailService.emailPoller(emailInfo.getToken()));
            }
            else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ERROR");
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ERROR");
        }
    }
}
