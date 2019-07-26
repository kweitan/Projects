package com.sinjee.im.common;

import com.sinjee.im.dto.DataPacket;
import com.sinjee.im.enums.Command;
import com.sinjee.im.dto.* ;

public class CommandBeanFactory {
    public static Class<? extends DataPacket> getPacketByCommand(int command){
        Class<? extends DataPacket> cc = null ;
        if (Command.LOGIN_REQUEST.getVaule() == command){
            cc = LoginRequestPacket.class ;
        }else if (Command.LOGIN_RESPONSE.getVaule() == command){
            cc = LoginResponsePacket.class ;
        }else if(Command.CLIENT_SEND_MSG.getVaule() == command){
            cc = MessageRequestPacket.class ;
        }else if(Command.SERVER_SEND_MSG.getVaule() == command){
            cc = MessageResponsePacket.class ;
        }else{

        }
        return  cc ;
    }
}
