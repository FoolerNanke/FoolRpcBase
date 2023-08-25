package com.scj.foolRpcBase.entity;

import java.io.Serializable;

/**
 * @author suchangjie.NANKE
 * @Title: FoolResponse
 * @date 2023/8/19 21:34
 * @description 请求回复
 */
public class FoolResponse implements Serializable {

    // 回复对象全类名
    private String fullClassName;

    // 响应结果列表
    private Object data;

    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
