package com.zero.rpc.proxy;

import com.zero.rpc.entity.RpcClientConfig;
import com.zero.rpc.serialize.jdk.JdkSerialization;
import com.zero.rpc.transport.RpcClient;
import com.zero.rpc.transport.socket.SocketClient;

public class RpcSocketClientTest {

    public static void main(String[] args) {
        RpcClientConfig rpcClientConfig = new RpcClientConfig();
        rpcClientConfig.setClient(SocketClient.class);
        rpcClientConfig.setAddress("127.0.0.1");
        rpcClientConfig.setPort(23456);
        rpcClientConfig.setSerializer(JdkSerialization.class);
        RpcClient rpcClient = new SocketClient(rpcClientConfig);
        rpcClient.init(rpcClientConfig);
        RpcProxy rpcProxy = new RpcProxy(rpcClient);
        Hello hello = rpcProxy.create(Hello.class);
        String zzz = hello.sayHi("zzz");
        System.out.println("zzz = " + zzz);
    }
}
