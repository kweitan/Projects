package com.sinjee.im.dto;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.sinjee.im.enums.Command;
import com.sinjee.im.enums.SerializeEnum;
import lombok.Data;

/**
 * @author kweitan
 * 登录请求数据包
 */
@Data
public class LoginRequestPacket extends DataPacket {

    //指令
    @Protobuf(fieldType = FieldType.INT32,order = 2,required = true)
    private Integer command = Command.LOGIN_REQUEST.getVaule();

    //序列化算法方式
    @Protobuf(fieldType = FieldType.INT32,order = 3,required = true)
    private Integer serializeMark = SerializeEnum.PROTOBUF_SERIALIZE.getValue();

    //用户userId
    @Protobuf(fieldType = FieldType.STRING, order = 4, required = false)
    private String userId ;

    //用户名
    @Protobuf(fieldType = FieldType.STRING, order = 5, required = true)
    private String userName ;

    //用户密码
    @Protobuf(fieldType = FieldType.STRING, order = 6, required = true)
    private String userPassword ;

    public int getCommandType(){
        return getCommand() ;
    }
}
