package com.qf.controller;

import com.qf.pojo.PersonResponse;
import com.qf.pojo.TrAccount;
import com.qf.pojo.User;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by 54110 on 2020/6/2.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/sendEmail")
    public String sendEmail(@RequestBody Map<String,String> map){

        return userService.sendEmail(map);
    }

    @RequestMapping(value = "/registry",method = RequestMethod.POST)
    public String registry(@RequestBody User user){

       return userService.registry(user);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestBody User user){
        return userService.login(user);
    }

    @RequestMapping(value = "/findAll/{pagesize}/{pageNum}")
    public PersonResponse findll(@PathVariable("pagesize")Integer pagesize,@PathVariable("pageNum")Integer pageNum){
        return userService.findPerson(pagesize,pageNum);
    }

    @RequestMapping(value = "/pay",method = RequestMethod.POST)
    public String pay(@RequestBody TrAccount trAccount){

      return userService.pay(trAccount);
    }

}
