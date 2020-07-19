package com.zero.rpc.transport.socket;

import com.zero.rpc.entity.RpcRequest;
import com.zero.rpc.entity.RpcResponse;
import com.zero.rpc.entity.RpcServerConfig;
import com.zero.rpc.invoker.BaseInvoker;
import com.zero.rpc.invoker.Invoker;
import com.zero.rpc.register.BaseRegister;
import com.zero.rpc.register.Register;
import com.zero.rpc.serialize.Serialization;
import com.zero.rpc.transport.RpcServer;
import com.zero.rpc.util.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zero
 */
public class SocketServer implements RpcServer {

    private RpcServerConfig rpcServerConfig;
    private Register register;
    private Serialization serialization;
    private Invoker invoker;
    private ServerSocket serverSocket;

    public SocketServer(RpcServerConfig rpcServerConfig) {
        this.rpcServerConfig = rpcServerConfig;
    }

    @Override
    public void init(RpcServerConfig rpcServerConfig) {
        Assert.notNull(rpcServerConfig, "rpc server config is null");
        register = new BaseRegister();
        try {
            serverSocket = new ServerSocket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(rpcServerConfig.getAddress(), rpcServerConfig.getPort());
            serverSocket.bind(inetSocketAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            serialization = rpcServerConfig.getSerializer().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        invoker = new BaseInvoker(register);
    }

    @Override
    public <T> void publishService(T service, Class<T> serviceClass) {
        register.registerServer(service, serviceClass);
    }

    @Override
    public void start() {
        try {
            Socket socket;
            while ((socket = serverSocket.accept()) != null) {
                RpcRequest rpcRequest = read(socket.getInputStream());
                RpcResponse rpcResponse = invoker.invoke(rpcRequest);
                write(socket.getOutputStream(), rpcResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(OutputStream outputStream, RpcResponse rpcResponse) {
        byte[] serialize = serialization.serialize(rpcResponse);
        try {
            outputStream.write(serialize);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private RpcRequest read(InputStream inputStream) {
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            RpcRequest deserialize = serialization.deserialize(buffer.toByteArray(), RpcRequest.class);
            System.out.println("deserialize = " + deserialize);
            return deserialize;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void stop() {

    }
}
