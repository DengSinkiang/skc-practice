package com.dxj.skc.service;

import com.dxj.skc.domain.User2;

/**
 * @author sinkiang
 * @date 2022/4/8 10:06
 */
public interface User2Service {
    /**
     * 111
     */
    void truncated();
    /**
     * 111
     * @param user
     */
    void addRequired(User2 user);
    /**
     * 111
     * @param user
     */
    void addRequiredException(User2 user);
    /**
     * 111
     * @param user
     */
    void addSupports(User2 user);
    /**
     * 111
     * @param user
     */
    void addSupportsException(User2 user);
    /**
     * 111
     * @param user
     */
    void addRequiresNew(User2 user);
    /**
     * 111
     * @param user
     */
    void addRequiresNewException(User2 user);
    /**
     * 111
     * @param user
     */
    void addNotSupported(User2 user);
    /**
     * 111
     * @param user
     */
    void addNotSupportedException(User2 user);
    /**
     * 111
     * @param user
     */
    void add(User2 user);
    /**
     * 111
     * @param user
     */
    void addException(User2 user);
    /**
     * 111
     * @param user
     */
    void addMandatory(User2 user);
    /**
     * 111
     * @param user
     */
    void addMandatoryException(User2 user);
    /**
     * 111
     * @param user
     */
    void addNever(User2 user);
    /**
     * 111
     * @param user
     */
    void addNeverException(User2 user);
    /**
     * 111
     * @param user
     */
    void addNested(User2 user);
    /**
     * 111
     * @param user
     */
    void addNestedException(User2 user);

    /**
     * 111
     * @param id
     * @return
     */
    User2 getRequired(Integer id);
    /**
     * 111
     * @param id
     * @return
     */
    User2 get(Integer id);
}
