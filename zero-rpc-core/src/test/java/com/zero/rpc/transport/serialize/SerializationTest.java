package com.zero.rpc.transport.serialize;

import com.zero.rpc.transport.entity.RpcRequest;
import com.zero.rpc.transport.serialize.jdk.JdkSerialization;
import com.zero.rpc.transport.serialize.kryo.KryoSerialization;
import org.junit.Before;
import org.junit.Test;

public class SerializationTest {

    private final static long TEST_TIME = 1000_000L;
    private RpcRequest rpcRequest;
    private JdkSerialization jdkSerialization = new JdkSerialization();
    private KryoSerialization kryoSerialization = new KryoSerialization();

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
    public void testSerialization() {
        long start0 = System.currentTimeMillis();
        testJDK(TEST_TIME);
        long end0 = System.currentTimeMillis();
        System.out.println("jdk  = " + (end0 - start0));

        long start1 = System.currentTimeMillis();
        testKryo(TEST_TIME);
        long end1 = System.currentTimeMillis();
        System.out.println("kryo  = " + (end1 - start1));

    }

    private void testJDK(long testTime) {
        for (long i = 0; i < testTime; i++) {
            byte[] serialize = jdkSerialization.serialize(rpcRequest);
            jdkSerialization.deserialize(serialize, RpcRequest.class);
        }
    }

    private void testKryo(long testTime) {
        for (long i = 0; i < testTime; i++) {
            byte[] serialize = kryoSerialization.serialize(rpcRequest);
            kryoSerialization.deserialize(serialize, RpcRequest.class);
        }
    }
}
