package com.zero.rpc.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * @author zero
 */
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = 3829252771168681281L;
    private String requestId;
    private String interfaceName;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] parameterArgs;

    public RpcRequest() {
        this.requestId = UUID.randomUUID().toString();
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameterArgs() {
        return parameterArgs;
    }

    public void setParameterArgs(Object[] parameterArgs) {
        this.parameterArgs = parameterArgs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RpcRequest that = (RpcRequest) o;
        return Objects.equals(requestId, that.requestId) &&
                Objects.equals(interfaceName, that.interfaceName) &&
                Objects.equals(methodName, that.methodName) &&
                Arrays.equals(parameterTypes, that.parameterTypes) &&
                Arrays.equals(parameterArgs, that.parameterArgs);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(requestId, interfaceName, methodName);
        result = 31 * result + Arrays.hashCode(parameterTypes);
        result = 31 * result + Arrays.hashCode(parameterArgs);
        return result;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "requestId='" + requestId + '\'' +
                ", interfaceName='" + interfaceName + '\'' +
                ", methodName=" + methodName +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                ", parameterArgs=" + Arrays.toString(parameterArgs) +
                '}';
    }
}
