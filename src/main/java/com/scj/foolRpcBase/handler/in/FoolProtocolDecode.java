package com.scj.foolRpcBase.handler.in;

import com.scj.foolRpcBase.serialize.FoolSerializeCache;
import com.scj.foolRpcBase.constant.Constant;
import com.scj.foolRpcBase.entity.*;
import com.scj.foolRpcBase.exception.ExceptionEnum;
import com.scj.foolRpcBase.exception.FoolException;
import com.scj.foolRpcBase.serialize.FoolSerialize;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author suchangjie.NANKE
 * @Title: FoolProtocolDecode
 * @date 2023/8/13 11:40
 * @description 消息解码器
 */

public class FoolProtocolDecode extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
        int len = byteBuf.readableBytes();
        if (len < Constant.HEADER_LENGTH) {
            // 当前接收到的消息长度不足以解码
            return;
        }
        // 标记读取起点
        byteBuf.markReaderIndex();
        // 获取魔数
        short magic = byteBuf.readShort();
        if (magic != Constant.MAGIC) {
            throw new FoolException(ExceptionEnum.PROTOCOL_NOT_MATCH);
        }
        // 获取版本号
        byte version = byteBuf.readByte();
        // 获取请求类型
        byte remoteType = byteBuf.readByte();
        // 获取编序列化方式
        byte serializeType = byteBuf.readByte();
        // 获取请求ID
        long reqId = byteBuf.readLong();
        // 获取消息体长度
        int dataLen = byteBuf.readInt();
        if (byteBuf.readableBytes() < dataLen) {
            // 消息体长度不足以解码 指针会退
            byteBuf.resetReaderIndex();
            return;
        }
        // 读取消息体
        byte[] data = new byte[dataLen];
        byteBuf.readBytes(data);
        // 填充基本数据
        FoolProtocol<Object> foolProtocol = new FoolProtocol<>();
        foolProtocol.setMagic(magic);
        foolProtocol.setRemoteType(remoteType);
        foolProtocol.setVersion(version);
        foolProtocol.setSerializableType(serializeType);
        foolProtocol.setReqId(reqId);
        // 消息体反序列化
        FoolSerialize foolSerialize = FoolSerializeCache.getFoolSerialize(serializeType);
        // 填充消息体
        Object obj = null;
        switch (remoteType) {
            case Constant.REMOTE_REQ:
                obj = foolSerialize.deSerialize(data, FoolRemoteReq.class);
                break;
            case Constant.REMOTE_RESP:
                obj = foolSerialize.deSerialize(data, FoolRemoteResp.class);
                break;
            case Constant.REGISTER_REQ_REG_CLASS:
            case Constant.REGISTER_REQ_GET_IP:
            case Constant.REGISTER_PING_REQ:
                obj = foolSerialize.deSerialize(data, FoolCommonReq.class);
                break;
            case Constant.REGISTER_RESP_REG_CLASS:
            case Constant.REGISTER_RESP_GET_IP:
            case Constant.REGISTER_PONG_RESP:
                obj = foolSerialize.deSerialize(data, FoolCommonResp.class);
                break;
            default:
                break;
        }
        foolProtocol.setData(obj);
        // 完成消息解码
        list.add(foolProtocol);
    }
}
