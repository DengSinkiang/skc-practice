package com.dxj.skc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dxj.skc.domain.User1;
import com.dxj.skc.mapper.User1Mapper;
import com.dxj.skc.service.User1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sinkiang
 * @date 2022/4/8 10:06
 */
@Service
public class User1ServiceImpl extends ServiceImpl<User1Mapper, User1> implements User1Service {

    @Autowired
    private User1Mapper user1Mapper;

    @Override
    public void truncated(){
        user1Mapper.truncated();
    }

    @Override
    public void add(User1 user) {
        user1Mapper.insert(user);
    }

    @Override
    public User1 get(Integer id) {
        return user1Mapper.selectById(id);
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
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = true)
    public User1 getRequired(Integer id) {
        return user1Mapper.selectById(id);
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
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public User1 getRequiresNew(Integer id) {
        return user1Mapper.selectById(id);
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
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public User1 getNotSupported(Integer id) {
        return user1Mapper.selectById(id);
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


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED, readOnly = true)
    public User1 getNested(Integer id) {
        return user1Mapper.selectById(id);
    }
}
