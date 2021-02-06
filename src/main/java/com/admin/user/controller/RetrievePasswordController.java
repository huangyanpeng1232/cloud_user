package com.admin.user.controller;

import com.admin.user.service.RetrievePasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("retrievePassword")
public class RetrievePasswordController {

    @Autowired
    RetrievePasswordService retrievePasswordService;

    @RequestMapping("sendEmailCode")
    public String sendEmailCode(String userId){
        log.info("发送邮箱验证码,userId:{}",userId);

        String email = "2905325171@qq.com";

        String code = retrievePasswordService.sendEmailCode(email);

        return "true";
    }

}
