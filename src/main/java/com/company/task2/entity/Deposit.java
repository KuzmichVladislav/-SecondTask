package com.company.task2.entity;

import java.util.Date;

public abstract class Deposit {


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
        final StringBuilder sb = new StringBuilder("Deposit{");
        sb.append("bankName='").append(bankName).append('\'');
        sb.append(", country=").append(country);
        sb.append(", depositor=").append(depositor);
        sb.append('}');
        return sb.toString();
    }

    public class Depositor {
        private String name;
        private String accountID;
        private double amountOnDeposit;
        private double profitability;
        private Date openingDate;

        public Depositor() {
        }

        public Depositor(String name, String accountID, double amountOnDeposit, double profitability, Date openingDate, int timeConstraints) {
            this.name = name;
            this.accountID = accountID;
            this.amountOnDeposit = amountOnDeposit;
            this.profitability = profitability;
            this.openingDate = openingDate;
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


        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Depositor{");
            sb.append("name='").append(name).append('\'');
            sb.append(", accountID='").append(accountID).append('\'');
            sb.append(", amountOnDeposit=").append(amountOnDeposit);
            sb.append(", profitability=").append(profitability);
            sb.append(", openingDate=").append(openingDate);
            sb.append('}');
            return sb.toString();
        }
    }
}