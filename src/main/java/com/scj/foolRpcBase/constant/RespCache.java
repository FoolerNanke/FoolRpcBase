package com.scj.foolRpcBase.constant;

import com.scj.foolRpcBase.entity.FoolProtocol;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Promise;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author suchangjie.NANKE
 * @Title: LocalCache
 * @date 2023/8/21 21:04
 * @description 本地缓存
 */
public class RespCache {

    /**
     * 请求回复 promise 存储对象
     * key:reqId
     * value:Promise<FoolResponse>
     */
    private static final Map<Long, Promise<Object>> PromiseMap
            = new ConcurrentHashMap<>();

    /**
     * 请求回执信息处理线程池
     */
    private static final EventLoopGroup respPromiseEventLoop
            = new NioEventLoopGroup(Constant.NUMBER_OF_RESP_PROMISE_WORKER);

    /**
     * 存储请求:Promise对象
     * @param foolProtocol 存储协议
     */
    public static Promise<Object> handNewReq(FoolProtocol<?> foolProtocol){
        Promise<Object> promise = new DefaultPromise<>(respPromiseEventLoop.next());
        PromiseMap.put(foolProtocol.getReqId(), promise);
        return promise;
    }

    /**
     * 根据响应获取对应的PromiseMap对象
     * @param foolProtocol 响应
     * @return Promise<FoolResponse>
     */
    public static Promise<Object> getPromise(FoolProtocol<?> foolProtocol){
        return PromiseMap.remove(foolProtocol.getReqId());
    }
}
