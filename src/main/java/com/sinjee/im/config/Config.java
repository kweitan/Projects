package com.sinjee.im.config;

import com.sinjee.im.utils.Session;
import io.netty.util.AttributeKey;

/**
 * @author kweitan
 * 基础配置
 */
public class Config {
    //版本号
    public static final int VERSION = 1 ;

    //channel 属性 绑定倒channel上
    public static final AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login") ;

    public static final AttributeKey<Session> SESSION = AttributeKey.newInstance("session") ;

    public static final int MAGIC_NUM = 0x12345678 ;
}
