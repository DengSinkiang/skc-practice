package com.dxj.skc.util;

import cn.hutool.core.util.StrUtil;
import com.dxj.skc.domain.vo.PageVo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @Description:
 * @Author: Sinkiang
 * @Date: 2020/3/27 16:01
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
public class PageUtil {

    /**
     * JPA分页封装
     *
     * @param page
     * @return
     */
    public static Pageable initPage(PageVo page) {

        Pageable pageable;
        int pageNumber = page.getPageIndex();
        int pageSize = page.getPageSize();
        String sort = page.getSort();
        String order = page.getOrder();

        if (pageNumber < 1) {
            pageNumber = 1;
        }
        if (pageSize < 1) {
            pageSize = 10;
        }
        if (StrUtil.isNotBlank(sort)) {
            Sort.Direction d;
            if (StrUtil.isBlank(order)) {
                d = Sort.Direction.DESC;
            } else {
                d = Sort.Direction.valueOf(order.toUpperCase());
            }
            Sort s = Sort.by(d, sort);
            pageable = PageRequest.of(pageNumber - 1, pageSize, s);
        } else {
            pageable = PageRequest.of(pageNumber - 1, pageSize);
        }
        return pageable;
    }
}
