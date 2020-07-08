package com.zero.rpc.transport.serialize.jdk;

import com.zero.rpc.exception.serialize.SerializeException;
import com.zero.rpc.transport.serialize.Serialization;

import java.io.*;

/**
 * @author zero
 */
public class JdkSerialization implements Serialization {


    @Override
    public byte[] serialize(Object object) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(object);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new SerializeException("序列化失败，序列化方法为：JDK，序列化对象为：" + object);
        }
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        try (ByteArrayInputStream bi = new ByteArrayInputStream(data);
             ObjectInputStream ois = new ObjectInputStream(bi)) {
            Object returnObject = ois.readObject();
            return clazz.cast(returnObject);
        } catch (IOException | ClassNotFoundException e) {
            throw new SerializeException("反序列化失败,序列化方法为：JDK,目标对象为：" + clazz.getName());
        }
    }
}
