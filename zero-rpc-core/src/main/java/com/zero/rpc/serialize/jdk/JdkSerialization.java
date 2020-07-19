package com.zero.rpc.serialize.jdk;

import com.zero.rpc.exception.serialize.SerializeException;
import com.zero.rpc.serialize.Serialization;

import java.io.*;

/**
 * @author zero
 */
public class JdkSerialization implements Serialization {

    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

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

    public void write(OutputStream outputStream, Object data) throws IOException {
        objectOutputStream = new ObjectOutputStream(outputStream);
        {
            objectOutputStream.writeObject(data);
            objectOutputStream.flush();
        }
    }

    public <T> T read(InputStream inputStream, Class<T> clazz) throws IOException, ClassNotFoundException {
        objectInputStream = new ObjectInputStream(inputStream);
        {
            Object returnObject = objectInputStream.readObject();
            return clazz.cast(returnObject);
        }
    }

    public void close() {
        if (objectInputStream != null) {
            try {
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (objectOutputStream != null) {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
