package com.zero.rpc.register;

import java.util.concurrent.ConcurrentHashMap;

public class BaseRegister implements Register {

    private final static ConcurrentHashMap<String, Object> serviceMap = new ConcurrentHashMap<>();

    @Override
    public <T> void registerServer(T service, Class<T> serviceClass) {
        serviceMap.put(serviceClass.getCanonicalName(), service);
    }

    @Override
    public Object getService(String interfaceName) {

        return serviceMap.get(interfaceName);

    }
}
