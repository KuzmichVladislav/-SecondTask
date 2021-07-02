package com.company.task2.main;

import com.company.task2.parser.AbstractDepositsBuilder;
import com.company.task2.parser.DepositBuilderFactory;

public class Main {
    public static void main(String[] args) {

       /*try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(new ConsoleDepositHandler());
            reader.setErrorHandler(new DepositErrorHandler());
            reader.parse("src/main/resources/data_xml/deposits.xml");
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }*/

        String type = "dom";
        AbstractDepositsBuilder builder = DepositBuilderFactory.createDepositsBuilder(type);
        builder.buildSetDeposits("src/main/resources/data_xml/deposits.xml");
        System.out.println(builder.getDeposits());
    }
}
