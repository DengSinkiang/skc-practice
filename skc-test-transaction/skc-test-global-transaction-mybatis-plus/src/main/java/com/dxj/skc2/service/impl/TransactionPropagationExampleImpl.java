package com.dxj.skc2.service.impl;

import com.dxj.skc2.domain.User1;
import com.dxj.skc2.domain.User2;
import com.dxj.skc2.service.TransactionPropagationExample;
import com.dxj.skc2.service.User1Service;
import com.dxj.skc2.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sinkiang
 * @date 2022/4/8 10:06
 */
@Service
public class TransactionPropagationExampleImpl implements TransactionPropagationExample {
    @Autowired
    private User1Service user1Service;
    @Autowired
    private User2Service user2Service;


    @Override
    public void truncated() {
        user1Service.truncated();
        user2Service.truncated();
    }


    @Override
    public void notransactionExceptionNotransactionNotransaction() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.add(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.add(user2);
        throw new RuntimeException();
    }


    @Override
    public void noTransactionNotransactionNotransactionException() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.add(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addException(user2);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transactionExceptionNoTransactionNoTransaction() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.add(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.add(user2);
        throw new RuntimeException();
    }

    /**
     * 对user1数据源开启事务
     */
    @Override
    @Transactional(rollbackFor = Exception.class, value = "user1TM")
    public void transactionExceptionNoTransactionNotransactionUser1() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.add(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.add(user2);
        throw new RuntimeException();
    }

    /**
     * 对user2数据源开启事务
     */
    @Override
    @Transactional(rollbackFor = Exception.class, value = "user2TM")
    public void transactionExceptionNoTransactionNoTransactionUser2() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.add(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.add(user2);
        throw new RuntimeException();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transactionNoTransactionNoTransactionException() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.add(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addException(user2);
    }

    /**
     * 没有事务注解。
     */
    @Override
    public void noTransactionRequiredRequired() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addRequired(user2);
    }

    /**
     * 方法本身抛出异常
     */
    @Override
    public void noTransactionExceptionRequiredRequired() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addRequired(user2);

        throw new RuntimeException();
    }


    /**
     * 调用方法抛出异常
     */
    @Override
    public void noTransactionRequiredRequiredException() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addRequiredException(user2);
    }

    /**
     * 方法本身抛出异常
     */

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void transactionExceptionRequiredRequired() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addRequired(user2);

        throw new RuntimeException();
    }


    /**
     * 调用方法抛出异常
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void transactionRequiredRequiredException() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addRequiredException(user2);
    }


    @Override
    public void noTransactionSupportsSupportsException() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addSupports(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addSupportsException(user2);
    }

    @Override
    public void notransactionExceptionSupportsSupports() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addSupports(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addSupports(user2);
        throw new RuntimeException();

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void transactionSupportsSupportsException() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addSupports(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addSupportsException(user2);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void transactionExceptionSupportsSupports() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addSupports(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addSupports(user2);

        throw new RuntimeException();
    }


    @Override
    public void notransactionRequiresNewRequiresNewException() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequiresNew(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addRequiresNewException(user2);
    }

    @Override
    public void notransactionExceptionRequiresNewRequiresNew() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequiresNew(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addRequiresNew(user2);
        throw new RuntimeException();

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void transactionRequiredRequiresNewRequiresNewException() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addRequiresNew(user2);

        User2 user3 = new User2();
        user3.setName("王五");
        user2Service.addRequiresNewException(user3);
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void transactionRequiredRequiresNewRequiresNewExceptionTry() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addRequiresNew(user2);
        User2 user3 = new User2();
        user3.setName("王五");
        try {
            user2Service.addRequiresNewException(user3);
        } catch (Exception e) {
            System.out.println("回滚");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void transactionExceptionRequiredRequiresNewRequiresNew() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addRequiresNew(user2);

        User2 user3 = new User2();
        user3.setName("王五");
        user2Service.addRequiresNew(user3);

        throw new RuntimeException();
    }


    @Override
    public void noTransactionExceptionRequiredNotSuppored() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addNotSupported(user2);
        throw new RuntimeException();
    }

    @Override
    public void noTransactionRequiredNotSupporedException() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addNotSupportedException(user2);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transactionExceptionRequiredNotSuppored() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addNotSupported(user2);
        throw new RuntimeException();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transactionRequiredNotSupporedException() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addNotSupportedException(user2);
    }


    @Override
    public void noTransactionMandatory() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addMandatory(user1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transactionExceptionMandatoryMandatory() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addMandatory(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addMandatory(user2);
        throw new RuntimeException();
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transactionMandatoryMandatoryException() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addMandatory(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addMandatoryException(user2);
    }


    @Override
    public void noTransactionExceptionNeverNever() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addNever(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addNever(user2);
        throw new RuntimeException();
    }

    @Override
    public void noTransactionNeverNeverException() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addNever(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addNeverException(user2);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transactionNever() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addNever(user1);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transactionExceptionNestedNested() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addNested(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addNested(user2);
        throw new RuntimeException();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transactionNestedNestedException() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addNested(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addNestedException(user2);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transactionNestedNestedExceptionTry() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addNested(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        try {
            user2Service.addNestedException(user2);
        } catch (Exception e) {
            System.out.println("方法回滚");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transactionRequiredRequiredExceptionTry() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        try {
            user2Service.addRequiredException(user2);
        } catch (Exception e) {
            System.out.println("方法回滚");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transactionNoTransactionNoTransactionExceptionTry() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.add(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        try {
            user2Service.addException(user2);
        } catch (Exception e) {
            System.out.println("方法回滚");
        }
    }


}
