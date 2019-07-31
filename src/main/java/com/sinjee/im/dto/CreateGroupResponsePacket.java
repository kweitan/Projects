package com.sinjee.im.dto;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.sinjee.im.enums.Command;
import com.sinjee.im.enums.SerializeEnum;
import lombok.Data;

import java.util.List;

@Data
public class CreateGroupResponsePacket extends DataPacket {
    //指令
    @Protobuf(fieldType = FieldType.INT32,order = 2,required = true)
    private Integer command = Command.ADD_GROUP_RESPONSE.getVaule();

    //序列化算法方式
    @Protobuf(fieldType = FieldType.INT32,order = 3,required = true)
    private Integer serializeMark = SerializeEnum.PROTOBUF_SERIALIZE.getValue();

    //应答通知
    @Protobuf(fieldType=FieldType.BOOL, order=4, required=true)
    private Boolean success ;

    @Protobuf(fieldType = FieldType.STRING, order = 5, required = false)
    private String groupId ;

    @Protobuf(fieldType = FieldType.STRING, order = 6, required = false)
    private List<String> userNameList ;

    @Override
    public int getCommandType() {
        return getCommand();
    }
}
