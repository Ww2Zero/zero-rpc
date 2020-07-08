package com.zero.rpc.proxy;

import com.zero.rpc.transport.remote.Server;
import org.junit.Test;

public class RpcServerTest {


    @Test
    public void testServer() {
        Person person = new Person();
        Server server = new Server("127.0.0.1", 23456);
        server.publishService(person, Hello.class);
        server.start();
    }
}
