package com.zero.rpc.register;

/**
 * @author zero
 */
public interface Register {

    <T> void registerServer(T service, Class<T> serviceClass);

    Object getService(String interfaceName);
}
