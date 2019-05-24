package com.example.demo.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}
