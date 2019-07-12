package com.example.demo.constant.zkclient;

public interface IConfigValueChange<T> {
    void onChange(String var1, T var2, T var3);
}

