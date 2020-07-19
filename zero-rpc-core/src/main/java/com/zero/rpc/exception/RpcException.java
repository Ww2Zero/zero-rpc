package com.zero.rpc.exception;

/**
 * @author zero
 */
public class RpcException extends RuntimeException {
    private static final long serialVersionUID = -6455444840045173895L;

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public RpcException(String message) {
        super(message);
    }
}
