package com.zero.rpc.transport;

import com.zero.rpc.entity.RpcClientConfig;
import com.zero.rpc.entity.RpcRequest;
import com.zero.rpc.entity.RpcResponse;

/**
 * @author zero
 */
public interface RpcClient {

    void init(RpcClientConfig rpcClientConfig);

    RpcResponse sendRequest(RpcRequest rpcRequest);
}
