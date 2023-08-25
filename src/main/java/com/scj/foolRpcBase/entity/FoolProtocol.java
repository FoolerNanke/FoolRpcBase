package com.scj.foolRpcBase.entity;

import com.scj.foolRpcBase.constant.Constant;

import java.io.Serializable;
import java.util.Random;

/**
 * @author suchangjie.NANKE
 * @Title: FoolProtocol
 * @date 2023/8/12 23:41
 * @description 协议类
 */
public class FoolProtocol<T> implements Serializable {

    // 魔数
    private short magic = Constant.MAGIC;

    // 版本号
    private byte version = Constant.VERSION;

    // 序列化方法
    private byte SerializableType = Constant.HESSIAN;

    // 请求类型
    private byte remoteType;

    // 请求 ID
    private Long reqId = new Random().nextLong();

    // 请求数据
    private T data;

    public short getMagic() {
        return magic;
    }

    public void setMagic(short magic) {
        this.magic = magic;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public byte getSerializableType() {
        return SerializableType;
    }

    public void setSerializableType(byte serializableType) {
        SerializableType = serializableType;
    }

    public byte getRemoteType() {
        return remoteType;
    }

    public void setRemoteType(byte remoteType) {
        this.remoteType = remoteType;
    }

    public Long getReqId() {
        return reqId;
    }

    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
