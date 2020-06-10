package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by 54110 on 2020/6/4.
 */
@Entity
@Data
@Table(name = "ar_account")
public class Pay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private Double money;
}
