package com.example.demo.constant.zkclient;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;

public interface IZooKeeperConfig {
    String get(String var1);

    boolean exist(String var1);

    void set(String var1, String var2);

    boolean delete(String var1);

    boolean deleteCascade(String var1);

    void createDir(String var1);

    List<String> listAllKeys();

    IZooKeeperConfig openDir(String var1);

    boolean isRoot();

    String getPath(String var1);

    String getCommonPath(String var1);

    <T> void dataChanges(String var1, AtomicReference<T> var2, T var3);

    <T> void dataChanges(String var1, IConfigValueChange<T> var2, T var3);

    <T> void dataChanges(String var1, IZkDataListener var2);

    void dataChanges(String var1, IZkChildListener var2);
}
