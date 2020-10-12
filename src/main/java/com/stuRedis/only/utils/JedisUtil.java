package com.stuRedis.only.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis工具类
 *
 * @author:Yangying
 * @date:2020-4-21
 */
@Component
@Slf4j
public class JedisUtil {

    @Autowired()
    private JedisPool jedisPool;

    private Jedis getJedis() {
        return jedisPool.getResource();
    }
    private void close(Jedis jedis){
        if(null!=jedis){
            jedis.close();
        }
    }

    /**
     * redis设值
     * @param key
     * @param value
     * @return 1
     */
    public String set(String key,String value){
        Jedis jedis=null;
        try{
            jedis=getJedis();
            return jedis.set(key,value);
        }catch (Exception ex){
            log.error("set key:{}value:{}error",key,value,ex);
            return null;
        }finally {
            close(jedis);
        }
    }

    /**
     * redis设值
     * @param key
     * @param value
     * @param expireTime 过期时间, 单位: s
     * @return
     */
    public String set(String key,String value,int expireTime){
        Jedis jedis=null;
        try{
            jedis=getJedis();
            return jedis.setex(key,expireTime,value);
        }catch (Exception ex){
            log.error("set key:{}value:{}expireTime:{}error",key,value,expireTime,ex);
            return null;
        }finally {
            close(jedis);
        }
    }

    /**
     * redis 获取值
     * @param key
     * @return
     */
    public String get(String key){
        Jedis jedis=null;
        try{
            jedis=getJedis();
            return jedis.get(key);
        }catch (Exception ex){
            log.error("get key:{}error",key,ex);
            return null;
        }finally {
            close(jedis);
        }
    }

    /**
     * 删除 key
     * @param key
     * @return
     */
    public Long del(String key){
        Jedis jedis=null;
        try{
            jedis=getJedis();
            return jedis.del(key.getBytes());
        }catch (Exception ex){
            log.error("del key:{}error",key,ex);
            return null;
        }finally {
            close(jedis);
        }
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public Boolean exists(String key){
        Jedis jedis=null;
        try{
            jedis=getJedis();
            return jedis.exists(key.getBytes());
        }catch (Exception ex){
            log.error("exists key:{}error",key,ex);
            return null;
        }finally {
            close(jedis);
        }
    }

    /**
     * 设置key过期时间
     * @param key
     * @param expireTime
     * @return
     */
    public Long expire(String key,int expireTime){
        Jedis jedis=null;
        try{
            jedis=getJedis();
            return jedis.expire(key.getBytes(),expireTime);
        }catch (Exception ex){
            log.error("expire key:{}expireTime:{}error",key,expireTime,ex);
            return null;
        }finally {
            close(jedis);
        }
    }

    /**
     * 获取key剩余时间
     * @param key
     * @return
     */
    public Long ttl(String key){
        Jedis jedis=null;
        try{
            jedis=getJedis();
            return jedis.ttl(key.getBytes());
        }catch (Exception ex){
            log.error("ttl key:{}error",key,ex);
            return null;
        }finally {
            close(jedis);
        }
    }
}
