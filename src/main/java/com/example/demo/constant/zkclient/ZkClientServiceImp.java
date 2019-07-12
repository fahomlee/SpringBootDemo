package com.example.demo.constant.zkclient;


import java.io.Closeable;
import java.io.IOException;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;

public class ZkClientServiceImp implements IZkClientService, Closeable {
    static Log log = LogFactory.getLog(ZkClientServiceImp.class);
    private ZkClient client;

    public ZkClientServiceImp(String url) {
        log.debug("new ZkClientServiceImp");
        this.client = new ZkClient(url);
        this.client.setZkSerializer(new BytesPushThroughSerializer());
    }

    public ZkClient getZkClient() {
        return this.client;
    }

    public String createNode(String path, Object data, CreateMode createModel) {
        return this.client.create(path, data, createModel);
    }

    public void nodeWatcher(long maxMsToWaitUntilConnected, Watcher watcher) {
        this.client.connect(maxMsToWaitUntilConnected, watcher);
    }

    public void deleteNode(String path) {
        ZkClient client = this.getZkClient();
        client.delete(path);
    }

    public void close() throws IOException {
        if (this.client != null) {
            this.client.close();
        }

    }

    @Deprecated
    public IZKLock createZKlock(String name) {
        return ZKLock.createZkLock(this.client, name);
    }

}
