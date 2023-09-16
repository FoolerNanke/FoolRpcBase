package com.scj.foolRpcBase.handler.resp;

import com.scj.foolRpcBase.constant.Constant;
import com.scj.foolRpcBase.constant.RespCache;
import com.scj.foolRpcBase.entity.FoolProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.Promise;

/**
 * @author suchangjie.NANKE
 * @Title: FoolRespHandler
 * @date 2023/8/27 17:04
 * @description 响应最终处理器
 *              将响应信息写入promise
 */
public class FoolRespHandler extends SimpleChannelInboundHandler<FoolProtocol<?>> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx
            , FoolProtocol<?> foolProtocol) {
        if (foolProtocol.getRemoteType() == Constant.REGISTER_PONG_RESP
                || foolProtocol.getRemoteType() == Constant.REMOTE_RESP ){
            Promise<Object> promise = RespCache.getPromise(foolProtocol);
            promise.setSuccess(foolProtocol.getData());
        }
        ctx.fireChannelRead(foolProtocol);
    }
}
