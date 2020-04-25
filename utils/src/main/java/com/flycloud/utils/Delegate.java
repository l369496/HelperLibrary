package com.flycloud.utils;

import java.net.InetAddress;

public interface Delegate {
    interface Action {
        void invoke();
    }
    interface Action1<T> {
        InetAddress invoke(T t);
    }
    interface Action2<T1, T2> {
        void invoke(T1 t1, T2 t2);
    }
    interface Function<TResult>{
        TResult invoke();
    }
    interface Function1<T, TResult>{
        TResult invoke(T t);
    }
    interface Function2<T1, T2, TResult>{
        TResult invoke(T1 t1, T2 t2);
    }
}
