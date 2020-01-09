package org.asnworks.apis.springemailexample;

import org.asnworks.apis.springemailexample.model.XUser;
import org.asnworks.apis.springemailexample.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.mail.javamail.JavaMailSender;

//initialize the server

@SpringBootApplication
@EnableJpaRepositories("org.asnworks.apis.springemailexample.repository")
//public class SpringEmailExampleApplication implements CommandLineRunner {
public class SpringEmailExampleApplication {


    //start the whole web application
    public static void main(String[] args) {
        SpringApplication.run(SpringEmailExampleApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {

//    }



}
