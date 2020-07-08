package com.zero.rpc.exception.serialize;

/**
 * @author zero
 */
public class SerializeException extends RuntimeException {
    private static final long serialVersionUID = 4448075833805565918L;

    public SerializeException(String msg) {
        super(msg);
    }
}
