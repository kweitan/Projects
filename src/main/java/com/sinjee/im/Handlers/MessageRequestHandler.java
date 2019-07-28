package com.sinjee.im.Handlers;

import com.sinjee.im.dto.MessageRequestPacket;
import com.sinjee.im.dto.MessageResponsePacket;
import com.sinjee.im.utils.DataPacketCodeC;
import com.sinjee.im.utils.Session;
import com.sinjee.im.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/***
 * @author kweitan
 * 消息请求处理响应
 */
@Slf4j
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        //1.拿到消息发送方的会话信息
        Session session = SessionUtil.getSession(ctx.channel()) ;

        //2.根据发送方的信息 构造要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket() ;
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setVersion(msg.getVersion());
        messageResponsePacket.setMessage(msg.getMessage());

        //3.拿到消息接收方的channel
        Channel toUserChannel = SessionUtil.getChannel(msg.getToUserId()) ;
        if(null != toUserChannel && SessionUtil.hasLogin(toUserChannel)){
            toUserChannel.writeAndFlush(messageResponsePacket) ;
        }else {
            log.error("【{}】不在线，发送消息失败！！！",msg.getToUserId());
        }

    }

}
