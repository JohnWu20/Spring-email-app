package org.asnworks.apis.springemailexample.model;

/**
 * ClassName TokenEmail
 * Description:
 * Author: Jialiang Wu
 *
 * @Date 2019-12-13 19:09
 * @Version 1.0
 **/

public class TokenEmail {
    private String command;
    private String token;
    private EmailWithReceivers email;
    TokenEmail(){}

    TokenEmail(EmailWithReceivers email, String token){
        this.email = email;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public EmailWithReceivers getEmail(){
        return email;
    }

    public void setEmail(EmailWithReceivers email){
        this.email = email;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}