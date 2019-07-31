package com.sinjee.im.Handlers;

import com.sinjee.im.dto.CreateGroupRequestPacket;
import com.sinjee.im.dto.CreateGroupResponsePacket;
import com.sinjee.im.utils.RandomUtil;
import com.sinjee.im.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        List<String> userIdList = msg.getUserIdList() ;
        List<String> userNameList = new ArrayList<>();
        //创建一个 channel 分组
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        //把自己也加进去
        channelGroup.add(ctx.channel()) ;

        // 2. 筛选出待加入群聊的用户的 channel 和 userName
        for (String userId : userIdList) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        }

        //4. 保存群
        String channelGroupId = RandomUtil.getRandomUUID() ;
        SessionUtil.bindChannelGroup(channelGroupId,channelGroup);

        //3. 创建群聊创建结果的响应
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket() ;
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupId(channelGroupId);
        createGroupResponsePacket.setUserNameList(userNameList);

        System.out.println("command：" + createGroupResponsePacket.getCommand()) ;

        // 4. 给每个客户端发送拉群通知
        channelGroup.writeAndFlush(createGroupResponsePacket);

        System.out.println("群创建成功，id 为["+createGroupResponsePacket.getGroupId()+"]") ;
        System.out.println("群里面有：" + createGroupResponsePacket.getUserNameList()) ;
    }
}
