package com.zero.rpc.transport.serialize;


import com.zero.rpc.transport.entity.RpcRequest;
import com.zero.rpc.transport.serialize.jdk.JdkSerialization;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JdkSerializationTest {

    private RpcRequest rpcRequest;
    private JdkSerialization jdkMessageCoder = new JdkSerialization();

    @Before
    public void before() {
        rpcRequest = new RpcRequest();
        rpcRequest.setRequestId("111");
        rpcRequest.setInterfaceName("com.zero.interface");
        rpcRequest.setMethodName("getRequestId");
        rpcRequest.setParameterTypes(new Class[]{String.class});
        rpcRequest.setParameterArgs(new Object[]{"zero"});

    }

    @Test
    public void test() throws Exception {
        System.out.println("rpcRequest = " + rpcRequest);
        byte[] serialize = jdkMessageCoder.serialize(rpcRequest);
        System.out.println("serialize = " + serialize.length);
//        System.out.println("serialize = " + Arrays.toString(serialize));
        Object desRpcRequest = jdkMessageCoder.deserialize(serialize, RpcRequest.class);
        System.out.println("desRpcRequest = " + desRpcRequest);
        assertEquals(rpcRequest, desRpcRequest);
    }
}
