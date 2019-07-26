package com.sinjee.im.utils;

import com.sinjee.im.config.Config;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @author kweitan
 * 服务端登录检查 验证
 */
public class LoginUtil {

    //设置true到channel上
    public static void markAsLogin(Channel channel){
        channel.attr(Config.LOGIN).set(true);
    }

    //判断是否登录
    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> loginAttribute = channel.attr(Config.LOGIN) ;
        return loginAttribute.get() != null ;
    }
}
