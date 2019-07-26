package com.sinjee.im.dto;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.sinjee.im.config.Config;
import com.sinjee.im.utils.Util;
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

    //魔数
    @Protobuf(fieldType = FieldType.INT32, order = 1, required = true)
    private Integer magic = 0x12345678 ; //默认

    //版本号
    @Protobuf(fieldType = FieldType.BYTES,order = 2,required = true)
    private byte[] version = Util.intToByteLowArray(Config.VERSION) ;

    //序列化算法标识
    @Protobuf(fieldType = FieldType.BYTES,order = 3,required = true)
    private byte[] serializeMark = Util.intToByteLowArray(Config.SERIALIZEMARK);

    //数据长度
    @Protobuf(fieldType = FieldType.INT32, order = 4, required = true)
    private Integer dataLength ;


}
