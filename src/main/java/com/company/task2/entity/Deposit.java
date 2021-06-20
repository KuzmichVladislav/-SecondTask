package com.company.task2.entity;

import java.util.Date;

public class Deposit {


    private String bankName;
    private Country country;
    private Depositor depositor = new Depositor();

    public Deposit() {
    }

    public Deposit(String bankName, Country country, Depositor depositor) {
        this.bankName = bankName;
        this.country = country;
        this.depositor = depositor;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Depositor getDepositor() {
        return depositor;
    }

    public void setDepositor(Depositor depositor) {
        this.depositor = depositor;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "bankName='" + bankName + '\'' +
                ", country=" + country.getFullName() +
                ", depositor=" + depositor +
                '}' + '\n';
    }

    public class Depositor {
        private String name;
        private String accountID;
        private double amountOnDeposit;
        private double profitability;
        private Date openingDate;
        private int timeConstraints;

        public Depositor() {
        }

        public Depositor(String name, String accountID, double amountOnDeposit, double profitability, Date openingDate, int timeConstraints) {
            this.name = name;
            this.accountID = accountID;
            this.amountOnDeposit = amountOnDeposit;
            this.profitability = profitability;
            this.openingDate = openingDate;
            this.timeConstraints = timeConstraints;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAccountID() {
            return accountID;
        }

        public void setAccountID(String accountID) {
            this.accountID = accountID;
        }

        public double getAmountOnDeposit() {
            return amountOnDeposit;
        }

        public void setAmountOnDeposit(double amountOnDeposit) {
            this.amountOnDeposit = amountOnDeposit;
        }

        public double getProfitability() {
            return profitability;
        }

        public void setProfitability(double profitability) {
            this.profitability = profitability;
        }

        public Date getOpeningDate() {
            return openingDate;
        }

        public void setOpeningDate(Date openingDate) {
            this.openingDate = openingDate;
        }

        public int getTimeConstraints() {
            return timeConstraints;
        }

        public void setTimeConstraints(int timeConstraints) {
            this.timeConstraints = timeConstraints;
        }

        @Override
        public String toString() {
            return "Depositor{" +
                    "name='" + name + '\'' +
                    ", accountID='" + accountID + '\'' +
                    ", amountOnDeposit=" + amountOnDeposit +
                    ", profitability=" + profitability +
                    ", openingDate=" + openingDate +
                    ", timeConstraints=" + timeConstraints +
                    '}';
        }
    }
}