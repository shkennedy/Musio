package com.sbu.webspotify.service;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.webspotify.conf.AppConfig;
import com.sbu.webspotify.model.User;

@Service("emailService")
public class EmailService {

    // musio.sbu@gmail.com
    // musiopassword

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private AppConfig appConfig;
	
    public boolean sendSimpleEmailToUser(User user, String subject, String body) {
        System.out.println("Start of email send");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setFrom(appConfig.emailAddress);
        message.setSubject(subject);
        message.setText(body);

        try {
            System.out.println("Pre email send");
            mailSender.send(message);
            System.out.println("Post email send");
        } catch (MailException me) {
            System.out.println("email send error " + me.getMessage());
            return false;
        }
        return true;
    }
}
