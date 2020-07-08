package com.zero.rpc.proxy;

import net.sf.cglib.core.NamingPolicy;
import net.sf.cglib.core.Predicate;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class CglibProxy implements MethodInterceptor {

    private Object target;

    public CglibProxy(Object target) {
        this.target = target;
    }

    public Object getInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setNamingPolicy(ZeroProxy.INSTANCE);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
//        System.out.println("target = " + target);
        System.out.println("method = " + method);
        System.out.println("args = " + Arrays.toString(args));
        System.out.println("methodProxy = " + methodProxy);
        return methodProxy.invokeSuper(target, args);
    }

    static class ZeroProxy implements NamingPolicy {

        public static final ZeroProxy INSTANCE = new ZeroProxy();
        private static final boolean STRESS_HASH_CODE = Boolean.getBoolean("net.sf.cglib.test.stressHashCodes");

        public ZeroProxy() {
        }

        @Override
        public String getClassName(String prefix, String source, Object key, Predicate names) {
            if (prefix == null) {
                prefix = "net.sf.cglib.empty.Object";
            } else if (prefix.startsWith("java")) {
                prefix = "$" + prefix;
            }

            String base = prefix + "$$" + source.substring(source.lastIndexOf(46) + 1) + this.getTag() + "$$" + Integer.toHexString(STRESS_HASH_CODE ? 0 : key.hashCode());
            String attempt = base;
            int var7 = 2;
            do {
                attempt = base + "_" + var7++;
            } while (names.evaluate(attempt));
            return attempt;
        }

        private Object getTag() {
            return "-zeroCglib-";
        }

        public int hashCode() {
            return this.getTag().hashCode();
        }

        public boolean equals(Object o) {
            return o instanceof ZeroProxy && ((ZeroProxy) o).getTag().equals(this.getTag());
        }
    }
}
