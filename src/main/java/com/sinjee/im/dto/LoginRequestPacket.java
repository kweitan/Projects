package com.sinjee.im.dto;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import lombok.Data;

/**
 * @author kweitan
 * 登录请求数据包
 */
@Data
public class LoginRequestPacket extends DataPacket {

    //指令
    @Protobuf(fieldType = FieldType.BYTES,order = 5,required = true)
    private byte[] command = Command.LOGIN_REQUEST.getVaule();

    //用户userId
    @Protobuf(fieldType = FieldType.STRING, order = 6, required = true)
    private String userId ;

    //用户名
    @Protobuf(fieldType = FieldType.STRING, order = 7, required = true)
    private String userName ;

    //用户密码
    @Protobuf(fieldType = FieldType.STRING, order = 8, required = true)
    private String userPassword ;
}
