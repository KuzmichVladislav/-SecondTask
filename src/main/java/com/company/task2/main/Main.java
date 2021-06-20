package com.company.task2.main;

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

        /*DepositsSaxBuilder saxBuilder = new DepositsSaxBuilder();
        saxBuilder.buildSetDeposits("src/main/resources/data_xml/deposits.xml");
        System.out.println(saxBuilder.getDeposits());*/

        /*DepositsDomBuilder domBuilder = new DepositsDomBuilder();
        domBuilder.buildSetDeposits("src/main/resources/data_xml/deposits.xml");
        System.out.println(domBuilder.getDeposits());*/
    }
}
