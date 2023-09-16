package com.scj.foolRpcBase.handler.in;

import com.scj.foolRpcBase.constant.Constant;
import com.scj.foolRpcBase.entity.FoolProtocol;
import com.scj.foolRpcBase.runnable.PingPongHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: suchangjie.NANKE
 * @Title: AddTimeHandler
 * @date: 2023/9/17
 * @description: 对于指定请求或者响应 可以认为tcp另外那一头的机器还健康
 *               下一次向他发起心跳请求的时间可以稍微延后一些
 */
public class AddTimeHandler extends SimpleChannelInboundHandler<FoolProtocol<?>> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
                                FoolProtocol<?> protocol) {
        if (protocol.getRemoteType() == Constant.REGISTER_RESP_GET_IP
        || protocol.getRemoteType() == Constant.REGISTER_REQ_GET_IP){
            PingPongHandler.addTime(ctx.channel());
        }
        ctx.fireChannelRead(protocol);
    }
}
