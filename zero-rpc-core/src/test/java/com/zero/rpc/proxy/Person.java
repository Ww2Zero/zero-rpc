package com.zero.rpc.proxy;

public class Person implements Hello {


    @Override
    public String sayHi(String name) {
        return "hi~" + name;
    }
}
