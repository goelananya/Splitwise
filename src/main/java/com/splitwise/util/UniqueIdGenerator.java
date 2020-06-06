package com.splitwise.util;

public class UniqueIdGenerator {

    static int id = 0;
    public static String generateExpenseId() {
        return SplitWiseConstants.EXPENSE.concat(Integer.toString(id++));
    }

    public static String generateTransactionId() {
        return SplitWiseConstants.TRANSACTION.concat(Integer.toString(id++));
    }

    public static String generateUserId() {
        return SplitWiseConstants.USER.concat(Integer.toString(id++));
    }

    public static String generateBalanceId() {
        return SplitWiseConstants.BALANCE.concat(Integer.toString(id++));
    }

    public static String generateBalanceBookId() {
        return SplitWiseConstants.BALANCE_BOOK.concat(Integer.toString(id++));
    }
}
