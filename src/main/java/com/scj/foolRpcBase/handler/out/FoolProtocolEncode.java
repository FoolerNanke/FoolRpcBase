package com.scj.foolRpcBase.handler.out;

import com.scj.foolRpcBase.serialize.FoolSerializeCache;
import com.scj.foolRpcBase.entity.FoolProtocol;
import com.scj.foolRpcBase.serialize.FoolSerialize;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author suchangjie.NANKE
 * @Title: FoolProtocolDecode
 * @date 2023/8/13 00:34
 * @description 消息编码器
 */

public class FoolProtocolEncode<T> extends MessageToByteEncoder<FoolProtocol<T>> {

    @Override
    protected void encode(ChannelHandlerContext context,
                          FoolProtocol<T> foolProtocol, ByteBuf byteBuf) {
        // 写入魔数
        byteBuf.writeShort(foolProtocol.getMagic());
        // 写入版本号
        byteBuf.writeByte(foolProtocol.getVersion());
        // 写入请求类型
        byteBuf.writeByte(foolProtocol.getRemoteType());
        // 写入序列化方法
        byteBuf.writeByte(foolProtocol.getSerializableType());
        // 写入请求ID
        byteBuf.writeLong(foolProtocol.getReqId());
        // 获取序列化实例
        FoolSerialize foolSerialize = FoolSerializeCache.getFoolSerialize(foolProtocol.getSerializableType());
        // 序列化请求体数据
        byte[] data = foolSerialize.serialize(foolProtocol.getData());
        // 写入请求体长度
        byteBuf.writeInt(data.length);
        // 写入请求体
        byteBuf.writeBytes(data);
    }
}
