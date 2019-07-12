
package com.example.demo.constant.zkclient;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
@SuppressWarnings({"rawtypes","unchecked"})
public class ZooKeeperConfigImp implements IZooKeeperConfig {
    static Charset CharsetUTF8 = Charset.forName("utf-8");
    static Log log = LogFactory.getLog(ZooKeeperConfigImp.class);
    private IZkClientService zooKeeper;
    private String rootPath;
    private String appName;
    private String commonName = "Common";

    /**
     * 构造函数
     * 
     * @param zkClientService
     * @param path 路径
     * @param appName 应用名称
     */
    public ZooKeeperConfigImp(IZkClientService zkClientService, String path, String appName) {
        this.zooKeeper = zkClientService;
        this.rootPath = path;
        this.appName = appName;
    }

    private ZkClient getZkClient() {
        ZkClient client = this.zooKeeper.getZkClient();
        return client;
    }

    private String getPath(String key, String appName) {
        if (key != null) {
            if ("/".equals(key)) {
                return this.rootPath + "/" + appName;
            }

            if (key.startsWith("/")) {
                key = key.substring(1);
            }

            if (key.endsWith("/")) {
                key = key.substring(0, key.length() - 1);
            }
        }

        String path = null;
        if ("/".equals(this.rootPath)) {
            path = this.rootPath + appName + "/" + key;
        } else {
            path = this.rootPath + "/" + appName + "/" + key;
        }

        return path;
    }

    public String get(String key) {
        ZkClient client = this.getZkClient();
        String path = this.getPath(key, this.appName);
        byte[] b = null;
        if (client.exists(path)) {
            b = (byte[]) client.readData(path);
            if (b == null) {
                path = this.getPath(key, this.commonName);
                log.debug("get common path->" + path);
                b = (byte[]) client.readData(path);
            }
        } else {
            path = this.getPath(key, this.commonName);
            log.debug("get common path->" + path);
            b = (byte[]) client.readData(path);
        }

        return b != null ? new String(b, CharsetUTF8) : null;
    }

    public boolean exist(String key) {
        ZkClient client = this.getZkClient();
        String path = this.getPath(key, this.appName);
        boolean f = client.exists(path);
        return f;
    }

    public void set(String key, String data) {
        ZkClient client = this.getZkClient();
        String path = this.getPath(key, this.appName);
        boolean flag = client.exists(path);
        if (flag) {
            client.writeData(path, data.getBytes(CharsetUTF8));
        } else {
            client.createPersistent(path, true);
            client.writeData(path, data.getBytes(CharsetUTF8));
        }

    }

    public boolean delete(String key) {
        boolean flag = false;
        ZkClient client = this.getZkClient();
        String path = this.getPath(key, this.appName);
        List<String> nodes = client.getChildren(path);
        if ((null == nodes || nodes.size() <= 0) && client.exists(path)) {
            flag = client.delete(path);
        }

        return flag;
    }

    public boolean deleteCascade(String key) {
        boolean flag = false;
        ZkClient client = this.getZkClient();
        List<String> nodes = client.getChildren(key);
        if (null != nodes && nodes.size() > 0) {
            Iterator var5 = nodes.iterator();
            while (var5.hasNext()) {
                String nodeName = (String) var5.next();
                if ("/".equals(key)) {
                    this.deleteCascade(key + nodeName);
                } else {
                    this.deleteCascade(key + "/" + nodeName);
                }
            }
        }

        if (client.exists(key)) {
            flag = client.delete(key);
        }

        return flag;
    }

    public void createDir(String key) {
        ZkClient client = this.getZkClient();
        String path = this.getPath(key, this.appName);
        boolean f = client.exists(path);
        if (!f) {
            client.createPersistent(path, true);
        }

    }

    public List<String> listAllKeys() {
        ZkClient client = this.getZkClient();
        List<String> nodeList = new ArrayList();
        this.getChildren(client, this.rootPath, nodeList);
        return nodeList;
    }

    public IZooKeeperConfig openDir(String key) {
        String path = this.getPath(key, this.appName);
        IZooKeeperConfig config = new ZooKeeperConfigImp(this.zooKeeper, path, "");
        return config;
    }

    public boolean isRoot() {
        return "/".equals(this.rootPath);
    }

    private void getChildren(ZkClient client, String path, List<String> nodeList) {
        List<String> list = client.getChildren(path);
        if (list != null && !list.isEmpty()) {
            if (nodeList == null) {
                nodeList = new ArrayList();
            }

            Iterator var5 = list.iterator();

            while (var5.hasNext()) {
                String s = (String) var5.next();
                String pp = "";
                if ("/".equals(path)) {
                    pp = path + s;
                } else {
                    pp = path + "/" + s;
                }

                ((List) nodeList).add(pp);
                if (pp.startsWith("/")) {
                    this.getChildren(client, pp, (List) nodeList);
                } else {
                    this.getChildren(client, "/" + pp, (List) nodeList);
                }
            }
        }

    }

