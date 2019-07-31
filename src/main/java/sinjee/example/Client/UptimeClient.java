package sinjee.example.Client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author kweitan
 * 心跳连接客户端
 */
public class UptimeClient {

    public static String HOST = System.getProperty("host","127.0.0.1") ;
    public static int PORT = Integer.parseInt(System.getProperty("port","9001")) ;

    //尝试获取一条连接间隔时间
    public static final int RECONNECT_DELAY = Integer.parseInt(System.getProperty("reconnectDelay", "5"));

    //10秒服务端没有数据发送过来 进行重新连接
    private static final int READ_TIMEOUT = Integer.parseInt(System.getProperty("readTimeout", "10"));

    private static final UptimeClientHandler handler = new UptimeClientHandler();
    private static final Bootstrap bs = new Bootstrap();

    public static void main(String [] args){

        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup() ;
        bs.group(eventLoopGroup).channel(NioServerSocketChannel.class)
                .remoteAddress(HOST,PORT).handler(new ChannelInitializer<SocketChannel>(){

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new IdleStateHandler(READ_TIMEOUT,0,0)) ;
                ch.pipeline().addLast(handler) ;
            }
        }) ;

        bs.connect() ;
    }

    public static void reConnect(){
        bs.connect().addListener((ChannelFutureListener) future ->{
            if (future.cause() != null){
                handler.startTime = -1;
                handler.println("Failed to connect: " + future.cause());
            }
        }) ;
    }
}
