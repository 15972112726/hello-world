package com.zz.controller;

import org.apache.juli.ClassLoaderLogManager;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




/**
 * Created by dell on 2017/10/20.
 */

@RestController
@RequestMapping("/zz_1")
public class Controller_1 {

    @RequestMapping(value = "/{name}",method = RequestMethod.GET)
    public String sayHello(@PathVariable("name") String name){
        return "Hello "+name;
    }
}
