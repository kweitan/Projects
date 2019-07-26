package com.sinjee.im.dto;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.sinjee.im.config.Config;
import lombok.Data;

/**
 * @author kweitan
 * 数据包格式【学习闪电侠的数据结构】
 * ---------------------------------------------------------------------------------------------
 * 魔数(4 byte)|版本号(1 byte)|序列化算法标识(1 byte)|指令(1 byte)|数据长度(4 byte)|数据内容(N byte)
 * ---------------------------------------------------------------------------------------------
 */

@Data
public abstract class DataPacket {

    //版本号
    @Protobuf(fieldType = FieldType.INT32, order = 1, required = true)
    private Integer version = Config.VERSION ;

    public abstract int getCommandType() ;
}
