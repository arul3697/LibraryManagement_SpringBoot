package com.ssb.onlinelibrary.controller;

import com.ssb.onlinelibrary.common.Constants;
import com.ssb.onlinelibrary.model.User;
import com.ssb.onlinelibrary.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/api")
public class EmailController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping( value = "/resetPassword" )
    public User reset(@RequestBody User user ) throws Exception {
        System.out.println("--------Email-------"+user.getEmail_id());
        User userEmail = userService.getUserEmail(user.getEmail_id());
        String toEmail =user.getEmail_id();
        String fromEmail = "arulselvan844@gmail.com";
        String password = "*****";

        try {
            // your host email smtp server details
            Properties pr = new Properties();
            pr.setProperty("mail.smtp.host", "smtp.gmail.com");
            pr.setProperty("mail.smtp.port", "465");
            pr.setProperty("mail.smtp.auth", "true");
            pr.setProperty("mail.smtp.starttls.enable", "true");
            pr.put("mail.smtp.socketFactory.port", "465");
            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            //get session to authenticate the host email address and password
            Session session = Session.getInstance(pr, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            //set email message details
            Message mess = new MimeMessage(session);

            //set from email address
            mess.setFrom(new InternetAddress(fromEmail));
            //set to email address or destination email address
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            //set email subject
            mess.setSubject("Your Password");

            //set message text
            mess.setText(" Your account password: " + userEmail.getPassword());
            //send the message
            Transport.send(mess);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Invalid EmailId...!!");
//            modelMap.addAttribute("error", Constants.INVALID_MAIL);
        }

//        modelMap.addAttribute("reset", Constants.SEND_EMAIL);
        return user;
    }
}
