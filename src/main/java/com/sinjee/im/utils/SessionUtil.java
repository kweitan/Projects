package com.sinjee.im.utils;

import com.sinjee.im.config.Config;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author kweitan
 * 保存用户的会话信息
 */
public class SessionUtil {

    //userId -> channel 映射
    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>() ;

    //绑定session
    public static void bindSession(Session session, Channel channel){
        userIdChannelMap.put(session.getUserId(),channel) ;
        channel.attr(Config.SESSION).set(session);
    }

    public static void unBindSession(Channel channel){
        if(hasLogin(channel)){
            userIdChannelMap.remove(getSession(channel).getUserId()) ;
            channel.attr(Config.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel){
        return channel.hasAttr(Config.SESSION) ;
    }

    public static Session getSession(Channel channel){
        return channel.attr(Config.SESSION).get() ;
    }

    public static Channel getChannel(String userId){
        return userIdChannelMap.get(userId) ;
    }

}
