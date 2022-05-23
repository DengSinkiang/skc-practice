package com.dxj.skc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dxj.skc.entity.User1;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author sinkiang
 * @date 2022/4/8 9:41
 */
@Mapper
public interface User1Mapper extends BaseMapper<User1> {
    /**
     * 111
     * @return
     */
    int truncated();
}
