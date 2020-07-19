package com.zero.rpc.entity;

import com.zero.rpc.invoker.Invoker;
import com.zero.rpc.serialize.Serialization;
import com.zero.rpc.transport.RpcServer;

/**
 * @author zero
 */
public class RpcServerConfig {

    private Class<? extends RpcServer> server;
    private Class<? extends Serialization> serializer;
    private Class<? extends Invoker> invoker;
    private String version;
    private long timeout;
    private String address;
    private int port;
    private String accessToken;
    private int corePoolSize;
    private int maxPoolSize;

    public Class<? extends RpcServer> getServer() {
        return server;
    }

    public void setServer(Class<? extends RpcServer> server) {
        this.server = server;
    }

    public Class<? extends Serialization> getSerializer() {
        return serializer;
    }

    public void setSerializer(Class<? extends Serialization> serializer) {
        this.serializer = serializer;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public Class<? extends Invoker> getInvoker() {
        return invoker;
    }

    public void setInvoker(Class<? extends Invoker> invoker) {
        this.invoker = invoker;
    }

    @Override
    public String toString() {
        return "RpcServerConfig{" +
                "server=" + server +
                ", serializer=" + serializer +
                ", invoker=" + invoker +
                ", version='" + version + '\'' +
                ", timeout=" + timeout +
                ", address='" + address + '\'' +
                ", port='" + port + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", corePoolSize=" + corePoolSize +
                ", maxPoolSize=" + maxPoolSize +
                '}';
    }
}
