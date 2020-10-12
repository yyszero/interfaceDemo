package com.stuRedis.only.service.impl;

import com.stuRedis.only.common.Constant;
import com.stuRedis.only.common.ResponseCode;
import com.stuRedis.only.common.ServerResponse;
import com.stuRedis.only.exception.ServiceException;
import com.stuRedis.only.service.TokenService;
import com.stuRedis.only.utils.JedisUtil;
import com.stuRedis.only.utils.RandomUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;


/**
 * @author:Yangying
 * @date:2020-4-22
 */
@Service
@Slf4j
public class TokenServiceImpl implements TokenService {
    private static final String TOKEN_NAME="token";
    @Autowired
    JedisUtil jedisUtil;
    @Override
    public ServerResponse createToken() {
        String str= RandomUtil.uuid32();
        StringBuilder token =new StringBuilder();
//        token.append(Constant.Redis.TOKEN_PREFIX).append(str);
        token.append(str);
        jedisUtil.set(token.toString(),token.toString(),Constant.Redis.EXPIRE_TIME_MINUTE);
        return ServerResponse.success(token.toString());
    }

    @Override
    public void checkToken(HttpServletRequest request) {
        String token=request.getHeader(TOKEN_NAME);
        if(StringUtils.isBlank(token)){
            //header中不存在token
            token=request.getParameter(TOKEN_NAME);
            if(StringUtils.isBlank(token)){
                throw new ServiceException(ResponseCode.ILLEGAL_ARGUMENT.getMsg());
            }
        }
        //如果在redis数据库中token不存在 说明重复操作
        if(!jedisUtil.exists(token)){
            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }
        //正常存在 删除已经存在的token
        Long del=jedisUtil.del(token);
        if(del<=0){
            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }
    }
}
