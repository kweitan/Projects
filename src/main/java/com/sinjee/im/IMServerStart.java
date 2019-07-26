package com.sinjee.im;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author kweitan
 * IM服务端启动类
 */
public class IMServerStart {

    public static void main(String[] args){
        doStartUp() ;
    }

    //启动服务端
    public static void doStartUp(){

        //1.启动辅助类
        ServerBootstrap serverBootstrap = new ServerBootstrap() ;

        //2.boss OP_ACCEPT事件监听线程池
        NioEventLoopGroup boss = new NioEventLoopGroup(1) ;

        //3.work IO 读写事件监听线程池
        NioEventLoopGroup work = new NioEventLoopGroup() ;

        //4.辅助类加入boss work
        serverBootstrap.group(boss,work)
                //5.指定IO模式
                .channel(NioServerSocketChannel.class)
                //6.临时存放已经完成三次握手的请求的队列的最大长度
                .option(ChannelOption.SO_BACKLOG,1024)
                //7.是否开启TCP底层心跳机制 true为开启
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                //8.Nagle算法，true表关闭 提高实时性 如果需要减少发送次数减少网络交互，就开启
                .childOption(ChannelOption.TCP_NODELAY,true)
                //9.ChannelInitializer定义后续每条连接的数据读写 业务处理逻辑
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        //todo
                    }
                });
        //10.绑定端口
        serverBootstrap.bind(9000).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()){
                    System.out.println("端口启动成功");
                }else{
                    System.out.println("端口启动失败");
                }

            }
        }) ;

    }

}
