package com.dxj.skc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dxj.skc.domain.entity.User;
import com.dxj.skc.mapper.UserMapper;
import com.dxj.skc.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: dengxj29231
 * @Date: 2020/8/6 17:36
 * @CopyRight: 2020 hundsun all rights reserved.
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
