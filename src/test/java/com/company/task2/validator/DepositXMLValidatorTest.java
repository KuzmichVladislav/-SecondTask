package com.company.task2.validator;

import com.company.task2.exception.DepositException;
import com.company.task2.main.Main;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URL;

public class DepositXMLValidatorTest {
    String FILE_PATH = "data_xml/deposits.xml";
    String SCHEMA_PATH = "data_xml/deposits.xsd";
    String WRONG_FILE_PATH = "data_xml/wrong_file.xml";

    ClassLoader classLoader = Main.class.getClassLoader();

    String absolutePath(String path) {
        URL resource = classLoader.getResource(path);
        assert resource != null;
        return new File(resource.getFile()).getAbsolutePath();
    }

    @Test
    public void testValidateXMLFile() throws DepositException {
        boolean result = DepositXMLValidator.validateXMLFile(absolutePath(FILE_PATH),absolutePath(SCHEMA_PATH));
        Assert.assertTrue(result);
    }

    @Test
    public void testValidateWrongXMLFile() throws DepositException {
        boolean result = DepositXMLValidator.validateXMLFile(absolutePath(WRONG_FILE_PATH),absolutePath(SCHEMA_PATH));
        Assert.assertFalse(result);
    }
}