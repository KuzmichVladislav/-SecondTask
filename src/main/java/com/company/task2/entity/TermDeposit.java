package com.company.task2.entity;

public class TermDeposit extends Deposit {
    int timeConstraints;

    public TermDeposit(int timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    public TermDeposit(String bankName, Country country, Depositor depositor, int timeConstraints) {
        super(bankName, country, depositor);
        this.timeConstraints = timeConstraints;
    }

    @Override
    public String toString() {
        return "TermDeposit{" +
                "timeConstraints=" + timeConstraints +
                '}';
    }
}