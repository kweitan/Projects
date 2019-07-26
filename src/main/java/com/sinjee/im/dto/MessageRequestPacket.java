package com.sinjee.im.dto;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.sinjee.im.enums.Command;
import com.sinjee.im.enums.SerializeEnum;
import lombok.Data;

/***
 * @author kweitan
 * 发送消息对象
 */
@Data
public class MessageRequestPacket extends DataPacket{
    //指令
    @Protobuf(fieldType = FieldType.INT32,order = 2,required = true)
    private Integer command = Command.LOGIN_REQUEST.getVaule();

    //序列化算法方式
    @Protobuf(fieldType = FieldType.INT32,order = 3,required = true)
    private Integer serializeMark = SerializeEnum.PROTOBUF_SERIALIZE.getValue();

    //消息内容
    @Protobuf(fieldType = FieldType.STRING, order = 4, required = false)
    private String message ;

    public int getCommandType(){
        return getCommand() ;
    }
}
