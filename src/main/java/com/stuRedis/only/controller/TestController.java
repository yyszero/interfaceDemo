package com.stuRedis.only.controller;

import com.stuRedis.only.annonation.ApiIdempotent;
import com.stuRedis.only.common.ServerResponse;
import com.stuRedis.only.pojo.Mail;
import com.stuRedis.only.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:Yangying
 * @date:2020-4-22
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Autowired
    private TestService testService;

    @ApiIdempotent
    @PostMapping("testIdempotence")
    public ServerResponse testIdempotence() {
        return testService.testIdempotence();
    }

//    @AccessLimit(maxCount = 5, seconds = 5)
//    @PostMapping("accessLimit")
//    public ServerResponse accessLimit() {
//        return testService.accessLimit();
//    }
//
//    @PostMapping("send")
//    public ServerResponse sendMail(@Validated Mail mail, Errors errors) {
//        if (errors.hasErrors()) {
//            String msg = errors.getFieldError().getDefaultMessage();
//            return ServerResponse.error(msg);
//        }
//
//        return testService.send(mail);
//    }
}
