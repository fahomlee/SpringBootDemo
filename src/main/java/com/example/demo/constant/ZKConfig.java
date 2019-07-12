package com.example.demo.constant;


import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import com.example.demo.constant.zkclient.IZkClientService;
import com.example.demo.constant.zkclient.IZooKeeperConfig;
import com.example.demo.constant.zkclient.ZkClientServiceImp;
import com.example.demo.constant.zkclient.ZooKeeperConfigImp;

/**
 * 动态获取zookeeper中配置的参数
 * @author fahomlee
 *
 */
@Component
public class ZKConfig {


    private IZkClientService zkClientService = new ZkClientServiceImp("");// 127.0.0.1:2181
    private IZooKeeperConfig zooKeeperConfig;

    @PostConstruct
    public void init() {
        // 初始化zookeeper中配置的参数信息到ZookeeperConstant中  
        //路径 AppConfig/XXX/springbootdemo/callbackUrl
        zooKeeperConfig = new ZooKeeperConfigImp(zkClientService, "/AppConfig/XXX", "springbootdemo");

        ZookeeperConstant.CALLBACK_URL.set(zooKeeperConfig.get("callbackUrl"));
        zooKeeperConfig.dataChanges("callbackUrl", ZookeeperConstant.CALLBACK_URL, "");

        ZookeeperConstant.AUDIT_VALID_DAY.set(Integer.valueOf(zooKeeperConfig.get("auditValidDay")));
        zooKeeperConfig.dataChanges("auditValidDay", ZookeeperConstant.AUDIT_VALID_DAY, 0);
    }

}
