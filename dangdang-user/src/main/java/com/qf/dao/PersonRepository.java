package com.qf.dao;

import com.qf.pojo.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 54110 on 2020/6/4.
 */
public interface PersonRepository extends JpaRepository<Person,Integer> {
}
