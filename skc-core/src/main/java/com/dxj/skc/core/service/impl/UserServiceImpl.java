package com.dxj.skc.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dxj.skc.core.domain.User;
import com.dxj.skc.core.mapper.UserMapper;
import com.dxj.skc.core.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author: sinkiang
 * @date: 2020/8/6 17:36
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
