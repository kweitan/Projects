package com.sinjee.im.Handlers;

import com.sinjee.im.dto.LoginRequestPacket;
import com.sinjee.im.dto.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/***
 * @author kweitan
 * 登录请求处理器
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        //登录请求 校验后进行登录请求响应
        ctx.channel().writeAndFlush(login(msg));
    }

    private LoginResponsePacket login(LoginRequestPacket msg){
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket() ;
        loginResponsePacket.setVersion(msg.getVersion());
        //登录校验
        if(valid(msg)){
            //登录成功
            loginResponsePacket.setSuccess(true);
        }else {
            //登录失败
            loginResponsePacket.setReason("校验登录失败");
            loginResponsePacket.setSuccess(false);
        }

        return loginResponsePacket ;
    }

    //登录校验逻辑处理
    private boolean valid(LoginRequestPacket loginRequestPacket){
        return true ; //以后可以扩展
    }
}
