package com.zero.rpc.transport.serialize;

import com.zero.rpc.transport.entity.RpcRequest;
import com.zero.rpc.transport.entity.RpcResponse;


/**
 * @author zero
 */
public interface Serialization {


    byte[] serialize(RpcRequest rpcRequest);

    <T> T deserialize(byte[] data, Class<T> clazz);

    RpcResponse deserialize(byte[] data);
}
