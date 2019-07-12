package com.example.demo.constant.zkclient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.example.demo.util.SHA1Util;

@SuppressWarnings({"rawtypes","unchecked"})
public final class ZKLock {
    public static final String LockPrex = "XXX_ZKLock";
    private static Hashtable<String, Object> lockobj = new Hashtable<String, Object>();
    private static Log log = LogFactory.getLog(ZKLock.class);

    public ZKLock() {
    }
    @Deprecated
    public static IZKLock createZkLock(ZkClient c, String name) {
        return new ZKLock.ZKLockImp(c, name);
    }
    
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
        ZkClient c = new ZkClient("127.0.0.1:2181");
        IZKLock i1 = createZkLock(c, "abc");
        IZKLock i2 = createZkLock(c, "abc");
        log.debug("log..........");
        (new Thread(() -> {
            log.debug("t1 -> start....");
            i1.lock();
            log.debug("t1 -> lock....");

            try {
                Thread.sleep(1000L);
            } catch (Exception var2) {
            }

            log.debug("t1 -> unlock....");
            i1.unlock();
        })).start();
        (new Thread(() -> {
            log.debug("t2 -> start....");
            i2.lock();
            log.debug("t2 -> lock....");

            try {
                Thread.sleep(1000L);
            } catch (Exception var2) {
            }

            log.debug("t2 -> unlock....");
            i2.unlock();
        })).start();
        log.debug("unlog..........");
        Thread.sleep(100000L);
    }

    @SuppressWarnings("deprecation")
    private static class ZKLockImp implements IZKLock {
        private ZkClient client;
        private String name;
        private Object lock = null;
        private String nodeName;
        private long index = -1L;

        public ZKLockImp(ZkClient client, String lname) {
            this.client = client;
            this.name = SHA1Util.getSHA1String(lname);

            try {
                client.createPersistent("/XXX_ZKLock");
            } catch (Exception var6) {
            }

            synchronized(ZKLock.lockobj) {
                if (ZKLock.lockobj.contains(this.name)) {
                    this.lock = ZKLock.lockobj.get(this.name);
                } else {
                    this.lock = new Object();
                    ZKLock.lockobj.put(this.name, this.lock);
                }
            }

            client.subscribeChildChanges("/XXX_ZKLock", (parentPath, currentChilds) -> {
                
                List<Long> ll = new ArrayList();
                Iterator var4 = currentChilds.iterator();

                while(var4.hasNext()) {
                    String c = (String)var4.next();
                    if (c.startsWith(this.name)) {
                        ll.add(Long.parseLong(c.substring(40)));
                    }
                }

                Collections.sort(ll);
                if (ll.size() > 0 && (Long)ll.get(0) == this.index) {
                    synchronized(this.lock) {
                        try {
                            this.lock.notify();
                        } catch (Exception var7) {
                        }
                    }
                }

            });
        }

        public synchronized void lock() {
            if (StringUtils.isNoneEmpty(new CharSequence[]{this.nodeName})) {
                throw new RuntimeException("lock has locked");
            } else {
                String ret = this.client.createEphemeralSequential("/XXX_ZKLock/" + this.name, (Object)null);
                this.nodeName = ret;
                int ss = ret.lastIndexOf("/");
                String sss = ret.substring(ss + 1 + 40);
                this.index = Long.parseLong(sss);

                while(true) {
                    try {
                        List<String> list = this.client.getChildren("/XXX_ZKLock");
                        List<Long> l = this.sortL(list);
                        if ((Long)l.get(0) == this.index) {
                            return;
                        }

                        synchronized(this.lock) {
                            this.lock.wait(3000L);
                        }
                    } catch (InterruptedException var9) {
                    }
                }
            }
        }

        private List<Long> sortL(List<String> list) {
            List<Long> ll = new ArrayList(list.size());
            Iterator var3 = list.iterator();

            while(var3.hasNext()) {
                String l = (String)var3.next();
                if (l.startsWith(this.name)) {
                    ll.add(Long.parseLong(l.substring(40)));
                }
            }

            Collections.sort(ll);
            return ll;
        }

        public synchronized void unlock() {
            if (this.nodeName != null) {
                this.client.delete(this.nodeName);
                this.nodeName = null;
            }
        }
    }
}
