package com.zero.rpc.transport.socket;

import com.zero.rpc.entity.RpcClientConfig;
import com.zero.rpc.entity.RpcRequest;
import com.zero.rpc.entity.RpcResponse;
import com.zero.rpc.serialize.Serialization;
import com.zero.rpc.transport.RpcClient;
import com.zero.rpc.util.Assert;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient implements RpcClient {

    private RpcClientConfig rpcClientConfig;
    private Socket socket;
    private Serialization serialization;

    public SocketClient(RpcClientConfig rpcClientConfig) {
        this.rpcClientConfig = rpcClientConfig;
    }

    @Override
    public void init(RpcClientConfig rpcClientConfig) {
        Assert.notNull(rpcClientConfig, "client config is miss. check...");
        InetSocketAddress inetSocketAddress = new InetSocketAddress(rpcClientConfig.getAddress(), rpcClientConfig.getPort());
        this.socket = new Socket();
        try {
            socket.connect(inetSocketAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.serialization = rpcClientConfig.getSerializer().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RpcResponse sendRequest(RpcRequest rpcRequest) {

//        try {
//            write(socket.getOutputStream(), rpcRequest);
//            RpcResponse response = read(socket.getInputStream());
//            return response;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
        InetSocketAddress inetSocketAddress = new InetSocketAddress(rpcClientConfig.getAddress(), rpcClientConfig.getPort());
        try (Socket socket = new Socket()) {
            socket.connect(inetSocketAddress);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            // 通过输出流发送数据到服务端
            objectOutputStream.writeObject(rpcRequest);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            //从输入流中读取出 RpcResponse
            return (RpcResponse) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private RpcResponse read(InputStream inputStream) {
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            RpcResponse deserialize = serialization.deserialize(buffer.toByteArray(), RpcResponse.class);
            System.out.println("deserialize = " + deserialize);
            return deserialize;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void write(OutputStream outputStream, RpcRequest rpcRequest) {
        byte[] serialize = serialization.serialize(rpcRequest);
        try {
            outputStream.write(serialize);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
