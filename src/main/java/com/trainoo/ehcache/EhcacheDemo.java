package com.trainoo.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by zhoutao on 2018/6/20 14:21
 *
 */

public class EhcacheDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        String path = new File(".").getCanonicalPath();

        // 1. 创建缓存管理器
        CacheManager cacheManager = CacheManager.create(path + "/ASimpleDemo/src/main/resources/resource/ehcache.xml");

        // 2. 获取缓存对象
        Cache cache = cacheManager.getCache("HelloWorldCache");

        // 3. 创建元素
        Element element = new Element("key1", "value1");

        // 4. 将元素添加到缓存
        cache.put(element);
        System.out.println(cache.getSize());

        // 5. 获取缓存
        Thread.sleep(6000);
        Element value = cache.get("key1");
        System.out.println(value);
        System.out.println(value.getObjectValue());
        System.out.println(new Date(value.getCreationTime()));
        System.out.println(new Date(value.getLastAccessTime()));

        // 6. 删除元素
        cache.remove("key1");

        // 7. 刷新缓存
        cache.flush();

        // 8. 关闭缓存管理器
        cacheManager.shutdown();
    }
}
