package com.qf.client;

import com.qf.pojo.TrAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 54110 on 2020/6/2.
 */
@FeignClient(value = "dangdang-pay")
public interface PayClient {



    @RequestMapping(value = "/insertMoney",method = RequestMethod.POST)
    public void insertMoney(@RequestBody TrAccount trAccount);
}
