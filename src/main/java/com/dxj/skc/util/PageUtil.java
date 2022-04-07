package com.dxj.skc.util;

import com.dxj.skc.domain.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author: sinkiang
 * @date: 2020/4/13 9:45
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
        int pageIndex = page.getPageIndex();
        int pageSize = page.getPageSize();
        String sort = page.getSort();
        String order = page.getOrder();

        if (pageIndex < 1) {
            pageIndex = 1;
        }
        if (pageSize < 1) {
            pageSize = 10;
        }
        if (StringUtils.isNotBlank(sort)) {
            Sort.Direction d;
            if (StringUtils.isBlank(order)) {
                d = Sort.Direction.DESC;
            } else {
                d = Sort.Direction.valueOf(order.toUpperCase());
            }
            Sort s = Sort.by(d, sort);
            pageable = PageRequest.of(pageIndex - 1, pageSize, s);
        } else {
            pageable = PageRequest.of(pageIndex - 1, pageSize);
        }
        return pageable;
    }
}