    public <T> void dataChanges(String key, IConfigValueChange<T> field, T defaultValue) {
        ZkClient client = this.getZkClient();
        String path = this.getPath(key, this.appName);
        log.debug("dataChanges path -> " + path);
        if (!client.exists(path)) {
            path = this.getCommonPath(key);
            log.debug("dataChanges common path -> " + path);
            if (!client.exists(path)) {
                log.debug("dataChanges not exist path -> " + key);
                return;
            }
        }

        client.subscribeDataChanges(path, new ZooKeeperConfigImp.IZkDataListenerEx(key, defaultValue, field));
    }

    public void dataChanges(String key, IZkChildListener field) {
        ZkClient client = this.getZkClient();
        String path = this.getPath(key, this.appName);
        log.debug("dataChanges path -> " + path);
        if (!client.exists(path)) {
            path = this.getCommonPath(key);
            log.debug("dataChanges common path -> " + path);
            if (!client.exists(path)) {
                log.debug("dataChanges not exist path -> " + key);
                return;
            }
        }

        client.subscribeChildChanges(path, field);
    }

    public <T> void dataChanges(String key, final AtomicReference<T> field, T defaultValue) {
        this.dataChanges(key, new IConfigValueChange<T>() {
            public void onChange(String key, T oldval, T newval) {
                field.set(newval);
            }
        }, defaultValue);
    }

    public String getPath(String key) {
        return this.getPath(key, this.appName);
    }

    public String getCommonPath(String key) {
        return this.getPath(key, this.commonName);
    }

    public <T> void dataChanges(String key, IZkDataListener listener) {
        ZkClient client = this.getZkClient();
        String path = this.getPath(key, this.appName);
        if (!client.exists(path)) {
            path = this.getCommonPath(key);
        }

        client.subscribeDataChanges(path, listener);
    }

    private static class IZkDataListenerEx<T> implements IZkDataListener {
        T defaultValue;
        T lastValue;
        IConfigValueChange<T> f;
        String key;

        IZkDataListenerEx(String key, T defaultValue, IConfigValueChange<T> f) {
            this.defaultValue = defaultValue;
            this.lastValue = defaultValue;
            this.key = key;
            this.f = f;
        }

        public void handleDataChange(String dataPath, Object data) throws Exception {
            if (data != null) {
                Object argObj = null;
                if (data instanceof String) {
                    argObj = data;
                } else if (data instanceof byte[]) {
                    argObj = new String((byte[]) ((byte[]) data), "utf-8");
                } else {
                    argObj = data;
                }

                ZooKeeperConfigImp.log.debug("handleDataChange arg0->" + data + " ,arg1-> " + argObj);
                T o = null;
                if (this.defaultValue instanceof Byte) {
                    o = (T) Byte.valueOf(String.valueOf(argObj));
                } else if (this.defaultValue instanceof Boolean) {
                    o = (T)Boolean.valueOf(String.valueOf(argObj));
                } else if (this.defaultValue instanceof Short) {
                    o = (T) Short.valueOf(String.valueOf(argObj));
                } else if (this.defaultValue instanceof Integer) {
                    o = (T) Integer.valueOf(String.valueOf(argObj));
                } else if (this.defaultValue instanceof Long) {
                    o = (T) Long.valueOf(String.valueOf(argObj));
                } else if (this.defaultValue instanceof Float) {
                    o = (T) Float.valueOf(String.valueOf(argObj));
                } else if (this.defaultValue instanceof Double) {
                    o = (T) Double.valueOf(String.valueOf(argObj));
                } else if (this.defaultValue instanceof BigInteger) {
                    o = (T) new BigInteger(String.valueOf(argObj));
                } else if (this.defaultValue instanceof BigDecimal) {
                    o = (T) new BigDecimal(String.valueOf(argObj));
                } else {
                    o = (T) argObj;
                }

                try {
                    this.f.onChange(this.key, this.lastValue, o);
                } catch (Throwable var6) {
                    ZooKeeperConfigImp.log.error("", var6);
                }

                this.lastValue = o;
            }
        }

        public void handleDataDeleted(String dataPath) throws Exception {
            this.f.onChange(this.key, this.lastValue, this.defaultValue);
            this.lastValue = this.defaultValue;
        }
    }
}
