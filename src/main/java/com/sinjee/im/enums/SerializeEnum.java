package com.sinjee.im.enums;

import lombok.Getter;

@Getter
public enum SerializeEnum {
    PROTOBUF_SERIALIZE(1), //表示使用protobuf 序列化和反序列化
    JAVA_SERIALIZE(2), //表示使用JAVA 序列化和反序列化
    FASTJSON_SERIALIZE(3); //表示使用FASTJSON 序列化和反序列化
    private SerializeEnum(int value){
        this.value = value ;
    }
    private final int value ;
}
