package org.asnworks.apis.springemailexample.model;

import java.util.List;

/**
 * ClassName EmailWithReceivers
 * Description:
 * Author: Jialiang Wu
 *
 * @Date 2019-12-13 20:51
 * @Version 1.0
 **/

public class EmailWithReceivers extends Email{
    private List<String> toUsers;

    EmailWithReceivers(){}

    EmailWithReceivers(String fromUser, List toUsers, String title, String body){
        super(fromUser, title, body);
        this.toUsers = toUsers;
    }


    public List<String> getToUsers() {
        return toUsers;
    }

    public void setToUsers(List<String> toUsers) {
        this.toUsers = toUsers;
    }
}
