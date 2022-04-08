package com.dxj.skc.service;

import com.dxj.skc.domain.User2;

/**
 * @author sinkiang
 * @date 2022/4/8 10:06
 */
public interface User2Service {
    void truncated();

    void addRequired(User2 user);

    void addRequiredException(User2 user);

    void addSupports(User2 user);

    void addSupportsException(User2 user);

    void addRequiresNew(User2 user);

    void addRequiresNewException(User2 user);

    void addNotSupported(User2 user);

    void addNotSupportedException(User2 user);

    void add(User2 user);

    void addException(User2 user);

    void addMandatory(User2 user);

    void addMandatoryException(User2 user);

    void addNever(User2 user);

    void addNeverException(User2 user);

    void addNested(User2 user);

    void addNestedException(User2 user);

    User2 getRequired(Integer id);

    User2 get(Integer id);
}
