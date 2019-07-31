package com.sinjee.im.clientui;

import com.sinjee.im.dto.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LoginConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequest = new LoginRequestPacket() ;
        System.out.println("输入用户名称登录：");
        String userName = scanner.nextLine() ;
        loginRequest.setUserName(userName);
        loginRequest.setUserPassword("default");

        channel.writeAndFlush(loginRequest) ;
        System.out.println("服务端channel ID:"+channel.id());

        try {
            Thread.sleep(5000);
        }catch (InterruptedException ie){

        }

    }
}
