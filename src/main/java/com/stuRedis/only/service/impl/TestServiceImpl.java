package com.stuRedis.only.service.impl;

import com.stuRedis.only.common.ResponseCode;
import com.stuRedis.only.common.ServerResponse;
import com.stuRedis.only.dao.MsgLogMapper;
import com.stuRedis.only.pojo.Mail;
import com.stuRedis.only.pojo.MsgLog;
import com.stuRedis.only.service.TestService;
import com.stuRedis.only.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author:Yangying
 * @date:2020-4-22
 */
@Service
public class TestServiceImpl implements TestService {
//    @Autowired
//    private MsgLogMapper msgLogMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public ServerResponse testIdempotence() {
        return ServerResponse.success("testIdempotence: success");
    }

    @Override
    public ServerResponse accessLimit() {
        return ServerResponse.success("accessLimit: success");
    }

    @Override
    public ServerResponse send(Mail mail) {
        String msgId = RandomUtil.uuid32();
        mail.setMsgId(msgId);

//        MsgLog msgLog = new MsgLog(msgId, mail, RabbitConfig.MAIL_EXCHANGE_NAME, RabbitConfig.MAIL_ROUTING_KEY_NAME);
//        msgLogMapper.insert(msgLog);// 消息入库
//
//        CorrelationData correlationData = new CorrelationData(msgId);
//        rabbitTemplate.convertAndSend(RabbitConfig.MAIL_EXCHANGE_NAME, RabbitConfig.MAIL_ROUTING_KEY_NAME, MessageHelper.objToMsg(mail), correlationData);// 发送消息
//
        return ServerResponse.success(ResponseCode.MAIL_SEND_SUCCESS.getMsg());
    }
}
