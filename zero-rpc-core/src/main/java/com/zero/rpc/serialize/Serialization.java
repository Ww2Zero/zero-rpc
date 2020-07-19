package com.zero.rpc.serialize;

/**
 * @author zero
 */
public interface Serialization {

    /**
     * 将对象进行序列化为byte数组，以便传输
     *
     * @param object 需要传输的对象
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 将byte数组进行反序列化为指定类型的对象
     *
     * @param data  byte数组
     * @param clazz 指定类型
     * @param <T>   反序列化生成的对象
     * @return
     */
    <T> T deserialize(byte[] data, Class<T> clazz);
}
