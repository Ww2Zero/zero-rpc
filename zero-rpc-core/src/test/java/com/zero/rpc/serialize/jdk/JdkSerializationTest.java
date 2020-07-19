package com.zero.rpc.serialize.jdk;


import com.zero.rpc.entity.RpcRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JdkSerializationTest {

    private RpcRequest rpcRequest;
    private JdkSerialization serialization = new JdkSerialization();

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
        byte[] serialize = serialization.serialize(rpcRequest);
        System.out.println("serialize = " + serialize.length);
//        System.out.println("serialize = " + Arrays.toString(serialize));
        Object desRpcRequest = serialization.deserialize(serialize, RpcRequest.class);
        System.out.println("desRpcRequest = " + desRpcRequest);
        assertEquals(rpcRequest, desRpcRequest);
    }
}
