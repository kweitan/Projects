package com.sinjee.im.utils;

import java.util.UUID;

/**
 * @author kweitan
 * 线程安全随机数生成类
 */
public class RandomUtil {
    public synchronized static String getRandomUUID(){
        return UUID.randomUUID().toString() ;
    }
}
