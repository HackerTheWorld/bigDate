package com.bigdate.controller;

import com.alibaba.fastjson.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bigData")
public class BigDataUploadController{

    @PostMapping("/uploadBigData")
    public JSONObject uploadBigData(){
        JSONObject jsonObject = new JSONObject();
        return jsonObject;
    }

}
