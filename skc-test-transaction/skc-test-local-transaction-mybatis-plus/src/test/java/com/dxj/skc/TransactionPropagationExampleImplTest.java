package com.dxj.skc;

import com.dxj.skc.service.TransactionPropagationExample;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @description 测试各种 spring 事务传播属性。
 * @author sinkiang
 * @date 2022/4/7 10:32
 *
 */
@SpringBootTest(classes = LocalTransactionApplication.class)
public class TransactionPropagationExampleImplTest {

	@Autowired
	private TransactionPropagationExample transactionPropagationExample;

    @BeforeEach
    public void setUpBeforeClass() {
        transactionPropagationExample.truncated();
    }

	/**
	 * 结果：张三（插入），李四（插入）</br>
	 */
	@Test
	public void testNotransaction_exception_notransaction_notransaction() {
		transactionPropagationExample.notransactionExceptionNotransactionNotransaction();
	}

	/**
	 * 结果：张三（插入），李四（插入）</br>
	 */
	@Test
	public void testNotransaction_notransaction_notransaction_exception() {
		transactionPropagationExample.notransactionNotransactionNotransactionException();
	}

	/**
	 * 结果：张三（未插入），李四（未插入）</br>
	 * 外围方法开启事务，外围方法内的方法就应该在同一个事务中。外围方法抛出异常，整个事务所有方法都会被回滚。
	 */
	@Test
	public void testTransaction_exception_notransaction_notransaction() {
		transactionPropagationExample.transactionExceptionNotransactionNotransaction();
	}

	/**
	 * 结果：张三（未插入），李四（未插入）</br>
	 * 外围方法开启事务，外围方法内的方法就应该在同一个事务中。内部方法抛出异常，被外围方法捕获，整个事务中所有方法都会被回滚。
	 */
	@Test
	public void testTransaction_notransaction_notransaction_exception() {
		transactionPropagationExample.transactionNotransactionNotransactionException();
	}
	
	
	/**
	 * 结果：张三（插入），李四（插入）</br>
	 * 外围方法开启事务，内部方法都在同一事务中，只要不抛出异常，事务就不会回滚。
	 */
	@Test
	public void testTransaction_noTransaction_notransaction_exception_try() {
		transactionPropagationExample.transactionNoTransactionNoTransactionExceptionTry();
	}


	/**
	 * 结果：张三（插入），李四（插入）</br>
	 * 观察方法执行，执行完插入“张三”方法后数据库即插入数据，执行完插入“李四”方法后数据库即插入数据，结合后面回滚方法，说明两个方法分别在两个事务中执行。
	 */
	@Test
	public void testNoTransaction() {
		transactionPropagationExample.notransactionRequiredRequired();
	}
	
	


	/**
	 * 结果：张三（插入），李四（插入）。</br>
	 * 外围方法没有事务，插入“张三”、“李四”方法在自己的事务中独立运行，外围方法异常不影响内部插入“张三”、“李四”方法独立的事务。
	 */
	@Test
	public void testNotransaction_exception_required_required() {
		transactionPropagationExample.notransactionExceptionRequiredRequired();
	}

	/**
	 * 结果：张三（插入），李四（未插入）</br>
	 * 外围方法没有事务，插入“张三”、“李四”方法都在自己的事务中独立运行,所以插入“李四”方法抛出异常只会回滚插入“李四”方法，插入“张三”
	 * 方法不受影响。
	 * 
	 */
	@Test
	public void testNotransaction_required_required_exception() {
		transactionPropagationExample.notransactionRequiredRequiredException();
	}

	/**
	 * 结果：张三（未插入），李四（未插入）</br>
	 * 外围方法开启事务，插入“张三”、“李四”方法都在外围方法的事务中运行，加入外围方法事务，所以三个方法同一个事务。外围方法或内部方法抛出异常，
	 * 整个事务全部回滚。
	 */
	@Test
	public void testTransaction_exception_required_required() {
		transactionPropagationExample.transactionExceptionRequiredRequired();
	}

