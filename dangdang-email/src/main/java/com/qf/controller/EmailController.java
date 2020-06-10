package com.qf.controller;

import com.qf.pojo.Email;
import com.qf.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 54110 on 2020/6/4.
 */
@RestController
public class EmailController {

    @Autowired
    EmailService emailService;


    @RequestMapping("/sendEmail")
    public String sendEmail(@RequestBody Email email){
       return emailService.send(email);
    }

    @RequestMapping("/test")
    public String test(){
        return "ok";
    }
}
