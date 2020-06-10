package com.qf.pojo;

import lombok.Data;

/**
 * Created by 54110 on 2020/6/5.
 */
@Data
public class TrAccount {

    private Integer id;

    private String receiveUsername;

    private String toUserName;

    private Double money;
}
