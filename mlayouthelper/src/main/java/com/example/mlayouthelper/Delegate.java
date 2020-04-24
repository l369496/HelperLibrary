package com.example.mlayouthelper;

public interface Delegate {
    interface Action {
        void invoke();
    }
    interface Action1<T> {
        void invoke(T t);
    }
    interface Action2<T1, T2> {
        void invoke(T1 t1, T2 t2);
    }
}
