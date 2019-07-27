package com.sinjee.im.Handlers;

import com.sinjee.im.dto.DataPacket;
import com.sinjee.im.utils.DataPacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/***
 * @author kweitan
 * 统一编码器 序列化
 */
public class DataPacketEncode extends MessageToByteEncoder<DataPacket> {
    @Override
    protected void encode(ChannelHandlerContext ctx, DataPacket msg, ByteBuf out) throws Exception {
        DataPacketCodeC.INSTANCE.encode(msg,out);
    }
}
