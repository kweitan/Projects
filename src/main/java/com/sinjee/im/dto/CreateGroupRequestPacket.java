package com.sinjee.im.dto;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.sinjee.im.enums.Command;
import com.sinjee.im.enums.SerializeEnum;
import lombok.Data;

import java.util.List;

/**
 * @author kweitan
 * 创建群组请求包
 */
@Data
public class CreateGroupRequestPacket extends DataPacket{
    //指令
    @Protobuf(fieldType = FieldType.INT32,order = 2,required = true)
    private Integer command = Command.ADD_GROUP_REQUEST.getVaule();

    //序列化算法方式
    @Protobuf(fieldType = FieldType.INT32,order = 3,required = true)
    private Integer serializeMark = SerializeEnum.PROTOBUF_SERIALIZE.getValue();

    @Protobuf(fieldType = FieldType.STRING, order = 4, required = false)
    private List<String> userIdList;

    @Override
    public int getCommandType() {
        return getCommand();
    }
}
