package com.zero.rpc.invoker;

import com.zero.rpc.entity.RpcRequest;
import com.zero.rpc.entity.RpcResponse;

public interface Invoker {

    RpcResponse invoke(RpcRequest rpcRequest);
}
