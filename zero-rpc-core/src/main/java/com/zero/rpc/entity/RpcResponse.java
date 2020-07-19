package com.zero.rpc.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * @author zero
 */
public class RpcResponse implements Serializable {

    private static final long serialVersionUID = 7639104921558613513L;
    private String requestId;
    private String responseId;
    private Object returnValue;
    private String status;
    private Throwable throwable;

    public RpcResponse() {
        this.responseId = UUID.randomUUID().toString();
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RpcResponse that = (RpcResponse) o;
        return Objects.equals(requestId, that.requestId) &&
                Objects.equals(responseId, that.responseId) &&
                Objects.equals(returnValue, that.returnValue) &&
                Objects.equals(status, that.status) &&
                Objects.equals(throwable, that.throwable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, responseId, returnValue, status, throwable);
    }

    @Override
    public String toString() {
        return "RpcResponse{" +
                "requestId='" + requestId + '\'' +
                ", responseId='" + responseId + '\'' +
                ", returnValue=" + returnValue +
                ", status='" + status + '\'' +
                ", throwable=" + throwable +
                '}';
    }
}
