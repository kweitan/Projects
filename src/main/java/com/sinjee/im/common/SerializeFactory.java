package com.sinjee.im.common;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.sinjee.im.dto.*;
import com.sinjee.im.enums.Command;
import com.sinjee.im.enums.SerializeEnum;
import com.sinjee.im.utils.CommonUtil;

/**
 * @author kweitan
 * 序列化工厂
 */
public class SerializeFactory {

    public static Codec<LoginRequestPacket>  LoginRequestPacketCodec = getProtobufCodeC(LoginRequestPacket.class) ;

    public static Codec<LoginResponsePacket>  loginResponsePacketCodec = getProtobufCodeC(LoginResponsePacket.class) ;

    public static Codec<MessageRequestPacket>  messageRequestPacketCodec= getProtobufCodeC(MessageRequestPacket.class) ;

    public static Codec<MessageResponsePacket>  messageResponsePacketCodec= getProtobufCodeC(MessageResponsePacket.class) ;

    public static Codec<LogoutRequestPacket>  logoutRequestPacketCodec = getProtobufCodeC(LogoutRequestPacket.class) ;

    public static Codec<LogoutResponsePacket>  logoutResponsePacketCodec = getProtobufCodeC(LogoutResponsePacket.class) ;

    public static <T> Codec<T> getProtobufCodeC(Class<T> T){
        return ProtobufProxy
                    .create(T) ;
        //todo 其他序列化类型留着以后扩展
    }


    public static byte[] serialize(int command, int serializeType, DataPacket packet) throws Exception{
        byte[] bytes = null;
            if (command == Command.LOGIN_REQUEST.getVaule()){

                LoginRequestPacket loginRequestPacket = (LoginRequestPacket)packet ;
                //判断序列化方式
                if(loginRequestPacket.getSerializeMark() == serializeType){
                    //使用其他规则进行序列化
                }

                Codec<LoginRequestPacket> codec= LoginRequestPacketCodec ;
                try{
                    bytes = codec.encode(loginRequestPacket) ;
                }catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }


            }else if (command == Command.LOGIN_RESPONSE.getVaule()){
                Codec<LoginResponsePacket> codec= loginResponsePacketCodec ;
                bytes = codec.encode((LoginResponsePacket)packet) ;

            }else if (command == Command.CLIENT_SEND_MSG.getVaule()){
                Codec<MessageRequestPacket> codec = messageRequestPacketCodec ;
                bytes = codec.encode((MessageRequestPacket)packet) ;

            }else if (command == Command.SERVER_SEND_MSG.getVaule()){
                Codec<MessageResponsePacket> codec= messageResponsePacketCodec ;
                bytes = codec.encode((MessageResponsePacket)packet) ;

            }else if (command == Command.LOGOUT_REQUEST.getVaule()){
                Codec<LogoutRequestPacket> codec= logoutRequestPacketCodec ;
                bytes = codec.encode((LogoutRequestPacket)packet) ;

            }else if (command == Command.LOGOUT_RESPONSE.getVaule()){
                Codec<LogoutResponsePacket> codec= logoutResponsePacketCodec ;
                bytes = codec.encode((LogoutResponsePacket)packet) ;

            }


        return bytes ;
    }

    public static DataPacket deSerialize(byte command,byte serializeType,byte[] bytes)throws Exception{

        DataPacket dataPacket = null ;
        if (CommonUtil.byte2Int(serializeType) == SerializeEnum.PROTOBUF_SERIALIZE.getValue()){
            if (CommonUtil.byte2Int(command) == Command.LOGIN_REQUEST.getVaule()){
                try {

                }catch (Exception e){

                }
                Codec<LoginRequestPacket> codec= LoginRequestPacketCodec ;
                LoginRequestPacket loginRequestPacket = codec.decode(bytes) ;
                dataPacket = loginRequestPacket ;

            }else if (CommonUtil.byte2Int(command) == Command.LOGIN_RESPONSE.getVaule()){
                Codec<LoginResponsePacket> codec= loginResponsePacketCodec ;
                LoginResponsePacket loginResponsePacket = codec.decode(bytes) ;
                dataPacket = loginResponsePacket ;

            }else if (CommonUtil.byte2Int(command) == Command.CLIENT_SEND_MSG.getVaule()){
                Codec<MessageRequestPacket> codec= messageRequestPacketCodec ;
                MessageRequestPacket messageRequestPacket = codec.decode(bytes) ;
                dataPacket = messageRequestPacket ;

            }else if (CommonUtil.byte2Int(command) == Command.SERVER_SEND_MSG.getVaule()){
                Codec<MessageResponsePacket> codec= messageResponsePacketCodec ;
                MessageResponsePacket messageResponsePacket = codec.decode(bytes) ;
                dataPacket = messageResponsePacket ;
            }
        }
        return dataPacket ;
    }
}
