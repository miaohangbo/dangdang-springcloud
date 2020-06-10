package com.qf.contoller;

import com.qf.pojo.TrAccount;
import com.qf.pojo.User;
import com.qf.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 54110 on 2020/6/2.
 */
@RestController
public class PayController {

    @Autowired
    PayService payService;



    @RequestMapping(value = "/updatepay",method = RequestMethod.POST)
    public String updatePay(@RequestBody User user){

        System.out.println(user);
        return "OK了";
    }

    public String fallback(@RequestBody TrAccount trAccount){
        return "当前支付不可用，请稍后再试";
    }

    @RequestMapping(value = "/insertMoney",method = RequestMethod.POST)
    //@HystrixCommand(fallbackMethod = "fallback")
    public void insertMoney(@RequestBody TrAccount trAccount){
        payService.insertMoney(trAccount);
    }
}
