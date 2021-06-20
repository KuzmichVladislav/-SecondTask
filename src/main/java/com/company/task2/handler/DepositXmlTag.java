package com.company.task2.handler;

enum DepositXmlTag {
    DEPOSITS("deposits"),
    DEPOSIT("deposit"),
    BANK_NAME("bank-name"),
    COUNTRY("country"),
    DEPOSITOR("depositor"),
    NAME("name"),
    ACCOUNT_ID("account-id"),
    AMOUNT_ON_DEPOSIT("amount-on-deposit"),
    PROFITABILITY("profitability"),
    OPENING_DATE("opening-date"),
    TIME_CONSTRAINTS("time-constraints");

    private final String tag;

    DepositXmlTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
