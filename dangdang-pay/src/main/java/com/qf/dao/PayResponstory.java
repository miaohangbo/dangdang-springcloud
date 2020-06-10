package com.qf.dao;

import com.qf.pojo.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 54110 on 2020/6/4.
 */
public interface PayResponstory  extends JpaRepository<Pay,Integer> {
}
