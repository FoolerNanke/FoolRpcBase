package com.scj.foolRpcBase.runnable;

import com.scj.foolRpcBase.constant.Constant;
import com.scj.foolRpcBase.constant.RespCache;
import com.scj.foolRpcBase.entity.FoolCommonReq;
import com.scj.foolRpcBase.entity.FoolProtocol;
import io.netty.channel.Channel;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.EventLoopGroup;
import io.netty.util.concurrent.Promise;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author: suchangjie.NANKE
 * @Title: DefaultPingPongRunnable
 * @date: 2023/9/16
 * @description: //TODO
 */
@Slf4j
public class PingPongHandler extends FoolBaseRunnable{

    /**
     * 存储指定ip_port的有效截止时间
     * key:ip_port
     * value:有效截止时间
     */
    private final static Map<String, Long> ipMap = new ConcurrentHashMap<>();

    /**
     * 心跳检测执行线程池
     */
    private static final EventLoopGroup eventExecutors
            = new DefaultEventLoopGroup(Constant.NUMBER_OF_PING_PONG_WORKER);

    /**
     * 被检测的通道
     */
    public final Channel channel;

    /**
     * 被检测的ip+port
     */
    public final String ip_port;

    public PingPongHandler(Channel channel) {
        this.channel = channel;
        this.ip_port = channel.remoteAddress().toString();
        ipMap.put(ip_port, System.currentTimeMillis() + Constant.PING_PONG_TIME_GAP);
        eventExecutors.submit(this);
    }

    @Override
    public boolean beforeRun() {
        /*
        检验当前时刻是否需要发送心跳检测
        eg:上一秒可能刚刚与下游进行了一次通信
           则认为此时不需要进行心跳检测了
         */
        long time = System.currentTimeMillis();
        Long val = ipMap.getOrDefault(ip_port, 0L);
        if (val > time){
            eventExecutors.schedule(this
                    , val - time
                    , Constant.PING_PONG_TIME_UNIT);
            log.info("心跳成功 ip_port:{}", channel.remoteAddress().toString());
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void doRun() throws Throwable {
        /*
        设置请求体
        发送心跳请求
         */
        FoolProtocol<FoolCommonReq> foolProtocol = new FoolProtocol<>();
        foolProtocol.setData(new FoolCommonReq());
        //设置请求类型为 PING 类型
        foolProtocol.setRemoteType(Constant.PING_REQ);
        Promise<Object> promise = RespCache.handNewReq(foolProtocol);
        channel.writeAndFlush(foolProtocol);
        // 只要收到响应就行
        // 不需要对心跳响应内容做具体处理
        promise.get(Constant.TIME_OUT, TimeUnit.MILLISECONDS);
        log.info("心跳成功 ip_port:{}", ip_port);
    }

    @Override
    public void afterRun() {
        // 成功获取到下游响应 说明没毛病
        // 将本任务再次加入执行线程池
        eventExecutors.schedule(this
                , Constant.PING_PONG_TIME_GAP
                , Constant.PING_PONG_TIME_UNIT);
        // 成功收到一次响应
        // 将有效时间进行延长
        // 延长时间
        ipMap.put(ip_port,
                System.currentTimeMillis()
                        + Constant.PING_PONG_TIME_GAP);
    }

    @Override
    public void handError(Throwable t) {
        log.info("心跳检测异常 error:{} ip_port:{}"
                , t.getMessage()
                , ip_port);
    }

    /**
     * 添加通道保活时间
     * @param channel 通道
     */
    public static void addTime(Channel channel){
        String ip_port = channel.remoteAddress().toString();
        ipMap.put(ip_port, System.currentTimeMillis() + Constant.PING_PONG_TIME_GAP);
    }
}
