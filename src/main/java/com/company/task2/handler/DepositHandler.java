package com.company.task2.handler;

import com.company.task2.entity.Country;
import com.company.task2.entity.DemandDeposit;
import com.company.task2.entity.Deposit;
import com.company.task2.entity.TermDeposit;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class DepositHandler extends DefaultHandler {
    String termDeposit = DepositXmlTag.TERM_DEPOSIT.getTag();
    String demandDeposit = DepositXmlTag.DEMAND_DEPOSIT.getTag();
    private final Set<Deposit> deposits;
    private Deposit current;
    private DepositXmlTag currentXmlTag;
    private final EnumSet<DepositXmlTag> withText;

    public DepositHandler() {
        deposits = new HashSet<>();
        withText = EnumSet.range(DepositXmlTag.NAME, DepositXmlTag.SURRENDER);
    }

    public Set<Deposit> getDeposits() {
        return deposits;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (termDeposit.equals(qName) || demandDeposit.equals(qName)) {
            current = termDeposit.equals(qName) ? new TermDeposit() : new DemandDeposit();
            current.setBankName(attrs.getValue(0));
            if (attrs.getLength() == 2) {
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
        if (termDeposit.equals(qName) || demandDeposit.equals(qName)) {
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
                case TIME_CONSTRAINTS -> {
                    TermDeposit currentTerm = (TermDeposit) current;
                    currentTerm.setTimeConstraints(Integer.parseInt(data));
                }
                case SURRENDER -> {
                    DemandDeposit currentDeamond = (DemandDeposit) current;
                    currentDeamond.setSurrender(Double.parseDouble(data));
                }
                default -> throw new EnumConstantNotPresentException(
                        currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }
}
