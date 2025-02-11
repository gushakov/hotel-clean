package com.github.hotelclean.core.port.transaction;

/*
    References:
    ----------

    1. Code copied from https://github.com/gushakov/cargo-clean/blob/main/src/main/java/com/github/cargoclean/core/port/transaction/TransactionOperationsOutputPort.java
 */

public interface TransactionOperationsOutputPort {

    void doInTransaction(boolean readOnly, TransactionRunnableWithoutResult runnableWithoutResult);

    void doAfterCommit(TransactionRunnableWithoutResult runnableWithoutResult);
}
