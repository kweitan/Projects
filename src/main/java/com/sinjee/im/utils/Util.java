package com.sinjee.im.utils;

public class Util {

    //byte转int方法
    public static int byteArrayToInt(byte[] bytes) {
        return Byte.toUnsignedInt(bytes[0]);
    }

    //int转byte方法
    public static byte[] intToByteLowArray(int i) {
        byte[] result = new byte[1];
        result[0] = (byte) (i & 0xFF);
        return result;
    }

    public static void main(String[] args){
        int num = 208 ;
        byte[] b = intToByteLowArray(num) ;
        System.out.println(b);

        System.out.println(Byte.toUnsignedInt(b[0]));

        System.out.println(byteToBit(b[0]));
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
