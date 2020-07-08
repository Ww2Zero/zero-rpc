package com.zero.rpc.transport.serialize.jdk;

import com.zero.rpc.transport.entity.RpcRequest;
import com.zero.rpc.transport.entity.RpcResponse;
import com.zero.rpc.transport.serialize.Serialization;

import java.io.*;

/**
 * @author zero
 */
public class JdkSerialization implements Serialization {


    @Override
    public byte[] serialize(RpcRequest rpcRequest) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(rpcRequest);
            return bos.toByteArray();
        } catch (IOException e) {
            System.out.println("序列化失败 Exception:" + e.toString());
            return null;
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException ex) {
                System.out.println("io could not close:" + ex.toString());
            }
        }
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        ByteArrayInputStream bi = null;
        ObjectInputStream ois = null;
        try {
            bi = new ByteArrayInputStream(data);
            ois = new ObjectInputStream(bi);
            Object returnObject = ois.readObject();
            return (T) returnObject;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("bytes Could not deserialize:" + e.toString());
            return null;
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (bi != null) {
                    bi.close();
                }
            } catch (IOException ex) {
                System.out.println("LogManage Could not serialize:" + ex.toString());
            }
        }
    }

    @Override
    public RpcResponse deserialize(byte[] bytes) {
        return deserialize(bytes, RpcResponse.class);
    }
}
