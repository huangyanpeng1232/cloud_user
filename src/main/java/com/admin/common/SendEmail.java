package com.admin.common;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("common")
public interface SendEmail {

    @GetMapping("/email/sendEmail")
    String sendEmail(@RequestParam String recipients, @RequestParam String content);
}
