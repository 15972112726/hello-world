package com.zz.controller;

import com.zz.service.GzDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dell on 2017/10/21.
 */
@RestController
@RequestMapping("/zz_2")

public class GzController {
    @Autowired
    private GzDayService gzDayService;

    @RequestMapping(value = "/{s}",method = RequestMethod.GET)
    public String SayGzDay(@PathVariable("s") String s){
        String s1 = gzDayService.workDay(s);
        return s1;
    }

    @RequestMapping(value = "/save/{s}",method = RequestMethod.GET)
    public String SaveSayGzDay(@PathVariable("s") String s){
        boolean b = gzDayService.saveWorkDay(s);
        if(true){
            return "保存成功";
        }
        return  "保存失敗";

    }

}
