package com.scj.foolRpcBase.constant;

import com.scj.foolRpcBase.serialize.FoolSerialize;
import com.scj.foolRpcBase.serialize.HessianSerialize;
import io.netty.util.concurrent.Promise;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author suchangjie.NANKE
 * @Title: LocalCache
 * @date 2023/8/21 21:04
 * @description 本地缓存
 */
public class BaseLocalCache {

    /**
     * 序列化存储对象
     * key:SerializableType
     * value:FoolSerialize->Bean
     */
    private static final Map<Byte, FoolSerialize> SerializeMap = new ConcurrentHashMap<>();

    /**
     * 服务提供方存储对象
     * key:fullClassName
     * value:Bean
     */
    private static final Map<String, Object> ProviderMap = new ConcurrentHashMap<>();

    static {
        /*
         初始化 SerializeMap
         */
        SerializeMap.put(Constant.HESSIAN, new HessianSerialize());
    }

    /**
     * 获取序列化实例
     * @param type 序列化类型
     * @return 序列化实例
     */
    public static FoolSerialize getFoolSerialize(byte type){
        FoolSerialize serialize = SerializeMap.getOrDefault(type, null);
        if (serialize == null) return getFoolSerialize((byte) 0);
        return serialize;
    }

    /**
     * 新增响应实例
     * @param name 全类名
     * @param obj 对象
     */
    public static void put(String name, Object obj){
        ProviderMap.put(name, obj);
    }

    /**
     * 获取响应实例
     * @param name 全类名
     * @return Object->Bean
     */
    public static Object get(String name){
        return ProviderMap.get(name);
    }
}
