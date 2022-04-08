package com.dxj.skc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dxj.skc.domain.User2;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author sinkiang
 * @date 2022/4/8 9:42
 */
@Mapper
public interface User2Mapper extends BaseMapper<User2> {
    int truncated();
}
