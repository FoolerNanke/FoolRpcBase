package com.scj.foolRpcBase.entity;

import java.io.Serializable;

/**
 * @author suchangjie.NANKE
 * @Title: FoolRegisterReq
 * @date 2023/8/24 22:34
 * @description 注册中心注册请求体
 */

public class FoolRegisterReq implements Serializable {

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 时间戳
     */
    private long timeStamp;

    /**
     * 全类名
     */
    private String fullClassName;

    /**
     * 版本
     */
    private String version;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
