package com.dxj.skc2.service;

import com.dxj.skc2.domain.User1;

/**
 * @author sinkiang
 * @date 2022/4/8 10:06
 */
public interface User1Service {
    /**
     * 111
     * @param user
     */
	void addRequired(User1 user);
    /**
     * 111
     * @param user
     */
	void addRequiredException(User1 user);

    /**
     * 111
     */
	void truncated();
    /**
     * 111
     * @param user
     */
	void addSupports(User1 user);
    /**
     * 111
     * @param user
     */
	void addSupportsException(User1 user);
    /**
     * 111
     * @param user
     */
	void addRequiresNew(User1 user);
    /**
     * 111
     * @param user
     */
	void addRequiresNewException(User1 user);
    /**
     * 111
     * @param user
     */
	void addNotSupported(User1 user);
    /**
     * 111
     * @param user
     */
	void addNotSupportedException(User1 user);
    /**
     * 111
     * @param user
     */
	void add(User1 user);
    /**
     * 111
     * @param user
     */
	void addException(User1 user);
    /**
     * 111
     * @param user
     */
	void addMandatory(User1 user);
    /**
     * 111
     * @param user
     */
	void addMandatoryException(User1 user);
    /**
     * 111
     * @param user
     */
	void addNever(User1 user);
    /**
     * 111
     * @param user
     */
	void addNeverException(User1 user);
    /**
     * 111
     * @param user
     */
	void addNested(User1 user);
    /**
     * 111
     * @param user
     */
	void addNestedException(User1 user);

}