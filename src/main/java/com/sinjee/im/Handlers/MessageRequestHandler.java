package com.sinjee.im.Handlers;

import com.sinjee.im.dto.MessageRequestPacket;
import com.sinjee.im.dto.MessageResponsePacket;
import com.sinjee.im.utils.DataPacketCodeC;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/***
 * @author kweitan
 * 消息请求处理响应
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        ctx.channel().writeAndFlush(messageRes(msg)) ;
    }

    private MessageResponsePacket messageRes(MessageRequestPacket msg){
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket() ;
        messageResponsePacket.setVersion(msg.getVersion());
        messageResponsePacket.setMessage("服务端时间："+new Date()+" "+msg.getMessage());
        return messageResponsePacket ;
    }
}
