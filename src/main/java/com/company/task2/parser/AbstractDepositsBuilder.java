package com.company.task2.parser;

import com.company.task2.entity.Deposit;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractDepositsBuilder {
    protected Set<Deposit> deposits;

    protected AbstractDepositsBuilder() {
        deposits = new HashSet<>();
    }

    protected AbstractDepositsBuilder(Set<Deposit> deposits) {
        this.deposits = deposits;
    }

    public Set<Deposit> getDeposits() {
        return deposits;
    }

    public abstract void buildSetDeposits(String filename);
}
