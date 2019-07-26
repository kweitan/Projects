package com.sinjee.im.dto;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * @author kweitan
 * 登录请求响应数据包
 */
public class LoginResponsePacket extends DataPacket {
    //指令
    @Protobuf(fieldType = FieldType.BYTES,order = 5,required = true)
    private byte[] command = Command.LOGIN_RESPONSE.getVaule();

    //应答通知
    @Protobuf(fieldType=FieldType.BOOL, order=6, required=true)
    private Boolean success ;

    //原因
    @Protobuf(fieldType = FieldType.STRING, order=7, required = false)
    private String reason ;
}
