package com.zero.rpc.util;

import com.zero.rpc.exception.RpcException;

/**
 * @author zero
 */
public final class Assert {
    private Assert() {
    }


    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new RpcException(message);
        }
    }
}
