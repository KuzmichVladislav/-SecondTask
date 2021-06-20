package com.company.task2.parser;

import com.company.task2.entity.Deposit;
import com.company.task2.handler.DepositErrorHandler;
import com.company.task2.handler.DepositHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class DepositsSaxBuilder {
    private Set<Deposit> deposits;
    private DepositHandler handler = new DepositHandler();
    private XMLReader reader;

    public DepositsSaxBuilder() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        reader.setErrorHandler(new DepositErrorHandler());
        reader.setContentHandler(handler);
    }

    public Set<Deposit> getDeposits() {
        return deposits;
    }

    public void buildSetDeposits(String filename) {
        try {
            reader.parse(filename);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        deposits = handler.getDeposits();
    }
}
