package com.sinjee.im.Handlers;

import com.sinjee.im.dto.LogoutRequestPacket;
import com.sinjee.im.dto.LogoutResponsePacket;
import com.sinjee.im.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author kweitan
 * 登出请求
 */
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception {
        //1.解绑session
        SessionUtil.unBindSession(ctx.channel());

        //2.返回客户端
        ctx.channel().writeAndFlush(new LogoutResponsePacket()).addListener(future -> {
            if (future.isDone()){
                //3.任务完成 关闭channel
                ctx.channel().close() ;
            }
        }) ;

    }
}
