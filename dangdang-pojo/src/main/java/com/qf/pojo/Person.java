package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by 54110 on 2020/6/4.
 */
@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer age;

    @Column(name = "is_marray")
    private Integer IsMarray;

    private String detail;
}
