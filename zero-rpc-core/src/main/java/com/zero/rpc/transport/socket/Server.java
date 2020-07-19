package com.zero.rpc.transport.socket;

import com.zero.rpc.config.RemoteConstants;
import com.zero.rpc.entity.RpcRequest;
import com.zero.rpc.entity.RpcResponse;
import com.zero.rpc.serialize.jdk.JdkSerialization;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private final static ConcurrentHashMap<String, Object> serviceMap = new ConcurrentHashMap<>();
    private final String host;
    private final int port;
    private JdkSerialization jdkSerialization;

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
        jdkSerialization = new JdkSerialization();
    }

    public <T> void publishService(T service, Class<T> serviceClass) {
        serviceMap.put(serviceClass.getCanonicalName(), service);
    }

    public void start() {
        try (ServerSocket server = new ServerSocket()) {
            server.bind(new InetSocketAddress(host, port));
            Socket socket;
            while ((socket = server.accept()) != null) {
                RpcRequest rpcRequest = jdkSerialization.read(socket.getInputStream(), RpcRequest.class);
                RpcResponse rpcResponse = rpcHandler(rpcRequest);
                jdkSerialization.write(socket.getOutputStream(), rpcResponse);
            }
            jdkSerialization.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private RpcResponse rpcHandler(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setRequestId(rpcRequest.getRequestId());
        String interfaceName = rpcRequest.getInterfaceName();
        String methodName = rpcRequest.getMethodName();
        Class<?>[] parameterTypes = rpcRequest.getParameterTypes();
        Object[] parameterArgs = rpcRequest.getParameterArgs();

        if (serviceMap.containsKey(interfaceName)) {
            Object service = serviceMap.get(interfaceName);
            if (service != null) {
                try {
                    Method method = service.getClass().getMethod(methodName, parameterTypes);
                    Object invoke = method.invoke(service, parameterArgs);
                    rpcResponse.setReturnValue(invoke);
                    rpcResponse.setStatus(RemoteConstants.status.SUCCESS);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    rpcResponse.setThrowable(e);
                    rpcResponse.setStatus(RemoteConstants.status.FAIL);
                }
            } else {
                rpcResponse.setStatus(RemoteConstants.status.METHOD_NOT_FOUND);
            }
        }
        return rpcResponse;
    }
}
