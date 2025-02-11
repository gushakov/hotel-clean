package com.github.hotelclean.core.port.transaction;

/*
    References:
    ----------

    1. Code copied from https://github.com/gushakov/cargo-clean/blob/main/src/main/java/com/github/cargoclean/core/port/transaction/TransactionRunnableWithoutResult.java
 */

@FunctionalInterface
public interface TransactionRunnableWithoutResult {
    void run();
}