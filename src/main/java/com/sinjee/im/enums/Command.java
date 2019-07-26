package com.sinjee.im.enums;

import lombok.Getter;

/***
 * @author kweitan
 * 指令集合 1字节
 */
@Getter
public enum Command {

    /** 单聊指令集 **/
    LOGIN_REQUEST(1),//登录请求指令 客户端[发送] -> 服务端[接收]
    LOGIN_RESPONSE(2),//登录响应指令 服务端[发送] -> 客户端[接收]
    CLIENT_SEND_MSG(3),//客服的发消息指令 客户端[发送] -> 服务端[接收]
    SERVER_SEND_MSG(4), //服务端发消息指令 服务端[发送] -> 客户端[接收]
    LOGOUT_REQUEST(5), //登出请求 客户端[发送] -> 服务端[接收]
    LOGOUT_RESPONSE(6), //登出响应 服务端[发送] -> 客户端[接收]

    /** 群聊指令集 **/
    CREATE_GROUP_REQUEST(7), //创建群聊请求 客户端[发送] -> 服务端[接收]
    CREATE_GROUP_SUCCESS_RESPONSE(8), //创建群聊成功通知 服务端[发送] -> 客户端[接收]
    ADD_GROUP_REQUEST(9), //加入群聊请求 客户端[发送] -> 服务端[接收]
    ADD_GROUP_RESPONSE(10), //加入群聊通知 服务端[发送] -> 客户端[接收]
    SEND_GROUP_MSG(11), //发送群聊消息 客户端[发送] -> 服务端[接收]
    RECEIVE_GROUP_MSG(12), //接收群聊消息 服务端[发送] -> 客户端[接收]
    LOGOUT_GROUP_REQUEST(13), //退出群聊请求 客户端[发送] -> 服务端[接收]
    LOGOUT_GROUP_RESPONSE(14); //退出群聊通知 服务端[发送] -> 客户端[接收]

    private Command(int value){
        this.vaule = value ;
    }

    private final int vaule ;

}
