package com.zero.rpc.entity;

import com.zero.rpc.serialize.Serialization;
import com.zero.rpc.transport.RpcClient;

import java.util.Objects;

public class RpcClientConfig {

    private Class<? extends RpcClient> client;
    private Class<? extends Serialization> serializer;
    private String version;
    private long timeout;
    private String address;
    private int port;
    private String accessToken;

    public Class<? extends RpcClient> getClient() {
        return client;
    }

    public void setClient(Class<? extends RpcClient> client) {
        this.client = client;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RpcClientConfig that = (RpcClientConfig) o;
        return timeout == that.timeout &&
                Objects.equals(client, that.client) &&
                Objects.equals(serializer, that.serializer) &&
                Objects.equals(version, that.version) &&
                Objects.equals(address, that.address) &&
                Objects.equals(port, that.port) &&
                Objects.equals(accessToken, that.accessToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, serializer, version, timeout, address, port, accessToken);
    }

    @Override
    public String toString() {
        return "RpcClientConfig{" +
                "client=" + client +
                ", serializer=" + serializer +
                ", version='" + version + '\'' +
                ", timeout=" + timeout +
                ", address='" + address + '\'' +
                ", port='" + port + '\'' +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }
}
