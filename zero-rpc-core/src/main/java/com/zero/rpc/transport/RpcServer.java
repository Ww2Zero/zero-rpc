package com.zero.rpc.transport;

import com.zero.rpc.entity.RpcServerConfig;

public interface RpcServer {
    void init(RpcServerConfig rpcServerConfig);

    <T> void publishService(T service, Class<T> serviceClass);

    void start();

    void stop();
}
