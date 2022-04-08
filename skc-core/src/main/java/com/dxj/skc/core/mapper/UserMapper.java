package com.dxj.skc.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dxj.skc.core.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: sinkiang
 * @date: 2022/4/7 10:32
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
