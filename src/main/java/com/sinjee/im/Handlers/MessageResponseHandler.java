package com.sinjee.im.Handlers;

import com.sinjee.im.dto.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/****
 * @author kweitan
 * 处理消息响应
 */
@Slf4j
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        String fromUserId = msg.getFromUserId() ;
        String fromUserName = msg.getFromUserName() ;
        System.out.println("来自用户ID:"+fromUserId+";用户昵称:"+fromUserName+";发送的内容=>"+msg.getMessage());
    }
}
