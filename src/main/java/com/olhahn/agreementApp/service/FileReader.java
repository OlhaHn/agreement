package com.olhahn.agreementApp.service;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

/**
 * Project: agreement.
 * @author Olha Hnatiuk on 5/13/18
 * Interface for reading exel files
 **/
public interface FileReader {

    /**
     * Function to read from exel
     * file and insert into system table.
     * @param input - name of the file to read from
     */
    void readSystemFile(String input) throws IOException, InvalidFormatException;

    /**
     * Function to read from exel
     * file and insert into agreement table.
     * @param input - name of the file to read from
     */
    void readAgreementFile(String input);
}
