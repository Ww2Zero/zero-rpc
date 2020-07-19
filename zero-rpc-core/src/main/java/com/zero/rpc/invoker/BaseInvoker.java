package com.zero.rpc.invoker;

import com.zero.rpc.config.RemoteConstants;
import com.zero.rpc.entity.RpcRequest;
import com.zero.rpc.entity.RpcResponse;
import com.zero.rpc.register.Register;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zero
 */
public class BaseInvoker implements Invoker {

    private Register register;

    public BaseInvoker(Register register) {
        this.register = register;
    }

    @Override
    public RpcResponse invoke(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setRequestId(rpcRequest.getRequestId());
        String interfaceName = rpcRequest.getInterfaceName();
        String methodName = rpcRequest.getMethodName();
        Class<?>[] parameterTypes = rpcRequest.getParameterTypes();
        Object[] parameterArgs = rpcRequest.getParameterArgs();
        Object service = register.getService(interfaceName);
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
        return rpcResponse;
    }
}
