package com.stuRedis.only.pojo;

import com.stuRedis.only.common.Constant;
import com.stuRedis.only.utils.JodaTimeUtil;
import com.stuRedis.only.utils.JsonUtil;
import lombok.*;

import java.util.Date;

/**
 * @author:Yangying
 * @date:2020-4-22
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MsgLog {
    private String msgId;
    private String msg;
    private String exchange;
    private String routingKey;
    private Integer status;
    private Integer tryCount;
    private Date nextTryTime;
    private Date createTime;
    private Date updateTime;

    public MsgLog(String msgId, Object msg, String exchange, String routingKey) {
        this.msgId = msgId;
        this.msg = JsonUtil.objToStr(msg);
        this.exchange = exchange;
        this.routingKey = routingKey;

        this.status = Constant.MsgLogStatus.DELIVERING;
        this.tryCount = 0;

        Date date = new Date();
        this.createTime = date;
        this.updateTime = date;
        this.nextTryTime = (JodaTimeUtil.plusMinutes(date, 1));
    }
}
