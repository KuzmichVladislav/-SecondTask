package com.company.task2.entity;

public class TermDeposit extends Deposit {
    int timeConstraints;

    public TermDeposit() {
    }

    public TermDeposit(String bankName, Country country, Depositor depositor, int timeConstraints) {
        super(bankName, country, depositor);
        this.timeConstraints = timeConstraints;
    }

    public int getTimeConstraints() {
        return timeConstraints;
    }

    public void setTimeConstraints(int timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Term deposit").append('\n');
        sb.append(super.toString());
        sb.append("\t\tTime constraints: ").append(timeConstraints);
        sb.append("\n\n");
        return sb.toString();
    }
}