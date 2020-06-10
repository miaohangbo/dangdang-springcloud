package com.qf.dao;

import com.qf.pojo.TrAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by 54110 on 2020/6/4.
 */
@Mapper
public interface PayMapper {


    void insertMoney(TrAccount trAccount);
    void receiveMoney(TrAccount trAccount);
}
