package com.sinjee.im.utils;

/**
 * @author kweitan
 * 通用工具类
 */
public class CommonUtil {

    //byte转int方法
//    public static int byteArrayToInt(byte[] bytes) {
//        return Byte.toUnsignedInt(bytes[0]);
//    }

    //int转byte方法
    public static byte intToByte(int i) {
        byte result = (byte) (i & 0xFF);
        return result;
    }

    //byte转int方法
    public static int byteToInt(byte b){
        return Byte.toUnsignedInt(b) ;
    }

    public static void main(String[] args){
        int num = 8 ;
        byte b = intToByte(num) ;
        System.out.println(b);
        System.out.println(byteToBit(b));
        System.out.println(byteToInt(b));
    }

    //把byte转为字符串的bit
    public static String byteToBit(byte b) {
        return ""
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);
    }

}
