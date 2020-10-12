package com.stuRedis.only.dao;

import com.stuRedis.only.pojo.MsgLog;

import java.util.List;

/**
 * @author:Yangying
 * @date:2020-4-22
 */

public interface MsgLogMapper {
    void insert(MsgLog msgLog);

    void updateStatus(MsgLog msgLog);

    List<MsgLog> selectTimeoutMsg();

    void updateTryCount(MsgLog msgLog);

    MsgLog selectByPrimaryKey(String msgId);
}
