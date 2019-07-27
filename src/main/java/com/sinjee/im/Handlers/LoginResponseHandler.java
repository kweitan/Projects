package com.sinjee.im.Handlers;

import com.sinjee.im.dto.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/****
 * @author kweitan
 * 客户端处理LoginResponseHandler响应
 */
@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        if (msg.getSuccess()){
            log.info("登录成功！！！");
        }else {
            log.error("登录失败，原因{}",msg.getReason());
        }
    }
}
