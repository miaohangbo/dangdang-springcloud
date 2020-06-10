package com.qf.client;

import com.qf.pojo.Email;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 54110 on 2020/6/4.
 */
@FeignClient(value = "dangdang-email")
public interface EmailClient {
    @RequestMapping("/sendEmail")
    public String sendEmail(@RequestBody Email email);
}
