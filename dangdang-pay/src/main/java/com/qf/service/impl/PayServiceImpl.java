package com.qf.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.qf.dao.PayMapper;
import com.qf.pojo.TrAccount;
import com.qf.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 54110 on 2020/6/4.
 */
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    PayMapper payMapper;

    @Override
    @Transactional//管理本地事务
    //@TxcTransaction //管理分布式事务
    @LcnTransaction
    public void insertMoney(TrAccount trAccount) {

        payMapper.insertMoney(trAccount);
        //int i =1/0;
        //return "success";
    }
}
