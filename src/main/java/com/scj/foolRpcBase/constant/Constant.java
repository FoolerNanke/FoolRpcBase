package com.scj.foolRpcBase.constant;

import java.util.concurrent.TimeUnit;

/**
 * @author suchangjie.NANKE
 * @Title: Constant
 * @date 2023/8/12 20:46
 * @description 常量定义类
 */
public interface Constant {

    // ============= 杂项参数 ================

    /**
     * 消费者进行远程链接时的线程处理数量
     */
    int NUMBER_OF_RESP_PROMISE_WORKER = 4;

    /**
     * 心跳检测线程池处理数量
     */
    int NUMBER_OF_PING_PONG_WORKER = 4;

    /**
     * 字符串间隔
     */
    String GAP_POINT = ".";

    /**
     * 超时时间
     */
    long TIME_OUT = 3000;

    // ============= 请求类型 ================

    /**
     * 发送给注册中心的请求
     * 请求下游服务地址
     */
    byte REGISTER_REQ_GET_IP = 1;

    /**
     * 来自注册中心的响应
     * 获取响应地址
     */
    byte REGISTER_RESP_GET_IP = 2;

    /**
     * 将本地服务注册到注册中心
     */
    byte REGISTER_REQ_REG_CLASS = 3;

    /**
     * 将本地服务注册到注册中心的响应
     */
    byte REGISTER_RESP_REG_CLASS = 4;

    /**
     * 心跳请求
     */
    byte PING_REQ = 5;

    /**
     * 心跳响应
     */
    byte PONG_RESP = 6;

    /**
     * 发送给远程下游的请求
     */
    byte REMOTE_REQ = 11;

    /**
     * 来自远程下游的响应
     */
    byte REMOTE_RESP = 12;

    // ============= 序列化方法 ================

    /**
     * hessian 序列化方法
     */
    byte HESSIAN = 0;

    /**
     * jdk 序列化方法
     */
    byte JDK = 2;

    // ============= 请求基本参数 ================

    /**
     * 魔数
     */
    short MAGIC = (short) 0xF001;

    /**
     * 版本号
     */
    byte VERSION = 0x00;

    /**
     * 消息头长度
     * magic(short) + version(byte) + remoteType(byte)
     * + SerializableType(byte) + dataLen(int) + reqId(long)
     */
    int HEADER_LENGTH = 17;

    /**
     * 服务提供端口
     */
    int REGISTER_PORT = 5001;

    // ============ 普通常量 ============

    /**
     * 下游请求地址
     */
    int REMOTE_PORT = 4001;

    // ============ 心跳常量 ============

    /**
     * 心跳时间间隔
     */
    long PING_PONG_TIME_GAP = 10000;

    /**
     * 心跳时间间隔
     */
    TimeUnit PING_PONG_TIME_UNIT = TimeUnit.MILLISECONDS;
}
