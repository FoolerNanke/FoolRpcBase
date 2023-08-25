package com.scj.foolRpcBase.entity;

import java.io.Serializable;

/**
 * @author suchangjie.NANKE
 * @Title: FoolRegisterResp
 * @date 2023/8/24 23:32
 * @description 注册中心注册响应体
 */

public class FoolRegisterResp implements Serializable {

    /**
     * 请求响应信息
     */
    private String message;

    /**
     * 请求响应码
     */
    private String code;

    /**
     * IP地址
     */
    private String IP;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
}
