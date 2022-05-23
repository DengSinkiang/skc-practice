package com.dxj.skc2.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dxj.skc2.entity.User1;
import com.dxj.skc2.mapper.User1Mapper;
import com.dxj.skc2.service.User1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sinkiang
 * @date 2022/4/8 10:06
 */
@Service
@DS("test1")
public class User1ServiceImpl implements User1Service {

    @Autowired
    private User1Mapper user1Mapper;

    @Override
    public void truncated() {
        user1Mapper.truncated();
    }

    @Override
    public void add(User1 user) {
        user1Mapper.insert(user);
    }

    @Override
    public void addException(User1 user) {
        user1Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addRequired(User1 user) {
        user1Mapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addRequiredException(User1 user) {
        user1Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public void addSupports(User1 user) {
        user1Mapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public void addSupportsException(User1 user) {
        user1Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNew(User1 user) {
        user1Mapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNewException(User1 user) {
        user1Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NOT_SUPPORTED)
    public void addNotSupported(User1 user) {
        user1Mapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NOT_SUPPORTED)
    public void addNotSupportedException(User1 user) {
        user1Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.MANDATORY)
    public void addMandatory(User1 user) {
        user1Mapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.MANDATORY)
    public void addMandatoryException(User1 user) {
        user1Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NEVER)
    public void addNever(User1 user) {
        user1Mapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NEVER)
    public void addNeverException(User1 user) {
        user1Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void addNested(User1 user) {
        user1Mapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void addNestedException(User1 user) {
        user1Mapper.insert(user);
        throw new RuntimeException();
    }
}
