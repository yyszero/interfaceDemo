package com.stuRedis.only.service;

import com.stuRedis.only.common.ServerResponse;
import com.stuRedis.only.pojo.Mail;

/**
 * @author:Yangying
 * @date:2020-4-22
 */
public interface TestService {
    ServerResponse testIdempotence();

    ServerResponse accessLimit();

    ServerResponse send(Mail mail);
}
