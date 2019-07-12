package com.example.demo.constant.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;

public interface IZkClientService {
    ZkClient getZkClient();

    String createNode(String var1, Object var2, CreateMode var3);

    void nodeWatcher(long var1, Watcher var3);

    void deleteNode(String var1);

    /** @deprecated */
    @Deprecated
    IZKLock createZKlock(String var1);

}
