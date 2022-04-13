package com.dxj.skc2.service;

/**
 * @author sinkiang
 * @date 2022/4/8 10:06
 */
public interface TransactionPropagationExample {

    /**
     * 111
     */
	void truncated();
    /**
     * 111
     */
	void noTransactionRequiredRequired();

    /**
     * 111
     */
	void noTransactionExceptionRequiredRequired();
    /**
     * 111
     */
	void noTransactionRequiredRequiredException();

    /**
     * 111
     */
	void transactionExceptionRequiredRequired();
    /**
     * 111
     */
	void transactionRequiredRequiredException();
    /**
     * 111
     */
	void noTransactionSupportsSupportsException();
    /**
     * 111
     */
	void transactionSupportsSupportsException();
    /**
     * 111
     */
	void notransactionExceptionSupportsSupports();
    /**
     * 111
     */
	void transactionExceptionSupportsSupports();
    /**
     * 111
     */
	void transactionExceptionRequiredRequiresNewRequiresNew();
    /**
     * 111
     */
	void notransactionExceptionRequiresNewRequiresNew();
    /**
     * 111
     */
	void notransactionRequiresNewRequiresNewException();
    /**
     * 111
     */
	void transactionRequiredRequiresNewRequiresNewException();
    /**
     * 111
     */
	void noTransactionExceptionRequiredNotSuppored();
    /**
     * 111
     */
	void noTransactionRequiredNotSupporedException();
    /**
     * 111
     */
	void transactionExceptionRequiredNotSuppored();
    /**
     * 111
     */
	void transactionRequiredNotSupporedException();
    /**
     * 111
     */
	void notransactionExceptionNotransactionNotransaction();
    /**
     * 111
     */
	void noTransactionNotransactionNotransactionException();
    /**
     * 111
     */
	void transactionExceptionNoTransactionNotransactionUser1();
    /**
     * 111
     */
	void transactionNoTransactionNoTransactionException();
    /**
     * 111
     */
	void noTransactionMandatory();
    /**
     * 111
     */
	void transactionExceptionMandatoryMandatory();
    /**
     * 111
     */
	void transactionMandatoryMandatoryException();
    /**
     * 111
     */
	void noTransactionExceptionNeverNever();
    /**
     * 111
     */
	void noTransactionNeverNeverException();
    /**
     * 111
     */
	void transactionNever();
    /**
     * 111
     */
	void transactionExceptionNestedNested();
    /**
     * 111
     */
	void transactionNestedNestedException();
    /**
     * 111
     */
	void transactionNestedNestedExceptionTry();
    /**
     * 111
     */
	void transactionRequiredRequiredExceptionTry();
    /**
     * 111
     */
	void transactionNoTransactionNoTransactionExceptionTry();
    /**
     * 111
     */
	void transactionRequiredRequiresNewRequiresNewExceptionTry();
    /**
     * 111
     */
	void transactionExceptionNoTransactionNoTransactionUser2();
    /**
     * 111
     */
	void transactionExceptionNoTransactionNoTransaction();

	
	

}