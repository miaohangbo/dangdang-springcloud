package com.qf.pojo;

import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by 54110 on 2020/6/2.
 */
@Data

public class User {


    private Integer id;

    private String username;

    private String address;

    private String password;

    private String code;
}
