package com.sinjee.im.dto;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.sinjee.im.enums.Command;
import com.sinjee.im.enums.SerializeEnum;
import lombok.Data;

/**
 * @author kweitan
 * 登录请求响应数据包
 */
@Data
public class LoginResponsePacket extends DataPacket {
    //指令
    @Protobuf(fieldType = FieldType.INT32,order = 2,required = true)
    private int command = Command.LOGIN_RESPONSE.getVaule();

    //序列化算法方式
    @Protobuf(fieldType = FieldType.INT32,order = 3,required = true)
    private Integer serializeMark = SerializeEnum.PROTOBUF_SERIALIZE.getValue();

    //应答通知
    @Protobuf(fieldType=FieldType.BOOL, order=4, required=true)
    private Boolean success ;

    //原因
    @Protobuf(fieldType = FieldType.STRING, order=5, required = false)
    private String reason ;

    @Protobuf(fieldType = FieldType.STRING, order=6, required = true)
    private String userId ;

    public int getCommandType(){
        return getCommand() ;
    }
}
