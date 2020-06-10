package com.qf.pojo;

import lombok.Data;

import java.util.List;

/**
 * Created by 54110 on 2020/6/4.
 */
@Data
public class PersonResponse {

    private List<Person> userList;

    private Long total;

}
