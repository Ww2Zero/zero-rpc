package com.zero.rpc.proxy;

import com.zero.rpc.transport.entity.RpcRequest;
import com.zero.rpc.transport.entity.RpcResponse;
import com.zero.rpc.transport.remote.Client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zero
 */
public class RpcProxy implements InvocationHandler {

    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        Class<?>[] parameterTypes = method.getParameterTypes();

        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setParameterArgs(args);
        // todo fix
        rpcRequest.setInterfaceName(className);
        rpcRequest.setMethodName(methodName);
        rpcRequest.setParameterTypes(parameterTypes);
        Client client = new Client("127.0.0.1", 23456);
        RpcResponse remote = client.remote(rpcRequest);
        return remote.getReturnValue();
    }
}
