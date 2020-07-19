package com.zero.rpc.proxy;

import com.zero.rpc.entity.RpcServerConfig;
import com.zero.rpc.invoker.BaseInvoker;
import com.zero.rpc.serialize.jdk.JdkSerialization;
import com.zero.rpc.transport.RpcServer;
import com.zero.rpc.transport.socket.SocketServer;

public class RpcSocketServerTest {

    public static void main(String[] args) {
        Person person = new Person();
        RpcServerConfig rpcServerConfig = new RpcServerConfig();
        rpcServerConfig.setAddress("127.0.0.1");
        rpcServerConfig.setPort(23456);
        rpcServerConfig.setSerializer(JdkSerialization.class);
        rpcServerConfig.setInvoker(BaseInvoker.class);
        rpcServerConfig.setServer(SocketServer.class);

        RpcServer rpcServer = new SocketServer(rpcServerConfig);
        rpcServer.init(rpcServerConfig);
        rpcServer.publishService(person, Hello.class);
        rpcServer.start();
    }
}