	/**
	 * 结果：张三（未插入），李四（未插入）</br>
	 * 外围方法开启事务，插入“张三”、“李四”方法都在外围方法的事务中运行，加入外围方法事务，所以三个方法同一个事务。外围方法或内部方法抛出异常，
	 * 整个事务全部回滚。
	 */
	@Test
	public void testTransaction_required_required_exception() {
		transactionPropagationExample.transactionRequiredRequiredException();
	}
	
	
	
	
	/**
	 * 结果：张三（未插入），李四（未插入）</br>
	 * 外围方法开启事务，插入“张三”、“李四”方法都在外围方法的事务中运行，加入外围方法事务，所以三个方法同一个事务。外围方法或内部方法抛出异常，
	 * 整个事务全部回滚。虽然我们catch了插入“李四”方法的异常，使异常不会被外围方法感知，但是插入“李四”方法事务被回滚，内部方法外围方法一个事务，所以整体事务被回滚了。
	 */
	@Test
	public void testTransaction_required_required_exception_try() {
		transactionPropagationExample.transactionRequiredRequiredExceptionTry();
	}

	/**
	 * 结果：张三（插入），李四（插入）</br>
	 * 外围方法未开启事务，插入“张三”、“李四”方法也均未开启事务，因为不存在事务所以无论外围方法或者内部方法抛出异常都不会回滚。
	 */
	@Test
	public void testNotransaction_supports_supports_exception() {
		transactionPropagationExample.notransactionSupportsSupportsException();
	}

	/**
	 * 结果：张三（插入），李四（插入）</br>
	 * 外围方法未开启事务，插入“张三”、“李四”方法也均未开启事务，因为不存在事务所以无论外围方法或者内部方法抛出异常都不会回滚。
	 */
	@Test
	public void testNotransaction_exception_supports_supports() {
		transactionPropagationExample.notransactionExceptionSupportsSupports();
	}

	/**
	 * 结果：张三（未插入），李四（未插入）</br>
	 * 外围方法开启事务，插入“张三”、“李四”方法都在外围方法的事务中运行，加入外围方法事务，所以三个方法同一个事务。外围方法或内部方法抛出异常，
	 * 整个事务全部回滚。
	 */
	@Test
	public void testTransaction_supports_supports_exception() {
		transactionPropagationExample.transactionSupportsSupportsException();
	}

	/**
	 * 结果：张三（未插入），李四（未插入）</br>
	 * 外围方法开启事务，插入“张三”、“李四”方法都在外围方法的事务中运行，加入外围方法事务，所以三个方法同一个事务。外围方法或内部方法抛出异常，
	 * 整个事务全部回滚。
	 */
	@Test
	public void testTransaction_exception_supports_supports() {
		transactionPropagationExample.transactionExceptionSupportsSupports();
	}

	// ---------------------------------------------------------------------------------
	// REQUIRED和SUPPORTS在外围方法支持事务的时候没有区别，均加入外围方法的事务中。
	// 当外围方法不支持事务，REQUIRED开启新的事务而SUPPORTS不开启事务。
	// REQUIRED的事务一定和外围方法事务统一。如果外围方法没有事务，每一个内部REQUIRED方法都会开启一个新的事务，互不干扰。
	// ---------------------------------------------------------------------------------

	/**
	 * 结果：张三（插入），李四（插入）</br>
	 * 外围方法未开启事务，插入“张三”、“李四”方法都在自己的事务中独立运行。外围方法抛出异常，插入“张三”、“李四”事务均不回滚。
	 */
	@Test
	public void testNotransaction_exception_requiresNew_requiresNew() {
		transactionPropagationExample.notransactionExceptionRequiresNewRequiresNew();
	}

	/**
	 * 结果：张三（插入），李四（未插入）</br>
	 * 外围方法未开启事务，插入“张三”、“李四”方法都在自己的事务中独立运行。插入“李四”方法抛出异常只会导致插入“李四”方法中的事务被回滚，
	 * 不会影响插入“张三”方法的事务。
	 */
	@Test
	public void testNotransaction_requiresNew_requiresNew_exception() {
		transactionPropagationExample.notransactionRequiresNewRequiresNewException();
	}

	/**
	 * 结果：张三（未插入），李四（插入），王五（插入）</br>
	 * 外围方法开启事务，插入“张三”方法和外围方法一个事务，插入“李四”方法、插入“王五”方法分别在独立的新建事务中，
	 * 外围方法抛出异常只回滚和外围方法同一事务的方法，故插入“张三”的方法回滚。
	 */
	@Test
	public void testTransaction_exception_required_requiresNew_requiresNew() {
		transactionPropagationExample.transactionExceptionRequiredRequiresNewRequiresNew();
	}

