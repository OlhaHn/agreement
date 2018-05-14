package com.olhahn.agreementApp.service;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;

/**
 * Project: agreement.
 * @author Olha Hnatiuk on 5/13/18
 * Interface for reading exel files
 **/
public interface FileReaderService {

    /**
     * Function to read from excel
     * file and insert into system table.
     * @param input - name of the file to read from
     * @throws IOException if file does not exist
     * @throws InvalidFormatException if file in in improper format
     */
    void readSystemFile(File input) throws IOException,
            InvalidFormatException;

    /**
     * Function to read from excel
     * file and insert into agreement table.
     * @param input - name of the file to read from
     * @throws IOException if file does not exist
     * @throws InvalidFormatException if file in in improper format
     */
    void readAgreementFile(File input) throws IOException,
            InvalidFormatException;
}
