package com.sinjee.im.Handlers;

import com.sinjee.im.utils.DataPacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/***
 * @author kweitan
 * 统一解码器 反序列化
 */
public class DataPacketDecode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(DataPacketCodeC.INSTANCE.decode(in)) ;
    }

}
