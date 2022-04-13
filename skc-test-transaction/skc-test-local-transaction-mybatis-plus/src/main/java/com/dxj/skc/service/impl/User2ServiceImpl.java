package com.dxj.skc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dxj.skc.domain.User2;
import com.dxj.skc.mapper.User2Mapper;
import com.dxj.skc.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sinkiang
 * @date 2022/4/8 10:06
 */
@Service
public class User2ServiceImpl extends ServiceImpl<User2Mapper, User2> implements User2Service {

    @Autowired
    private User2Mapper user2Mapper;

    @Override
    public void truncated(){
        user2Mapper.truncated();
    }

    @Override
    public void add(User2 user) {
        user2Mapper.insert(user);
    }

    @Override
    public void addException(User2 user) {
        user2Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    public User2 get(Integer id) {
        return user2Mapper.selectById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addRequired(User2 user) {
        user2Mapper.insert(user);
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = true)
    public User2 getRequired(Integer id) {
        return user2Mapper.selectById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addRequiredException(User2 user) {
        user2Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public void addSupports(User2 user) {
        user2Mapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public void addSupportsException(User2 user) {
        user2Mapper.insert(user);
        throw new RuntimeException();
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNew(User2 user) {
        user2Mapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNewException(User2 user) {
        user2Mapper.insert(user);
        throw new RuntimeException();
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NOT_SUPPORTED)
    public void addNotSupported(User2 user) {
        user2Mapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NOT_SUPPORTED)
    public void addNotSupportedException(User2 user) {
        user2Mapper.insert(user);
        throw new RuntimeException();
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.MANDATORY)
    public void addMandatory(User2 user) {
        user2Mapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.MANDATORY)
    public void addMandatoryException(User2 user) {
        user2Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NEVER)
    public void addNever(User2 user) {
        user2Mapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NEVER)
    public void addNeverException(User2 user) {
        user2Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void addNested(User2 user) {
        user2Mapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void addNestedException(User2 user) {
        user2Mapper.insert(user);
        throw new RuntimeException();
    }
}
