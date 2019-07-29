package com.sinjee.im.Handlers;

import com.sinjee.im.utils.LoginUtil;
import com.sinjee.im.utils.Session;
import com.sinjee.im.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!SessionUtil.hasLogin(ctx.channel())){
            ctx.channel().close() ;
        }else {
            //验证过后删除
            ctx.pipeline().remove(this) ;

            ctx.fireChannelRead(msg);
        }

    }
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception{
        if (SessionUtil.hasLogin(ctx.channel())){
            log.info("当前连接登录完毕，无需再次验证，AuthHandler 被移除") ;
        }else{
            log.info("无登陆验证，强制关闭连接");
        }
    }
}
