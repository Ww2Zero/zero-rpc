package com.zero.rpc.proxy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestProxy {

    @Test
    public void testJdkProxy() {
        JdkProxy jdkProxy = new JdkProxy(new Bus());
        Car instance = (Car) jdkProxy.getInstance();
        System.out.println("instance = " + instance);
        assertEquals(instance.running(), "bus is running");
        assertEquals(instance.stop("red"), "bus is stop because red");
    }

    @Test
    public void testCglibProxy() {
        CglibProxy cglibProxy = new CglibProxy(new Bus());
        Bus instance = (Bus) cglibProxy.getInstance();
        System.out.println("instance = " + instance);
        assertEquals(instance.running(), "bus is running");
        assertEquals(instance.stop("red"), "bus is stop because red");
    }


    interface Car {
        String running();

        String stop(String cause);
    }

    static class Bus implements Car {

        @Override
        public String running() {
            String res = "bus is running";
            System.out.println(res);
            return res;
        }

        @Override
        public String stop(String cause) {
            String res = "bus is stop because " + cause;
            System.out.println(res);
            return res;
        }
    }
}
