package com.olhahn.agreementApp.service;

import com.olhahn.agreementApp.model.SystemEntity;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Project: agreement.
 * @author Olha Hnatiuk on 5/13/18
 * Implementation of FileReader interfce
 **/
@Service
public class FileReaderImpl implements FileReader {

    /**
     * System service for working with DB.
     */
    @Autowired
    private SystemService systemService;

    /**
     * Setter for the systemService field.
     * @param systemServiceIn new value for systemService field.
     */
    public void setSystemService(final SystemService systemServiceIn) {
        this.systemService = systemServiceIn;
    }

    /**
     * Agreement service for working with DB.
     */
    @Autowired
    private AgreementService agreementService;

    /**
     * Setter for agreementService field.
     * @param agreementServiceIn - new value for the field
     */
    public void setAgreementService(AgreementService agreementServiceIn) {
        this.agreementService = agreementServiceIn;
    }

    /**
     * Size of system indexes array.
     */
    private static final int SYSTEM_ARRAY_SIZE = 3;

    /**
     * Array of column indexes in file to read from into database.
     * [1] = name
     * [2] = description
     * [3] = owner
     */
    private final int[] systemIn = new int[SYSTEM_ARRAY_SIZE];

    

    /**
     * Function to read from excel
     * file and insert into system table.
     * @param input - name of the file to read from
     * @throws IOException - trying to open file
     * @throws InvalidFormatException trying to open excel file
     */
    public void readSystemFile(final String input) throws IOException, InvalidFormatException {
        File file = new File(input);
        Boolean exist = file.exists();
        Workbook workbook = WorkbookFactory.create(file);
        for (Sheet sheet: workbook) {
            // if all necessary columns are present
            if (setIndexesSystem(sheet)) {
                // for each row try to create new entity
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (allNotNullSystem(row)) {
                        try {
                            SystemEntity system = new SystemEntity();
                            // set name
                            system.setName(row.getCell(systemIn[0]).getStringCellValue());
                            // set owner

                            int owner = (int) row.getCell(systemIn[2]).getNumericCellValue();
                            system.setOwner(owner);
                            // set description if was in file
                            if (systemIn[1] != -1) {
                                Cell cell = row.getCell(systemIn[1]);
                                if (cell != null) {
                                    system.setDescription(cell.getStringCellValue());
                                }
                            }
                            // try to add to the DB
                            systemService.addObject(system);
                        } catch (IllegalStateException ignored) {
                        }
                    }
                }
            }
        }
        workbook.close();
    }

    /**
     * Checks if all not null fields in row are not empty.
     * @param row - row to check
     * @return true if all fields are not empty
     */
    private boolean allNotNullSystem(final Row row) {
        return row.getCell(systemIn[0]) != null
                && row.getCell(systemIn[2]) != null;
    }

    /**
     * Function to read from excel
     * file and insert into agreement table.
     * @param input - name of the file to read from
     */
    public void readAgreementFile(final String input) {

    }

    /**
     * Function to set column indexes to read from.
     * @param sheet - sheet to work with
     * @return true if all not null fields are
     * described as columns in the input file
     */
    private boolean setIndexesSystem(final Sheet sheet) {
        for (int i = 0; i < SYSTEM_ARRAY_SIZE; i++) {
            systemIn[i] = -1;
        }
        Row row = sheet.getRow(0);
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null) {
                try {
                    String name = cell.getStringCellValue();
                    /*
                     * If name = name of one of the columns in DB,
                     * then set the index, if index was already set
                     * - return false, is not unequivocal
                     * */
                    if (name.equals("name")) {
                        if (systemIn[0] != -1) {
                            return false;
                        }
                        systemIn[0] = i;
                    } else if (name.equals("description")) {
                        if (systemIn[1] != -1) {
                            return false;
                        }
                        systemIn[1] = i;
                    } else if (name.equals("owner")) {
                        if (systemIn[2] != -1) {
                            return false;
                        }
                        systemIn[2] = i;
                    }
                } catch (IllegalStateException ignored) {
                }
            }
        }
        return systemIn[0] != -1 && systemIn[2] != -1;
    }
}
