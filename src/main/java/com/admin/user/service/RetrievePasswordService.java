package com.admin.user.service;

import com.admin.common.SendEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class RetrievePasswordService {

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public String sendEmailCode(String email) {
        String content = "您好您的验证码为 842334";

        String sendResult = sendEmail.sendEmail(email, content);
        log.info("发送验证码 email:{} code:",email,sendResult);

        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);

        rabbitTemplate.convertAndSend("AdminExchanges", "email", map);

        return sendResult;
    }


}
