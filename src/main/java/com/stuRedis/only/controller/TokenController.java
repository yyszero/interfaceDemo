package com.stuRedis.only.controller;

import com.stuRedis.only.common.ServerResponse;
import com.stuRedis.only.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:Yangying
 * @date:2020-4-22
 */
@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ServerResponse token() {
        return tokenService.createToken();
    }
}
