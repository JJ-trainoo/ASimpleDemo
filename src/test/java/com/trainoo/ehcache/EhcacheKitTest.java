package com.trainoo.ehcache;

import net.sf.ehcache.Cache;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by zhoutao on 2018/6/28 11:41
 */

public class EhcacheKitTest {

    @Test
    public void test() throws IOException {
        Cache helloCache = EhcacheKit.instance().cache("HelloWorldCache");
        assertNotNull(helloCache);
    }
}