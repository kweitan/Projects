package com.sinjee.im.Handlers;

import com.sinjee.im.dto.LoginRequestPacket;
import com.sinjee.im.dto.LoginResponsePacket;
import com.sinjee.im.utils.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/****
 * @author kweitan
 * 客户端处理LoginResponseHandler响应
 */
@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        if (msg.getSuccess()){
            System.out.println("登录成功！！！ 用户ID:"+msg.getUserId()+";channel ID为："+ctx.channel().id());
            LoginUtil.markAsLogin(ctx.channel());
        }else {
            log.error("登录失败，原因{}",msg.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭");
    }
}
