package com.qf.pojo;

import lombok.Data;

/**
 * Created by 54110 on 2020/6/4.
 */
@Data
public class Email {
    //发送给谁
    private String sendTo;
    //发送的内容是什么

    private String message;

    //发送的主题
    private String subject;
}
