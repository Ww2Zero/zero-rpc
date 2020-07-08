package com.zero.rpc.exception.remote;

public class RemoteException extends RuntimeException {


    private static final long serialVersionUID = 2081032983925036498L;

    public RemoteException(String msg) {
        super(msg);
    }
}
