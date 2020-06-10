package com.qf.service.impl;

import com.qf.pojo.Email;
import com.qf.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by 54110 on 2020/6/4.
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String sendFrom;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public String send(Email email) {

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sendFrom);
            simpleMailMessage.setTo(email.getSendTo());
            simpleMailMessage.setText(email.getMessage());
            simpleMailMessage.setSubject(email.getSubject());

            javaMailSender.send(simpleMailMessage);
            return "success";
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return "fail";
    }

    @RabbitListener(queues = "email")
    public void send(Map map) {

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sendFrom);
            simpleMailMessage.setTo((String)map.get("email"));
            simpleMailMessage.setText((String)map.get("message"));
            simpleMailMessage.setSubject((String)map.get("username"));

            javaMailSender.send(simpleMailMessage);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
