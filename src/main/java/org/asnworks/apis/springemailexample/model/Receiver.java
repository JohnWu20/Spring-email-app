package org.asnworks.apis.springemailexample.model;

import javax.persistence.*;
import java.util.List;

/**
 * ClassName Receiver
 * Description:
 * Author: Jialiang Wu
 *
 * @Date 2019-12-13 21:12
 * @Version 1.0
 **/
@Entity //
@Table(name = "receivers_email") //
public class Receiver {
    private Integer receiverID;
    private String receiver;
    private Integer emailID;
    public Receiver(String receiver, int emailID){
        this.receiver = receiver;
        this.emailID = emailID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receivers_id", nullable = false)
    public Integer getReceiverID() {
        return receiverID;
    }
    public void setReceiverID(Integer receiverID) {
        this.receiverID = receiverID;
    }

    @Basic
    @Column(name = "receiver_address", nullable = false)
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Basic
    @Column(name = "email_id", nullable = false)
    public int getEmailID() {
        return emailID;
    }

    public void setEmailID(int emailID) {
        this.emailID = emailID;
    }



}