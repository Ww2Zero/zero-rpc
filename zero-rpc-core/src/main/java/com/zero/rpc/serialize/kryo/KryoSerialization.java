package com.zero.rpc.serialize.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.zero.rpc.exception.serialize.SerializeException;
import com.zero.rpc.serialize.Serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author zero
 */
public class KryoSerialization implements Serialization {


    @Override
    public byte[] serialize(Object object) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             Output output = new Output(byteArrayOutputStream)) {
            Kryo kryo = new Kryo();
            kryo.writeObject(output, object);
            return output.toBytes();
        } catch (Exception e) {
            throw new SerializeException("序列化失败，序列化方法为：Kryo，序列化对象为：" + object);
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             Input input = new Input(byteArrayInputStream)) {
            Kryo kryo = new Kryo();
            Object o = kryo.readObject(input, clazz);
            return clazz.cast(o);
        } catch (Exception e) {
            throw new SerializeException("反序列化失败,序列化方法为：Kryo,目标对象为：" + clazz.getName());
        }
    }
}
