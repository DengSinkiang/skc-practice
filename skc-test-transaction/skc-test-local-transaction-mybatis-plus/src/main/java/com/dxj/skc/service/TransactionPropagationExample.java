package com.dxj.skc.service;

/**
 * @descrigtion https://segmentfault.com/a/1190000013341344
 * @author sinkiang
 * @date 2022/4/8 9:52
 */
public interface TransactionPropagationExample {

    /**
     * 1
     */
    void notransactionRequiredRequired();

    /**
     * 2
     */
    void truncated();

    /**
     * 3
     */
    void notransactionExceptionRequiredRequired();

    /**
     * 4
     */
    void notransactionRequiredRequiredException();

    /**
     * 5
     */
    void transactionExceptionRequiredRequired();

    /**
     * 6
     */
    void transactionRequiredRequiredException();

    /**
     * 7
     */
    void notransactionSupportsSupportsException();

    /**
     * 8
     */
    void transactionSupportsSupportsException();

    /**
     * 9
     */
    void notransactionExceptionSupportsSupports();

    /**
     * 10
     */
    void transactionExceptionSupportsSupports();

    /**
     * 11
     */
    void transactionExceptionRequiredRequiresNewRequiresNew();

    /**
     * 12
     */
    void notransactionExceptionRequiresNewRequiresNew();

    /**
     * 13
     */
    void notransactionRequiresNewRequiresNewException();

    /**
     * 14
     */
    void transactionRequiredRequiresNewRequiresNewException();

    /**
     * 15
     */
    void notransactionExceptionRequiredNotSuppored();

    /**
     * 16
     */
    void notransactionRequiredNotSupporedException();

    /**
     * 17
     */
    void transactionExceptionRequiredNotSuppored();

    /**
     * 18
     */
    void transactionRequiredNotSupporedException();

    /**
     * 19
     */
    void notransactionExceptionNotransactionNotransaction();

    /**
     * 20
     */
    void notransactionNotransactionNotransactionException();

    /**
     * 21
     */
    void transactionExceptionNotransactionNotransaction();

    /**
     * 22
     */
    void transactionNotransactionNotransactionException();

    /**
     * 23
     */
    void notransactionMandatory();

    /**
     * 24
     */
    void transactionExceptionMandatoryMandatory();

    /**
     * 25
     */
    void transactionMandatoryMandatoryException();

    /**
     * 26
     */
    void notransactionExceptionNeverNever();

    /**
     * 27
     */
    void notransactionNeverNeverException();

    /**
     * 28
     */
    void transactionNever();

    /**
     * 28
     */
    void transactionExceptionNestedNested();

    /**
     * 28
     */
    void transactionNestedNestedException();

    /**
     * 28
     */
    void transactionNestedNestedExceptionTry();

    /**
     * 28
     */
    void transactionRequiredRequiredExceptionTry();
    /**
     * 28
     */
    void transactionNoTransactionNoTransactionExceptionTry();
    /**
     * 28
     */
    void transactionRequiredRequiresNewRequiresNewExceptionTry();
    /**
     * 28
     */
    void notransactionAddRequiredGetRequiredGet();
    /**
     * 28
     */
    void transactionAddRequiredGetRequiredGet();
    /**
     * 28
     */
    void transactionAddRequiredGetNestedGet();
    /**
     * 28
     */
    void transactionAddRequiredGetNotSupporedGet();
    /**
     * 28
     */
    void transactionAddRequiredGetRequiresNewGet();
    /**
     * 28
     */
    void notransactionExceptionNestedNested();
    /**
     * 28
     */
    void notransactionNestedNestedException();

}
