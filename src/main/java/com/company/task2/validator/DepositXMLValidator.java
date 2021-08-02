package com.company.task2.validator;

import com.company.task2.exception.DepositException;
import com.company.task2.handler.DepositErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class DepositXMLValidator {
    private static final Logger logger = LogManager.getLogger();

    private DepositXMLValidator() {
    }

    public static boolean validateXMLFile(String fileName, String schemaName) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.setErrorHandler(new DepositErrorHandler());
            validator.validate(source);
        } catch (SAXException | IOException e) {
            logger.error(fileName + " is not correct or valid");
            return false;
        }
        logger.info(fileName + " is valid");
        return true;
    }
}
