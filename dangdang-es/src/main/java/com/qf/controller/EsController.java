package com.qf.controller;

import com.qf.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by 54110 on 2020/6/8.
 */
@RestController
public class EsController {

    @Autowired
    EsService esService;

    @RequestMapping("/search/{value}")
    public String search(@PathVariable("value")String value) throws IOException {

       return esService.search(value);

    }
}
