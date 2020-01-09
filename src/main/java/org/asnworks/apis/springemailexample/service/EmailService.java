package org.asnworks.apis.springemailexample.service;

import org.asnworks.apis.springemailexample.model.Email;
import org.asnworks.apis.springemailexample.model.Receiver;
import org.asnworks.apis.springemailexample.model.TokenEmail;
import org.asnworks.apis.springemailexample.model.XUser;
import org.asnworks.apis.springemailexample.repository.EmailRepository;
import org.asnworks.apis.springemailexample.repository.ReceiverRepository;
import org.asnworks.apis.springemailexample.repository.UserRepository;
import org.asnworks.apis.springemailexample.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * ClassName EmailService
 * Description:
 * Author: Jialiang Wu
 *
 * @Date 2019-12-13 17:13
 * @Version 1.0
 **/
@Service
public class EmailService {
    private EmailRepository repository;
    private ReceiverRepository receiverRepository;
    private UserRepository userRepository;
    private TokenService tokenService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired // annotation a constructor
    public EmailService(EmailRepository repository, ReceiverRepository receiverRepository, TokenService tokenService, UserRepository userRepository){
        this.repository = repository;
        this.receiverRepository = receiverRepository;
        this.tokenService = tokenService;
        this.userRepository= userRepository;
    }

    public Email addBasicEmail(TokenEmail email){
        return repository.save(getBasicEmail(email));
    }

    public void add(TokenEmail email){
        Email basicEmail = addBasicEmail(email);
        List<String> toUsers = email.getEmail().getToUsers();
        for(String user: toUsers){
            Receiver curUser = new Receiver(user, basicEmail.getEmailID());
            receiverRepository.save(curUser);
        }
    }

    public void emailSender(List<String> receivers, String title, String body){
        for(String receiver : receivers){
            SimpleMailMessage message = emailUtil.composeEmail(title, body, new XUser(receiver));
            mailSender.send(message);
        }
    }


    private Email getBasicEmail(TokenEmail tokenEmail){
        //extract email info from the body
        Email basicEmail = new Email();
        basicEmail.setBody(tokenEmail.getEmail().getBody());
        basicEmail.setFromUser(tokenService.getAddress(tokenEmail.getToken()));
        basicEmail.setTitle(tokenEmail.getEmail().getTitle());
        basicEmail.setRead(true);
        return basicEmail;
    }

    public String emailPoller(String token) throws MessagingException, IOException {

        String userName = tokenService.getAddress(token);
        String password = userRepository.findAllByUserAddress(userName).getPassWord();

        Properties props = new Properties();
        props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.pop3.socketFactory.fallback", "false");
        props.put("mail.pop3.socketFactory.port", "993");
        props.put("mail.pop3.port", "993");
        props.put("mail.pop3.host", "imap.exmail.qq.com");
        props.put("mail.pop3.user", userName);
        props.put("mail.store.protocol", "imap");

        final String USERNAME = userName;
        final String PASSWORD = password;

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);

        // 4. Get the POP3 store provider and connect to the store.
        Store store = null;
        try {
            store = session.getStore("imap");
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        try {
            store.connect("imap.exmail.qq.com", userName, password);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // 5. Get folder and open the INBOX folder in the store.
        Folder inbox = null;
        try {
            inbox = store.getFolder("INBOX");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        inbox.open(Folder.READ_ONLY);
        Message[] messages = new Message[0];
        try {
            messages = inbox.getMessages();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
//        Message message = messages[0];
        String res = "";
        int emailNum = 1;
        for (Message message : messages) {
            Address[] addresses = new Address[0];
            try {
                addresses = message.getFrom();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
//            for(Address address: addresses){
//                if(address.toString().contains("JohnLWu2019@outlook.com")){
//                    System.out.println(getTextFromMessage(message));
//                }
//            }
            Address[] fromUsers = message.getFrom();
            Address[] receivers = message.getAllRecipients();
            Date date = message.getSentDate();
            String body = "Email content: "+ "\n" + getTextFromMessage(message);
            String fromUser = "From: " + "\n";
            String receiver = "To: " + "\n";
            for(Address user: fromUsers){
                fromUser += getAddressString(user.toString()) + "\n";
            }
            for(Address user : receivers){
                receiver += getAddressString(user.toString()) + "\n";
            }
            String title = "Title: " + message.getSubject() + "  Date: " + date.toString() + "\n";
            String everyEmail = "Email " + emailNum + " :\n" + fromUser + receiver +  title + body;
            emailNum ++;
            res += everyEmail;
        }

        // 7. Close folder and close store.
        inbox.close(false);
        store.close();
        return res;

    }
    public String getAddressString (String address){
        String cleanAddress = "";
        boolean flag = false;
        for(int i = 0; i < address.length(); i++){
            if(address.charAt(i) == '<'){
                flag = true;
            }
            if(flag){
                cleanAddress = address.substring(i, address.length());
                break;
            }
        }
        return cleanAddress;
    }



    public String getTextFromMessage(Message message) throws MessagingException, IOException {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        }
        else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

    public String getTextFromMimeMultipart(MimeMultipart mimeMultipart)  throws MessagingException, IOException{
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            }
            else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
            }
            else if (bodyPart.getContent() instanceof MimeMultipart){
                result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
            }
        }
        return result;
    }


}