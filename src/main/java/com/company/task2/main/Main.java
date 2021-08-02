package com.company.task2.main;

import com.company.task2.parser.AbstractDepositsBuilder;
import com.company.task2.parser.DepositBuilderFactory;

import java.io.File;
import java.net.URL;

public class Main {
    private static final String RELATIVE_FILE_PATH = "data_xml/deposits.xml";

    public static void main(String[] args) {
        ClassLoader classLoader = Main.class.getClassLoader();
        URL resource = classLoader.getResource(RELATIVE_FILE_PATH);
        assert resource != null;
        String absolutePath = new File(resource.getFile()).getAbsolutePath();

        String type = "dom";
        AbstractDepositsBuilder builder = DepositBuilderFactory.createDepositsBuilder(type);
        builder.buildSetDeposits(absolutePath);
        System.out.println(builder.getDeposits());
    }
}