	/**
	 * 结果：张三（未插入），李四（插入），王五（未插入）</br>
	 * 外围方法开启事务，插入“张三”方法和外围方法一个事务，插入“李四”方法、插入“王五”方法分别在独立的新建事务中。插入“王五”方法抛出异常，首先插入
	 * “王五”方法的事务被回滚，异常继续抛出被外围方法感知，外围方法事务亦被回滚，故插入“张三”方法也被回滚。
	 */
	@Test
	public void testTransaction_required_requiresNew_requiresNew_exception() {
		transactionPropagationExample.transactionRequiredRequiresNewRequiresNewException();
	}
	
	/**
	 * 结果：张三（插入），李四（插入），王五（未插入）</br>
	 * 外围方法开启事务，插入“张三”方法和外围方法一个事务，插入“李四”方法、插入“王五”方法分别在独立的新建事务中。插入“王五”方法抛出异常，首先插入
	 * “王五”方法的事务被回滚，异常被catch不会被外围方法感知，外围方法事务不回滚，故插入“张三”方法插入成功。
	 */
	@Test
	public void testTransaction_required_requiresNew_requiresNew_exception_try() {
		transactionPropagationExample.transactionRequiredRequiresNewRequiresNewExceptionTry();
	}

	
	
	// ---------------------------------------------------------------------------------
	// REQUIRES_NEW标注方法无论外围方法是否开启事务，内部REQUIRES_NEW方法均会开启独立事务，且和外围方法也不在同一个事务中，内部方法和外围方法、内部方法之间均不相互干扰。
	// 当外围方法不开启事务的时候，REQUIRED和REQUIRES_NEW标注的内部方法效果相同。
	// ---------------------------------------------------------------------------------

	/**
	 * 结果：张三（插入），李四（插入）</br>
	 * 外围方法未开启事务，插入“张三”方法在自己的事务中运行，插入“李四”方法不在任何事务中运行。外围方法抛出异常，但是外围方法没有事务，
	 * 所以其他内部事务方法不会被回滚，非事务方法更不会被回滚。
	 * 
	 */
	@Test
	public void testNotransaction_exception_required_notSuppored() {
		transactionPropagationExample.notransactionExceptionRequiredNotSuppored();
	}

	/**
	 * 结果：张三（插入），李四（插入）</br>
	 * 外围方法未开启事务，插入“张三”方法在自己的事务中运行，插入“李四”方法不在任何事务中运行。
	 * 插入“李四”方法抛出异常，首先因为插入“李四”方法没有开启事务，所以“李四”方法不会回滚，外围方法感知异常，但是因为外围方法没有事务，
	 * 所有外围方法也不会回滚。
	 * 
	 */
	@Test
	public void testNotransaction_required_notSuppored_exception() {
		transactionPropagationExample.notransactionRequiredNotSupporedException();
	}

	/**
	 * 结果：张三（未插入），李四（插入）</br>
	 * 外围方法开启事务，因为插入“张三”方法传播为required，所以和外围方法同一个事务。插入“李四”方法不在任何事务中运行。
	 * 外围方法抛出异常，外围方法所在的事务将会回滚，因为插入“张三”方法和外围方法同一个事务，所以将会回滚。
	 * 
	 */
	@Test
	public void testTransaction_exception_required_notSuppored() {
		transactionPropagationExample.transactionExceptionRequiredNotSuppored();
	}

	/**
	 * 结果：张三（未插入），李四（插入）</br>
	 * 外围方法开启事务，因为插入“张三”方法传播为required，所以和外围方法同一个事务。插入“李四”方法不在任何事务中运行。
	 * 插入“李四”方法抛出异常，因为此方法不开启事务，所以此方法不会被回滚，外围方法接收到了异常，所以外围事务需要回滚，因插入“张三”
	 * 方法和外围方法同一事务，故被回滚。
	 * 
	 */
	@Test
	public void testTransaction_required_notSuppored_exception() {
		transactionPropagationExample.transactionRequiredNotSupporedException();
	}

