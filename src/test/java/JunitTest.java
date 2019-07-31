import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufIDLGenerator;
import com.sinjee.im.common.CommandBeanFactory;
import com.sinjee.im.common.SerializeFactory;
import com.sinjee.im.dto.LoginRequestPacket;
import com.sinjee.im.dto.LoginResponsePacket;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

public class JunitTest {
    @Test
    public void test1(){
        System.out.println("hello world");

//        Codec<LoginRequestPacket> loginRequestPacketCodec = ProtobufProxy
//                .create(LoginRequestPacket.class);
        Codec<LoginRequestPacket> packetCodec = SerializeFactory.getProtobufCodeC(LoginRequestPacket.class) ;

        CommandBeanFactory.getPacketByCommand(1) ;



        LoginRequestPacket login = new LoginRequestPacket() ;
        login.setUserId(UUID.randomUUID().toString());
        login.setUserName("kweitan");
        login.setUserPassword("123456789");

        try {
            //序列化
            byte[] encode = packetCodec.encode(login);
            System.out.println(encode);

            //反序列化
            LoginRequestPacket newLogin = packetCodec.decode(encode) ;
            System.out.println(newLogin.toString());

            String code = ProtobufIDLGenerator.getIDL(LoginRequestPacket.class);

            System.out.println(code);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Test
    public void test2(){
        Codec<LoginResponsePacket> packetCodec = SerializeFactory.getProtobufCodeC(LoginResponsePacket.class) ;
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket() ;

        loginResponsePacket.setSuccess(true);
        loginResponsePacket.setReason("连接成功");

        try {
            //序列化
            byte[] encode = packetCodec.encode(loginResponsePacket);
            System.out.println(encode);

            //反序列化
            LoginResponsePacket newLogin = packetCodec.decode(encode) ;
            System.out.println(newLogin.toString());

            String code = ProtobufIDLGenerator.getIDL(LoginResponsePacket.class);

            System.out.println(code);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Test
    public void test3(){
        String HOST = System.getProperty("host","127.0.0.1") ;
        int PORT = Integer.parseInt(System.getProperty("port","9001")) ;
        System.out.println(System.nanoTime()) ;

        System.out.printf("HOST:%s  PORT:%d",HOST,PORT) ;

        System.out.println(UUID.randomUUID().toString().split("-")[0]) ;
    }

}
