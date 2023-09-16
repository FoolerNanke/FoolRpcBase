package com.scj.foolRpcBase.runnable;

/**
 * @author suchangjie.NANKE
 * @Title: PingPongScheduler
 * @date 2023/8/27 16:08
 * @description 基础 runnable 类
 */

public abstract class FoolBaseRunnable implements Runnable {

    /**
     * 心跳检测前置操作
     * @return 本次心跳检测是否需要进行
     */
    public abstract boolean beforeRun();

    /**
     * run的核心步骤
     */
    public abstract void doRun();

    /**
     * 心跳检测结束
     * 后置操作
     */
    public abstract void afterRun();

    /**
     * 心跳检测失败
     * 基类进行处理
     */
    public abstract void handError();

    @Override
    public void run() {
        if (!this.beforeRun())return;
        try {
            doRun();
            afterRun();
        } catch (Throwable t){
            handError();
        }
    }
}