	// ---------------------------------------------------------------------------------
	// NOT_SUPPORTED明确表示不开启事务。
	// ---------------------------------------------------------------------------------

	
	/**
	 * 结果：张三（未插入）</br>
	 * 外围方法未开启事务。内部插入“张三”方法执行的时候因外围没有事务而直接抛出异常，具体插入方法都没有机会执行。
	 * 
	 */
	@Test
	public void testNotransaction_mandatory() {
		transactionPropagationExample.notransactionMandatory();
	}

	
	/**
	 * 结果：张三（未插入），李四（未插入）</br>
	 * 外围方法开启事务，插入“张三”方法和插入“李四”方法都加入外围方法事务，外围方法抛出异常，事务回滚。
	 * 
	 */
	@Test
	public void testTransaction_exception_mandatory_mandatory() {
		transactionPropagationExample.transactionExceptionMandatoryMandatory();
	}

	/**
	 * 结果：张三（未插入），李四（未插入）</br>
	 * 外围方法开启事务，插入“张三”方法和插入“李四”方法都加入外围方法事务，内部方法抛出异常，整个事务回滚。
	 * 
	 */
	@Test
	public void testTransaction_mandatory_mandatory_exception() {
		transactionPropagationExample.transactionMandatoryMandatoryException();
	}
	
	
	/**
	 * 结果：张三（未插入</br>
	 * 外围方法开启事务。内部插入“张三”方法执行的时候因外围有事务而直接抛出异常，具体插入方法都没有机会执行。
	 * 
	 */
	@Test
	public void testTransaction_never() {
		transactionPropagationExample.transactionNever();
	}
	
	/**
	 * 结果：张三（插入），李四（插入）</br>
	 * 外围方法未开启事务，插入“张三”方法和插入“李四”方法也均无事务，任何异常都不会回滚。。
	 * 
	 */
	@Test
	public void testNotransaction_exception_never_never() {
		transactionPropagationExample.notransactionExceptionNeverNever();
	}
	/**
	 * 结果：张三（插入），李四（插入）</br>
	 * 外围方法未开启事务，插入“张三”方法和插入“李四”方法也均无事务，任何异常都不会回滚。。
	 * 
	 */
	@Test
	public void testNotransaction_never_never_exception() {
		transactionPropagationExample.notransactionNeverNeverException();
	}
	
	
	/**
	 * 结果：张三（未插入），李四（未插入）</br>
	 * 外围方法开启事务，插入“张三”方法和插入“李四”方法为外围方法的子事务，外围方法事务回滚，相应的子事务也会回滚。
	 * 
	 */
	@Test
	public void testTransaction_exception_nested_nested() {
		transactionPropagationExample.transactionExceptionNestedNested();
	}
	
	
	/**
	 * 结果：张三（未插入），李四（未插入）</br>
	 * 外围方法开启事务，插入“张三”方法和插入“李四”方法为外围方法的子事务，插入“李四”方法抛出异常，相应的子事务回滚，异常被外围方法感知，外围方法事务回滚，其他子事务即插入“张三”方法事务也回滚了。
	 * 
	 */
	@Test
	public void testTransaction_nested_nested_exception() {
		transactionPropagationExample.transactionNestedNestedException();
	}
	
	/**
	 * 结果：张三（插入），李四（插入）</br>
	 * 外围方法为开启事务，插入“张三”方法和插入“李四”方法分别开启自己的事务，外围方法事务回滚，所有方法均不回滚。
	 * 
	 */
	@Test
	public void testNotransaction_exception_nested_nested() {
		transactionPropagationExample.notransactionExceptionNestedNested();
	}
	
	
	/**
	 * 结果：张三（插入），李四（未插入）</br>
	 * 外围方法开启事务，插入“张三”方法和插入“李四”方法分别开启自己的事务，插入“李四”方法抛出异常，相应的子事务回滚，异常被外围方法感知，外围方法无事务所以无需回滚，故插入“张三”方法没有回滚。
	 * 
	 */
	@Test
	public void testnotransaction_nested_nested_exception() {
		transactionPropagationExample.notransactionNestedNestedException();
	}
	
	//------------------------------------------
	//在外围方法不开启事务的时候，NESTED和REQUIRED行为类似，均开新开事务。
	//------------------------------------------
	
	
	/**
	 * 结果：张三（插入），李四（未插入）</br>
	 * 外围方法开启事务，插入“张三”方法和插入“李四”方法为外围方法的子事务，插入“李四”方法抛出异常，相应的子事务回滚，异常被捕获外围方法不可知，故外围方法事务无需回滚。
	 * 
	 */
	@Test
	public void testTransaction_nested_nested_exception_try() {
		transactionPropagationExample.transactionNestedNestedExceptionTry();
	}
	
