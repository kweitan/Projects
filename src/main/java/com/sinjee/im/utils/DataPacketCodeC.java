package com.sinjee.im.utils;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.sinjee.im.common.SerializeFactory;
import com.sinjee.im.config.Config;
import com.sinjee.im.dto.DataPacket;
import com.sinjee.im.dto.LoginRequestPacket;
import com.sinjee.im.enums.SerializeEnum;
import io.netty.buffer.ByteBuf;

public class DataPacketCodeC {

    public static DataPacketCodeC INSTANCE = new DataPacketCodeC() ;

    private DataPacketCodeC(){}

    public void encode(DataPacket packet,ByteBuf out) throws Exception{
        //1.序列化对象
        Codec<DataPacket> packetCodec = SerializeFactory.getProtobufCodeC(DataPacket.class) ;
        byte[] bytes = packetCodec.encode(packet) ;

        //2.编码过程
        out.writeInt(Config.MAGIC_NUM) ;
        out.writeByte(packet.getVersion()) ;
        out.writeByte(SerializeEnum.PROTOBUF_SERIALIZE.getValue()) ;
        out.writeByte(packet.getCommandType()) ;

        out.writeInt(bytes.length) ;
        out.writeBytes(bytes) ;

    }

    public void decode(ByteBuf in){

        //1.跳过魔数
        in.skipBytes(4) ;

        //2.跳过版本号
        in.skipBytes(1) ;

        //3.读序列号标识
        byte serializeType = in.readByte() ;

        //4.读指令集
        byte command = in.readByte() ;

        //5.读数据包长度
        int length = in.readInt() ;

        byte[] bytes = new byte[length] ;
        in.readBytes(bytes);

        //反序列化

    }

}
