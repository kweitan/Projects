package com.sinjee.im.dto;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.sinjee.im.enums.Command;
import com.sinjee.im.enums.SerializeEnum;
import lombok.Data;

/***
 * @author kweitan
 * 接收消息对象
 */
@Data
public class MessageResponsePacket extends DataPacket {
    //指令
    @Protobuf(fieldType = FieldType.INT32,order = 2,required = true)
    private Integer command = Command.SERVER_SEND_MSG.getVaule();

    //序列化算法方式
    @Protobuf(fieldType = FieldType.INT32,order = 3,required = true)
    private Integer serializeMark = SerializeEnum.PROTOBUF_SERIALIZE.getValue();


    //来自谁
    @Protobuf(fieldType = FieldType.STRING, order = 4, required = true)
    private String fromUserId ;


    //来自谁名称
    @Protobuf(fieldType = FieldType.STRING, order = 5, required = true)
    private String fromUserName ;

    //消息内容
    @Protobuf(fieldType = FieldType.STRING, order = 6, required = false)
    private String message ;

    public int getCommandType(){
        return getCommand() ;
    }
}
