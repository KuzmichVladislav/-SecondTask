package com.company.task2.parser;

import com.company.task2.entity.Country;
import com.company.task2.entity.DemandDeposit;
import com.company.task2.entity.Deposit;
import com.company.task2.entity.TermDeposit;
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

public class DepositsDomBuilder extends AbstractDepositsBuilder {
    private final Set<Deposit> deposits;
    private DocumentBuilder docBuilder;

    public DepositsDomBuilder() {
        deposits = new HashSet<>();

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

    @Override
    public Set<Deposit> getDeposits() {
        return deposits;
    }

    @Override
    public void buildSetDeposits(String filename) {
        Document doc;
        try {
            doc = docBuilder.parse(filename);
            Element termRoot = doc.getDocumentElement();
            Element demandRoot = doc.getDocumentElement();
            NodeList termDepositsList = termRoot.getElementsByTagName("term-deposit");
            for (int i = 0; i < termDepositsList.getLength(); i++) {
                Element depositElement = (Element) termDepositsList.item(i);
                Deposit deposit = buildDeposit(depositElement);
                deposits.add(deposit);
            }
            NodeList demandDepositsList = demandRoot.getElementsByTagName("demand-deposit");
            for (int i = 0; i < demandDepositsList.getLength(); i++) {
                Element depositElement = (Element) demandDepositsList.item(i);
                Deposit deposit = buildDeposit(depositElement);
                deposits.add(deposit);
            }
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    private Deposit buildDeposit(Element depositElement) {
        Deposit deposit;
        if (depositElement.getTagName().equals("term-deposit")){
            deposit = new TermDeposit();
        }else {
            deposit = new DemandDeposit();
        }
        deposit.setCountry(Country.valueOf(depositElement.getAttribute("country")));
        Deposit.Depositor depositor = deposit.getDepositor();
        Element depositorElement = (Element) depositElement.getElementsByTagName("depositor").item(0);
        depositor.setName(getElementTextContent(depositElement, "name"));
        depositor.setAccountID(getElementTextContent(depositElement, "account-id"));
        Double amountOnDeposit = Double.parseDouble(getElementTextContent(depositElement, "amount-on-deposit"));
        depositor.setAmountOnDeposit(amountOnDeposit);
        Double profitability = Double.parseDouble(getElementTextContent(depositElement, "profitability"));
        depositor.setProfitability(profitability);
        Date openingDate = Date.valueOf(getElementTextContent(depositElement, "opening-date"));
        depositor.setOpeningDate(openingDate);
        if (depositElement.getTagName().equals("term-deposit")){
            Integer timeConstraints = Integer.parseInt(getElementTextContent(depositElement, "time-constraints"));
            TermDeposit termDeposit = (TermDeposit) deposit;
            termDeposit.setTimeConstraints(timeConstraints);
        }else {
            Double surrender = Double.parseDouble(getElementTextContent(depositElement, "surrender"));
            DemandDeposit demandDeposit = (DemandDeposit) deposit;
            demandDeposit.setSurrender(surrender);
        }
        deposit.setBankName(depositElement.getAttribute("bank-name"));
        return deposit;
    }
}