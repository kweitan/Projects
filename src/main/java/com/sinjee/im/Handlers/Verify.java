package com.sinjee.im.Handlers;

import com.sinjee.im.config.Config;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.List;

/***
 * @author kweitan
 * 拒绝非本协议连接
 * 基于长度域拆包器【由于我们使用自定义协议4+1+1+1】
 */
public class Verify extends LengthFieldBasedFrameDecoder {

    //长度域的偏移
    private static final int LENGTH_FIELD_OFFSET = 7;
    //长度域的长度
    private static final int LENGTH_FIELD_SIZE = 4;

    public Verify(){
        super(Integer.MAX_VALUE,LENGTH_FIELD_OFFSET,LENGTH_FIELD_SIZE);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        int magicNum = in.readInt() ;
        if(magicNum != Config.MAGIC_NUM){
            ctx.channel().close() ;
            return null ;
        }

        return super.decode(ctx,in) ;
    }

}
