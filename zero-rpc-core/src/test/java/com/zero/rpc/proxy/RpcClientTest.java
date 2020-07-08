package com.zero.rpc.proxy;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RpcClientTest {

    @Test
    public void testClient() {
        RpcProxy rpcProxy = new RpcProxy();
        Hello hello = rpcProxy.create(Hello.class);
        String zero = hello.sayHi("zero");
        System.out.println("zero = " + zero);
        assertEquals(zero, "hi~zero");
    }
}