	//------------------------------------------------------------------------------
	//NESTED和REQUIRED修饰的内部方法都属于外围方法事务，如果外围方法抛出异常，这两种方法的事务都会被回滚。
	//但是REQUIRED是加入外围方法事务，所以和外围事务同属于一个事务，所以一旦REQUIRED事务抛出异常被回滚，外围方法事务也将被回滚。{@link testTransaction_required_required_exception_try}
	//而NESTED是外围方法的子事务，有单独的保存点，所以NESTED方法抛出异常被回滚，不会影响到外围方法的事务。{@link testTransaction_nested_nested_exception_try}
	
	//NESTED和REQUIRES_NEW都可以做到内部方法事务回滚但是不影响外围方法事务。{@link testTransaction_required_requiresNew_requiresNew_exception_try}
	//但是因为NESTED是嵌套事务，所以外围方法回滚之后，作为外围方法事务的子事务也会被回滚。{@link testTransaction_exception_nested_nested}
	//而REQUIRES_NEW是通过开启新的事务实现的，内部事务和外围事务是两个事务，外围事务回滚不会影响内部事务。{@link testTransaction_exception_required_requiresNew_requiresNew}
	//------------------------------------------------------------------------------
	
	
	//------------------------------------------------------------------------------
	//事务之间可见性试验，进一步说明新开事务和嵌套事务之间的区别。
	//------------------------------------------------------------------------------
	/**
	 * 结果：getRequired可见，get可见</br>
	 * 外围方法未开启事务，addRequired在自己的事务中运行，执行外之后即对外可见，故getRequired和get都可见事务执行结果。
	 */
	@Test
	public void testNotransaction_addRequired_getRequired_get() {
		transactionPropagationExample.notransactionAddRequiredGetRequiredGet();
	}
	
	
	/**
	 * 结果：getRequired可见，get可见</br>
	 * 外围方法开启事务，addRequired和外围方法同事务，getRequired和get都和外围方法同一个事务，故均可见addRequired执行之后的结果。
	 */
	@Test
	public void testTransaction_addRequired_getRequired_get() {
		transactionPropagationExample.transactionAddRequiredGetRequiredGet();
	}
	
	/**
	 * 结果：getNested可见，get可见</br>
	 * 外围方法开启事务，addRequired和外围方法同事务，getNested属于外围事务子事务，get属于外围事务，故均可见addRequired执行之后的结果。
	 */
	@Test
	public void testTransaction_addRequired_getNested_get() {
		transactionPropagationExample.transactionAddRequiredGetNestedGet();
	}
	
	
	/**
	 * 结果：get可见</br>
	 * 外围方法开启事务，addRequired和外围方法同事务，getNotSuppored不支持事务，并将外围事务挂起，getNotSuppored不在addRequired事务范围中，由于事务隔离性，getNotSuppored看不到addRequired的执行结果。
	 * ，get属于外围事务，故可见addRequired执行之后的结果。
	 */
	@Test
	public void testTransaction_addRequired_getNotSuppored_get() {
		transactionPropagationExample.transactionAddRequiredGetNotSupporedGet();
	}
	
	
	/**
	 * 结果：get可见</br>
	 * 外围方法开启事务，addRequired和外围方法同事务，getRequiresNew新开事务，并将外围事务挂起，由于事务隔离性，getRequiresNew看不到addRequired的执行结果。
	 * ，get属于外围事务，故可见addRequired执行之后的结果。
	 */
	@Test
	public void testTransaction_addRequired_getRequiresNew_get() {
		transactionPropagationExample.transactionAddRequiredGetRequiresNewGet();
	}
	
	//------------------------------------------------------------------------------
	//以上结果说明:
	//对于REQUIRES_NEW和NOT_SUPPORTED传播属性，在外围方法开启事务，均不可见外围事务执行结果。
	//对于REQUIREED和NESTED传播属性,无论外围方法是否开启事务,均可见外围方法执行结果。
	//------------------------------------------------------------------------------
	
	


	
}
