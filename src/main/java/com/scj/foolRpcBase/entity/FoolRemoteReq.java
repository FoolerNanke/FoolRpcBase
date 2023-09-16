package com.scj.foolRpcBase.entity;

import java.io.Serializable;

/**
 * @author suchangjie.NANKE
 * @Title: FoolReqest
 * @date 2023/8/13 00:12
 * @description 远程请求类
 */

public class FoolRemoteReq implements Serializable {

    // 请求全类名
    private String fullClassName;

    // 请求方法名
    private String methodName;

    // 参数列表
    private Object[] args;

    // 参数类型
    private String[] argsType;

    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String[] getArgsType() {
        return argsType;
    }

    public void setArgsType(String[] argsType) {
        this.argsType = argsType;
    }
}
