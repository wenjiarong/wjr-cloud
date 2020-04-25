package com.system.cache;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.common.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class MybatisRedisCache implements Cache {

    // 读写锁
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    private RedisService redisService;

    private String id;

    public MybatisRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        if (redisService == null) {
            //由于启动期间注入失败，只能运行期间注入，这段代码可以删除
            redisService = SpringContextUtil.getBean("redisService", RedisService.class);
        }
        if (value != null) {
            redisService.set(key.toString(), value);
        }
    }

    @Override
    public Object getObject(Object key) {
        if (redisService == null) {
            //由于启动期间注入失败，只能运行期间注入，这段代码可以删除
            redisService = SpringContextUtil.getBean("redisService", RedisService.class);
        }
        if (key != null) {
            return redisService.get(key.toString());
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        if (redisService == null) {
            //由于启动期间注入失败，只能运行期间注入，这段代码可以删除
            redisService = SpringContextUtil.getBean("redisService", RedisService.class);
        }
        if (key != null) {
            redisService.del(key.toString());
        }
        return null;
    }

    @Override
    public void clear() {
        log.debug("清空缓存");
        if (redisService == null) {
            redisService = SpringContextUtil.getBean("redisService", RedisService.class);
        }
        Set<String> keys = redisService.keys("*:" + this.id + "*");
        if (!CollectionUtils.isEmpty(keys)) {
            redisService.multiDel(keys);
        }
    }

    @Override
    public int getSize() {
        if (redisService == null) {
            //由于启动期间注入失败，只能运行期间注入，这段代码可以删除
            redisService = SpringContextUtil.getBean("redisService", RedisService.class);
        }
        return redisService.getSize();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }
}