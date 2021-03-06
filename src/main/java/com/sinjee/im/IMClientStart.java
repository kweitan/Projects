package com.sinjee.im;

import com.sinjee.im.Handlers.*;
import com.sinjee.im.clientui.ConsoleCommandManager;
import com.sinjee.im.clientui.LoginConsoleCommand;
import com.sinjee.im.dto.LoginRequestPacket;
import com.sinjee.im.dto.MessageRequestPacket;
import com.sinjee.im.utils.LoginUtil;
import com.sinjee.im.utils.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author kweitan
 * IM客户端启动类
 */
@Slf4j
public class IMClientStart {

    private static final int MAX_RETRY = 5 ; //指数退避算法 默认重复测数

    public static void main(String[] args){
        doStartUp() ;
    }

    public static void doStartUp(){
        //1.启动辅助类
        Bootstrap bootstrap = new Bootstrap() ;

        //2.IO读写线程组
        NioEventLoopGroup work = new NioEventLoopGroup() ;

        //3.加入线程组
        bootstrap.group(work)
                //4.设置IO模式
                .channel(NioSocketChannel.class)
                //连接超时
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5000)
                //保持心跳连接
                .option(ChannelOption.SO_KEEPALIVE,true)
                //true 表示减少频繁网络连接
                .option(ChannelOption.TCP_NODELAY,true)
                //5.IO处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new VerifyHandler()) ; //拆包器+协议验证
                        ch.pipeline().addLast(new DataPacketDecode()); //解码器


                        //业务逻辑处理
                        ch.pipeline().addLast(new LoginResponseHandler()) ;

                        //登出逻辑
                        ch.pipeline().addLast(new LogoutResponseHandler()) ;

                        //创建群
                        ch.pipeline().addLast(new CreateGroupResponseHandler()) ;

                        //消息处理
                        ch.pipeline().addLast(new MessageResponseHandler()) ;

                        ch.pipeline().addLast(new DataPacketEncode()) ; //编码器

                    }
                }) ;

        //6.连接
        connect(bootstrap,"127.0.0.1",9000,MAX_RETRY) ;

    }

    /****
     *
     * @param bootstrap
     * @param host
     * @param port
     * @param retry 默认重复测数
     */
    private static void connect(Bootstrap bootstrap,String host,int port,int retry){
        //6.连接
        bootstrap.connect(host,port).addListener(future -> {
            if(future.isSuccess()){
                System.out.println("连接成功");
                Channel channel = ((ChannelFuture)future).channel() ;
                //启动控制台线程
                startConsoleThread(channel)  ;
            }else if(retry == 0){
                System.out.println("重复次数已经用完，放弃连接");
            }else {
                //实现指数退避的方式实现重新连接
                //重新连接
                int order = (MAX_RETRY - retry) + 1;
                int delay = 1 << order ;
                log.error("{}:连接失败，第{}次重连接......",new Date(),order);
                bootstrap.config().group().schedule(
                        ()->connect(bootstrap,host,port,retry -1),delay,TimeUnit.SECONDS) ;
            }
        });
    }

    //控制台线程
    private static void startConsoleThread(Channel channel){
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand() ;
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();

        Scanner scanner = new Scanner(System.in) ;
        new Thread(() -> {
            while (!Thread.interrupted()){
                if (!LoginUtil.hasLogin(channel)){
                    //登录请求
                    loginConsoleCommand.exec(scanner,channel);
                }else {
                    consoleCommandManager.exec(scanner, channel);
                }
            }
        }).start();
    }

}
