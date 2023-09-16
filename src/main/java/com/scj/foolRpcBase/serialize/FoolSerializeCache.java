package com.scj.foolRpcBase.serialize;

import com.scj.foolRpcBase.constant.Constant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author suchangjie.NANKE
 * @Title: LocalCache
 * @date 2023/8/21 21:04
 * @description 本地缓存
 */
public class FoolSerializeCache {

    /**
     * 序列化存储对象
     * key:SerializableType
     * value:FoolSerialize->Bean
     */
    private static final Map<Byte, FoolSerialize> SerializeMap = new ConcurrentHashMap<>();

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
}
