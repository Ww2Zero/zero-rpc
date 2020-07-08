package com.zero.rpc.transport.remote;

import com.zero.rpc.exception.remote.RemoteException;
import com.zero.rpc.transport.entity.RpcRequest;
import com.zero.rpc.transport.entity.RpcResponse;
import com.zero.rpc.transport.serialize.jdk.JdkSerialization;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    private InetSocketAddress inetSocketAddress;

    public Client(String host, int port) {
        this.inetSocketAddress = new InetSocketAddress(host, port);
    }

    public RpcResponse remote(RpcRequest rpcRequest) {
        try (Socket socket = new Socket()) {
            socket.connect(inetSocketAddress);
            JdkSerialization jdkSerialization = new JdkSerialization();
            jdkSerialization.write(socket.getOutputStream(), rpcRequest);
            RpcResponse response = jdkSerialization.read(socket.getInputStream(), RpcResponse.class);
            jdkSerialization.close();
            return response;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RemoteException("客户发送数据异常");
        }
    }
}
