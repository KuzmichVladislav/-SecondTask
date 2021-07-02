package com.company.task2.parser;

import com.company.task2.entity.Country;
import com.company.task2.entity.DemandDeposit;
import com.company.task2.entity.Deposit;
import com.company.task2.entity.TermDeposit;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class DepositsStaxBuilder extends AbstractDepositsBuilder {
    private final Set<Deposit> deposits;
    private final XMLInputFactory inputFactory;

    public DepositsStaxBuilder() {
        deposits = new HashSet<>();
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public Set<Deposit> getDeposits() {
        return deposits;
    }

    @Override
    public void buildSetDeposits(String fileName) {
        Deposit deposit = null;
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = inputFactory.createXMLEventReader(
                    new FileInputStream(fileName));
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    if (startElement.getName().getLocalPart().equals("term-deposit")) {
                        deposit = new TermDeposit();
                        Attribute bankName = startElement.getAttributeByName(new QName("bank-name"));
                        deposit.setBankName(bankName.getValue());
                        Attribute country = startElement.getAttributeByName(new QName("country"));
                        if (country != null) {
                            deposit.setCountry(Country.valueOf(country.getValue()));
                        }
                    } else if (startElement.getName().getLocalPart().equals("demand-deposit")) {
                        deposit = new DemandDeposit();
                        Attribute bankName = startElement.getAttributeByName(new QName("bank-name"));
                        deposit.setBankName(bankName.getValue());
                        Attribute country = startElement.getAttributeByName(new QName("country"));
                        if (country != null) {
                            deposit.setCountry(Country.valueOf(country.getValue()));
                        }
                    }
                    if (event.isStartElement()) {
                        StartElement startElementDepositor = event.asStartElement();
                        if (startElement.getName().getLocalPart().equals("name")) {
                            event = reader.nextEvent();
                            deposit.getDepositor().setName(event.asCharacters().getData());
                        } else if (startElement.getName().getLocalPart().equals("account-id")) {
                            event = reader.nextEvent();
                            deposit.getDepositor().setAccountID(event.asCharacters().getData());
                        } else if (startElement.getName().getLocalPart().equals("amount-on-deposit")) {
                            event = reader.nextEvent();
                            deposit.getDepositor().setAmountOnDeposit(Double.parseDouble(event.asCharacters().getData()));
                        } else if (startElement.getName().getLocalPart().equals("profitability")) {
                            event = reader.nextEvent();
                            deposit.getDepositor().setProfitability(Double.parseDouble(event.asCharacters().getData()));
                        } else if (startElement.getName().getLocalPart().equals("opening-date")) {
                            event = reader.nextEvent();
                            deposit.getDepositor().setOpeningDate(Date.valueOf(event.asCharacters().getData()));
                        }
                    }
                    if (startElement.getName().getLocalPart().equals("time-constraints")) {
                        event = reader.nextEvent();
                        TermDeposit termDeposit = (TermDeposit) deposit;
                        termDeposit.setTimeConstraints(Integer.parseInt(event.asCharacters().getData()));
                    }
                    if (startElement.getName().getLocalPart().equals("surrender")) {
                        event = reader.nextEvent();
                        DemandDeposit demandDeposit = (DemandDeposit) deposit;
                        demandDeposit.setSurrender(Double.parseDouble(event.asCharacters().getData()));
                    }
                }
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals("term-deposit") || endElement.getName().getLocalPart().equals("demand-deposit")) {
                        deposits.add(deposit);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
