package com.scj.foolRpcBase.handler.in;

import com.scj.foolRpcBase.constant.Constant;
import com.scj.foolRpcBase.entity.FoolCommonResp;
import com.scj.foolRpcBase.entity.FoolProtocol;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: suchangjie.NANKE
 * @Title: PingPongRespHandler
 * @date: 2023/9/17
 * @description: //TODO
 */
@ChannelHandler.Sharable
@Slf4j
public class PingPongRespHandler extends SimpleChannelInboundHandler<FoolProtocol<?>> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
                                FoolProtocol<?> protocol) {
        byte remoteType = protocol.getRemoteType();
        if (remoteType == Constant.PING_REQ){
            FoolProtocol<FoolCommonResp> respFoolProtocol = new FoolProtocol<>();
            respFoolProtocol.setRemoteType(Constant.PONG_RESP);
            respFoolProtocol.setReqId(protocol.getReqId());
            respFoolProtocol.setData(new FoolCommonResp());
            log.info("收到心跳请求 reqId = {}", protocol.getReqId());
            ctx.writeAndFlush(respFoolProtocol);
            ctx.fireChannelRead(protocol);
        }
    }
}
