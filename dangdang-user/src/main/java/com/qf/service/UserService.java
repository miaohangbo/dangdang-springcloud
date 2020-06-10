package com.qf.service;

import com.qf.pojo.PersonResponse;
import com.qf.pojo.TrAccount;
import com.qf.pojo.User;

import java.util.Map;

/**
 * Created by 54110 on 2020/6/4.
 */
public interface UserService {

    String registry(User user);

    String sendEmail(Map<String, String> map);

    String login(User user);

    PersonResponse findPerson(Integer pagesize, Integer pageNum);


    String pay(TrAccount trAccount);
}
