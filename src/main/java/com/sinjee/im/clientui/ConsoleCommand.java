package com.sinjee.im.clientui;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author kweitan
 * 重新构建控制台
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel) ;
}
