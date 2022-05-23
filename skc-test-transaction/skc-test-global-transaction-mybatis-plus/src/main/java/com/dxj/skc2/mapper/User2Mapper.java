package com.dxj.skc2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dxj.skc2.entity.User2;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author sinkiang
 * @date 2022/4/8 10:06
 */
@Mapper
public interface User2Mapper extends BaseMapper<User2> {
    /**
     * 111
     * @return
     */
    int truncated();
}