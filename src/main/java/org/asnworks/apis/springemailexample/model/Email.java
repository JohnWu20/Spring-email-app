package org.asnworks.apis.springemailexample.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * ClassName Email
 * Description:
 * Author: Jialiang Wu
 *
 * @Date 2019-12-12 19:11
 * @Version 1.0
 **/
@Entity
@Table(name = "user_email")
public class Email {

   private String userEmailID;
   private Integer emailID;
   private String fromUser;
   private String title;
   private String body;
   private Boolean isRead;


    public Email(){
        this.userEmailID = UUID.randomUUID().toString();
    }

    public Email(String fromUser, String title, String body){
        super();
        this.fromUser = fromUser;
        this.title = title;
        this.body = body;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_id", nullable = false)
    public Integer getEmailID() {
        return emailID;
    }

    public void setEmailID(Integer emailID) {
        this.emailID = emailID;
    }

    @Basic
    @Column(name = "user_email_id", nullable = false)
    public String getUserEmailID() {
        return userEmailID;
    }

    public void setUserEmailID(String userEmailID) {
        this.userEmailID = userEmailID;
    }

    @Basic
    @Column(name = "from_user", nullable = false)
    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

//    @Basic
//    @Column(name = "to_user")
//    public List getToUser() {
//        return toUsers;
//    }
//
//    public void setToUser(List toUser) {
//        this.toUsers = toUsers;
//    }
    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Basic
    @Column(name = "body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "is_read", nullable = false)
    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }
}