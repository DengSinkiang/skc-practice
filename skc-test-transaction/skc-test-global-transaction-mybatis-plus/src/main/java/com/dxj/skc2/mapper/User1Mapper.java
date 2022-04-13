package com.dxj.skc2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dxj.skc2.domain.User1;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author sinkiang
 * @date 2022/4/8 10:06
 */
@Mapper
public interface User1Mapper extends BaseMapper<User1> {
    /**
     * 111
     * @return
     */
    int truncated();
}