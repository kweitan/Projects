package com.sinjee.im.Handlers;

import com.sinjee.im.dto.LoginRequestPacket;
import com.sinjee.im.dto.LoginResponsePacket;
import com.sinjee.im.utils.RandomUtil;
import com.sinjee.im.utils.Session;
import com.sinjee.im.utils.SessionUtil;
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
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket() ;

        //登录校验【密码校验】
        if(valid(msg)){
            //登录成功
            loginResponsePacket.setSuccess(true);
            loginResponsePacket.setVersion(msg.getVersion());

            String userId = RandomUtil.getRandomUUID() ;
            loginResponsePacket.setUserId(userId);

            //Sesion绑定到channel
            SessionUtil.bindSession(new Session(userId,msg.getUserName()),ctx.channel());
            System.out.println("服务端绑定 channel ID:"+ctx.channel().id());
        }else {
            //登录失败
            loginResponsePacket.setReason("校验登录失败");
            loginResponsePacket.setSuccess(false);

            //ctx.channel().close() ;
        }

        ctx.channel().writeAndFlush(loginResponsePacket);

    }

    //登录校验逻辑处理
    private boolean valid(LoginRequestPacket loginRequestPacket){
        return true ; //以后可以扩展
    }
}
