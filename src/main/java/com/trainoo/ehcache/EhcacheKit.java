package com.trainoo.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by zhoutao on 2018/6/28 11:29
 */

public class EhcacheKit {

    private static EhcacheKit instatce = null;
    private static CacheManager cacheManager = null;

    private EhcacheKit(){}

    // private static String configPath = "/src/main/resources/resource/ehcache.xml";
    private static String configPath = "/AsimpleDemo/src/main/resources/resource/ehcache.xml";
    public static EhcacheKit instance() throws IOException {
        if(instatce == null){
            instatce = new EhcacheKit();
        }
        if(cacheManager == null){

            cacheManager = CacheManager.create(new File(".").getCanonicalPath() + configPath);
        }
        return instatce;
    }

    public Cache cache(String name){
        return cacheManager.getCache(name);
    }
}
