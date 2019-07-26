package com.sinjee.im.common;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.sinjee.im.enums.SerializeEnum;

/**
 * @author kweitan
 * 序列化工厂
 */
public class SerializeFactory {

    public static <T> Codec<T> getProtobufCodeC(Class<T> T){
        return ProtobufProxy
                    .create(T) ;
        //todo 其他序列化类型留着以后扩展
    }
}
