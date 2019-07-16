package com.example.demo.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
/**
 * 通用工具类
 *
 * @author lifahong
 * @date 2019/5/23
 */
public class CommonUtil {

    /**
     * list集合去重，保留原始排序
     */
    public static <T> List<T> rmDupAndKeepOrder(List<T> impList) {
        Set<T> set = new HashSet<T>();
        List<T> newList = new ArrayList<>();
        for (T element : impList) {
            if (set.add(element)) {
                newList.add(element);
            }
        }
        impList.clear();
        impList.addAll(newList);
        return impList;
    }
    
    public static void main(String[] args) {
        /*
         * JSONObject jsonObject = JSON.parseObject("{\"data\":{\"shebao_brief\":{\"姓名\":\"全小琴\"}}}"); JSONObject
         * dataObject = jsonObject.getJSONObject("data"); JSONObject shebao_brief =
         * dataObject.getJSONObject("shebao_brief"); //JSONPath解析中文key String h_name =
         * (String)JSONPath.read(dataObject.toJSONString(),"$.shebao_brief.\\姓\\名"); System.out.println(h_name);
         */
        Random random=new Random();
        System.out.println(random.nextInt(100)+1);//1-100随机数
    }
}
