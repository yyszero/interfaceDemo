package com.stuRedis.only.service;


import com.stuRedis.only.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:Yangying
 * @date:2020-4-22
 */
public interface TokenService {
    /**
     * 创建token
     * @return 返回
     */
    ServerResponse createToken();

    /**
     * 校验请求的token
     * @param request  请求
     */
    void checkToken(HttpServletRequest request);
}
