package com.company.task2.parser;

import com.company.task2.entity.Country;
import com.company.task2.entity.Deposit;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class DepositsDomBuilder {
    private Set<Deposit> deposits;
    private DocumentBuilder docBuilder;

    public DepositsDomBuilder() {
        deposits = new HashSet<Deposit>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

    public Set<Deposit> getDeposits() {
        return deposits;
    }

    public void buildSetDeposits(String filename) {
        Document doc;
        try {
            doc = docBuilder.parse(filename);
            Element root = doc.getDocumentElement();
            NodeList depositsList = root.getElementsByTagName("deposit");
            for (int i = 0; i < depositsList.getLength(); i++) {
                Element depositElement = (Element) depositsList.item(i);
                Deposit deposit = buildDeposit(depositElement);
                deposits.add(deposit);
            }
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    private Deposit buildDeposit(Element depositElement) {
        Deposit deposit = new Deposit();
        deposit.setCountry(Country.valueOf(depositElement.getAttribute("country")));
        Deposit.Depositor depositor = deposit.getDepositor();
        Element depositorElement =
                (Element) depositElement.getElementsByTagName("depositor").item(0);
        depositor.setName(getElementTextContent(depositElement, "name"));
        depositor.setAccountID(getElementTextContent(depositElement, "account-id"));
        Double amountOnDeposit = Double.parseDouble(getElementTextContent(depositElement, "amount-on-deposit"));
        depositor.setAmountOnDeposit(amountOnDeposit);
        Double profitability = Double.parseDouble(getElementTextContent(depositElement, "profitability"));
        depositor.setProfitability(profitability);
        Date openingDate = Date.valueOf(getElementTextContent(depositElement, "opening-date"));
        depositor.setOpeningDate(openingDate);
        Integer timeConstraints = Integer.parseInt(getElementTextContent(depositElement, "time-constraints"));
        depositor.setTimeConstraints(timeConstraints);
        deposit.setBankName(depositElement.getAttribute("bank-name"));
        return deposit;
    }
}