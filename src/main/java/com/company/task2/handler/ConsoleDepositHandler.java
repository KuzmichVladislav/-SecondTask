package com.company.task2.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ConsoleDepositHandler extends DefaultHandler {
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        String tagData = qName + " ";
        for (int i = 0; i < attributes.getLength(); i++) {
            tagData += " " + attributes.getQName(i) + "=" + attributes.getValue(i);
        }
        System.out.print(tagData);

    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.print(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.print(" " + qName);
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("\nParsing ended");
    }
}
