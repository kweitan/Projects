import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufIDLGenerator;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.sinjee.im.dto.LoginRequestPacket;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

public class JunitTest {
    @Test
    public void test1(){
        System.out.println("hello world");

        Codec<LoginRequestPacket> loginRequestPacketCodec = ProtobufProxy
                .create(LoginRequestPacket.class);

        LoginRequestPacket login = new LoginRequestPacket() ;
        login.setDataLength(256);
        login.setUserId(UUID.randomUUID().toString());
        login.setUserName("kweitan");
        login.setUserPassword("123456789");

        try {
            //序列化
            byte[] encode = loginRequestPacketCodec.encode(login);

            //反序列化
            LoginRequestPacket newLogin = loginRequestPacketCodec.decode(encode) ;

            System.out.println(newLogin.getMagic());

            String code = ProtobufIDLGenerator.getIDL(LoginRequestPacket.class);

            System.out.println(code);

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
