package com.dxj.skc;

/**
 * @author sinkiang
 * @date 2022/6/21 8:37
 */

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 拆分结合工具类
 *
 * @author shiwen
 * @date 2020/12/27
 */
public class SplitListUtils {

    /**
     * 拆分集合
     *
     * @param <T>           泛型对象
     * @param resList       需要拆分的集合
     * @param subListLength 每个子集合的元素个数
     * @return 返回拆分后的各个集合组成的列表
     * 代码里面用到了guava和common的结合工具类
     **/
    public static <T> List<List<T>> split(List<T> resList, int subListLength) {
        if (CollectionUtils.isEmpty(resList) || subListLength <= 0) {
            return Lists.newArrayList();
        }
        List<List<T>> ret = Lists.newArrayList();
        int size = resList.size();
        if (size <= subListLength) {
            // 数据量不足 subListLength 指定的大小
            ret.add(resList);
        } else {
            int pre = size / subListLength;
            int last = size % subListLength;
            // 前面 pre 个集合，每个大小都是 subListLength 个元素
            for (int i = 0; i < pre; i++) {
                List<T> itemList = Lists.newArrayList();
                for (int j = 0; j < subListLength; j++) {
                    itemList.add(resList.get(i * subListLength + j));
                }
                ret.add(itemList);
            }
            // last的进行处理
            if (last > 0) {
                List<T> itemList = Lists.newArrayList();
                for (int i = 0; i < last; i++) {
                    itemList.add(resList.get(pre * subListLength + i));
                }
                ret.add(itemList);
            }
        }
        return ret;
    }

    // 运行代码
    public static void main(String[] args) {
        List<String> list = Lists.newArrayList();
        int size = 9999999;
        for (int i = 0; i < size; i++) {
            list.add("hello-" + i);
        }
        // 大集合里面包含多个小集合
        List<List<String>> temps = split(list, 1000);
        int j = 0;
        // 对大集合里面的每一个小集合进行操作
        for (List<String> obj : temps) {
            System.out.printf("row:%s -> size:%s,data:%s%n", ++j, obj.size(), obj);
        }
    }

}
