package com.dxj.skc2.service;

import com.dxj.skc2.domain.User1;

/**
 * @author sinkiang
 * @date 2022/4/8 10:06
 */
public interface User1Service {

	void addRequired(User1 user);

	void addRequiredException(User1 user);
	
	void truncated();

	void addSupports(User1 user);

	void addSupportsException(User1 user);

	void addRequiresNew(User1 user);

	void addRequiresNewException(User1 user);

	void addNotSupported(User1 user);

	void addNotSupportedException(User1 user);

	void add(User1 user);

	void addException(User1 user);

	void addMandatory(User1 user);

	void addMandatoryException(User1 user);

	void addNever(User1 user);

	void addNeverException(User1 user);

	void addNested(User1 user);

	void addNestedException(User1 user);

}