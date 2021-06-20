package com.company.task2.handler;

import com.company.task2.entity.Country;
import com.company.task2.entity.Deposit;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class DepositHandler extends DefaultHandler {
    private static final String ELEMENT_DEPOSIT = "deposit";
    private Set<Deposit> deposits;
    private Deposit current;
    private DepositXmlTag currentXmlTag;
    private EnumSet<DepositXmlTag> withText;

    public DepositHandler() {
        deposits = new HashSet<Deposit>();
        withText = EnumSet.range(DepositXmlTag.NAME, DepositXmlTag.TIME_CONSTRAINTS);
    }

    public Set<Deposit> getDeposits() {
        return deposits;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (ELEMENT_DEPOSIT.equals(qName)) {
            current = new Deposit();
            current.setBankName(attrs.getValue(0));
            if (attrs.getLength() == 2) { // warning!!!!
                current.setCountry(Country.valueOf(attrs.getValue(1)));
            }

        } else {
            DepositXmlTag temp = DepositXmlTag.valueOf(qName.toUpperCase().replace('-', '_'));
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }

    }

    public void endElement(String uri, String localName, String qName) {
        if (ELEMENT_DEPOSIT.equals(qName)) {
            deposits.add(current);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case NAME -> current.getDepositor().setName(data);
                case ACCOUNT_ID -> current.getDepositor().setAccountID(data);
                case AMOUNT_ON_DEPOSIT -> current.getDepositor().setAmountOnDeposit(Double.parseDouble(data));
                case PROFITABILITY -> current.getDepositor().setProfitability(Double.parseDouble(data));
                case OPENING_DATE -> current.getDepositor().setOpeningDate(Date.valueOf(data));
                case TIME_CONSTRAINTS -> current.getDepositor().setTimeConstraints(Integer.parseInt(data));
                // default -> throw new EnumConstantNotPresentException(
                //        currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }
}
