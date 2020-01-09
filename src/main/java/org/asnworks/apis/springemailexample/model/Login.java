package org.asnworks.apis.springemailexample.model;

import javax.persistence.*;

/**
 * ClassName Login
 * Description:
 * Author: Jialiang Wu
 *
 * @Date 2019-12-12 19:52
 * @Version 1.0
 **/

@Entity //
@Table(name = "user_info") //
public class Login {

    private Integer userID;
    private String userAddress;
    private String passWord;
    private String userEmailID;

    public Login(){}

    public Login(String userAddress, String passWord){
        this.userAddress = userAddress;
        this.passWord = passWord;
    }

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Basic
    @Column(name = "user_address", nullable = false)
    public String getUserAddress() {
        return userAddress;
    }


    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Basic
    @Column(name = "user_password", nullable = false)
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Basic
    @Column(name = "user_email_id", nullable = false)
    public String getUserEmailID() {
        return userEmailID;
    }

    public void setUserEmailID(String userEmailID) {
        this.userEmailID = userEmailID;
    }
}