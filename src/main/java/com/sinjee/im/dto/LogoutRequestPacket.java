package com.sinjee.im.dto;


import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.sinjee.im.enums.Command;
import com.sinjee.im.enums.SerializeEnum;
import lombok.Data;

/***
 * @author kweitan
 * 登出数据包
 */
@Data
public class LogoutRequestPacket extends DataPacket{
    //指令
    @Protobuf(fieldType = FieldType.INT32,order = 2,required = true)
    private Integer command = Command.LOGOUT_REQUEST.getVaule();

    //序列化算法方式
    @Protobuf(fieldType = FieldType.INT32,order = 3,required = true)
    private Integer serializeMark = SerializeEnum.PROTOBUF_SERIALIZE.getValue();

    @Override
    public int getCommandType() {
        return getCommand();
    }
}
