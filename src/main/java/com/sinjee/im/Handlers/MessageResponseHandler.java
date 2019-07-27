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
        log.info("收到服务端返回的内容："+msg.getMessage());
    }
}
