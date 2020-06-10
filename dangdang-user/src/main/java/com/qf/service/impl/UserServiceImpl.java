package com.qf.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.qf.client.EmailClient;
import com.qf.client.PayClient;
import com.qf.dao.PayMapper;
import com.qf.dao.PersonRepository;
import com.qf.dao.UserMapper;
import com.qf.pojo.*;
import com.qf.service.UserService;
import com.qf.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

/**
 * Created by 54110 on 2020/6/4.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    EmailClient emailClient;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PayMapper payMapper;
    @Autowired
    PayClient payClient;

    @Override
    public String registry(User user) {

        String username = user.getUsername();

        //判断username是否唯一
        User byUserName = userMapper.findByUserName(username);

        if (byUserName!=null){
            return "用户名重复";
        }

        String code = user.getCode();

        String o =(String) redisUtils.get(username);

        if (code.equals(o)){

            //存入数据库
            userMapper.insertUser(user);
        }else {
            return "验证码不对，重新发送";
        }

        return "success";
    }


    //发送邮件s
    @Override
    public String sendEmail(Map<String, String> map) {

        //调用发送邮件的服务，发送验证码，同时返回成功后，存入redis中
        Email email = new Email();
        email.setSubject("注册发送验证码");
        email.setMessage("1234");
        email.setSendTo(map.get("email"));
        String s = emailClient.sendEmail(email);
        if (s.equals("success")){
            redisUtils.set(email.getSendTo(),email.getMessage());
            return "success";
        }
        return "fail";
    }

    @Override
    public String login(User user) {
        User byUserName = userMapper.findByUserName(user.getUsername());

        if (byUserName.getPassword().equals(user.getPassword())){
            UUID uuid = UUID.randomUUID();
            //uuid为redis中的key
            redisUtils.set(uuid.toString(),byUserName);
            return uuid.toString();
        }

        return "登录失败";
    }

    @Override
    public PersonResponse findPerson(Integer pagesize, Integer pageNum) {


        PageRequest pageRequest = new PageRequest( pageNum-1,pagesize);
        Page<Person> all = personRepository.findAll(pageRequest);

        long totalElements = all.getTotalElements();

        PersonResponse personResponse = new PersonResponse();

        personResponse.setTotal(totalElements);
        personResponse.setUserList(all.getContent());
        return personResponse;
    }

    @Override
    //转账业务逻辑
    @Transactional//管理本地事务
    //@TxcTransaction //管理分布式事务
    @LcnTransaction
    public String pay(TrAccount trAccount) {


        payMapper.receiveMoney(trAccount);

        payClient.insertMoney(trAccount);

       // int i = 1/0;
        return "success";
    }


}
