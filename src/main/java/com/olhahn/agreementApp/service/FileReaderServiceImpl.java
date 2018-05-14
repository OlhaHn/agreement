package com.olhahn.agreementApp.service;

import com.olhahn.agreementApp.model.AgreementEntity;
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
public class FileReaderServiceImpl implements FileReaderService {

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
    public void setAgreementService(final AgreementService agreementServiceIn) {
        this.agreementService = agreementServiceIn;
    }

    /**
     * Size of system indexes array.
     */
    private static final int SYSTEM_ARRAY_SIZE = 3;

    /**
     * Array of column indexes in file.
     * [0] = name
     * [1] = description
     * [2] = owner
     */
    private final int[] systemIn = new int[SYSTEM_ARRAY_SIZE];

    /**
     * Size of agreement index array.
     */
    private static final int AGREEMENT_ARRAY_SIZE = 10;

    /**
     * Array of column indexes in file.
     * [0] - order_number, [1] - date_from, [2] - to_date
     * [3] - amount, [4] - amount_type, [5] - amount_period
     * [6] - authorization_percent, [7] - active, [8] - request
     * [9] - system
     */
    private final int[] agreementIn = new int[AGREEMENT_ARRAY_SIZE];

    /**
     * List of possible columns in agreement's table.
     */
    private final String[] agreementColumns = {"order_number", "date_from",
            "to_date", "amount", "amount_type", "amount_period",
            "authorization_percent", "active",
            "request", "system"};
    /**
     * Function to read from excel
     * file and insert into system table.
     * @param file - file to read from
     * @throws IOException - trying to open file
     * @throws InvalidFormatException trying to open excel file
     */
    public void readSystemFile(final File file)
            throws IOException, InvalidFormatException {
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
     * @param file - file to read from
     * @throws IOException - when file from input does not exist
     * @throws InvalidFormatException - when file from input is
     * not in proper format
     */
    public void readAgreementFile(final File file)
            throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(file);
        for (Sheet sheet: workbook) {
            // if all necessary columns are present
            if (setIndexesAgreement(sheet)) {
                // iterate through all rows
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    try {
                        Row row = sheet.getRow(i);
                        if (allNotNullAgreement(row)) {
                            /*
                             *
                             * [0] - order_number, [1] - date_from, [2] - to_date
                             * [3] - amount, [4] - amount_type, [5] - amount_period
                             * [6] - authorization_percent, [7] - active, [8] - request
                             * [9] - system
                             *
                             */
                            AgreementEntity agreementEntity = new AgreementEntity();
                            agreementEntity.setNumber((int) row.getCell(agreementIn[0]).getNumericCellValue());
                            agreementEntity.setDateFrom(row.getCell(agreementIn[1]).getDateCellValue());
                            agreementEntity.setDateTo(row.getCell(agreementIn[2]).getDateCellValue());
                            agreementEntity.setAmount((float) row.getCell(agreementIn[3]).getNumericCellValue());
                            agreementEntity.setAmountType(row.getCell(agreementIn[4]).getStringCellValue());
                            agreementEntity.setPeriod(row.getCell(agreementIn[5]).getStringCellValue());
                            agreementEntity.setActive(Boolean.valueOf(row.getCell(agreementIn[7]).getStringCellValue()));
                            agreementEntity.setRequest((int) row.getCell(agreementIn[8]).getNumericCellValue());
                            String systemName = row.getCell(agreementIn[9]).getStringCellValue();
                            SystemEntity systemEntity = systemService.getSystemByName(systemName);
                            if (systemEntity == null) {
                                continue;
                            }
                            agreementEntity.setSystem(systemEntity);
                            Cell percentCell = row.getCell(agreementIn[6]);
                            if (percentCell != null) {
                                agreementEntity.setPercent((float) percentCell.getNumericCellValue());
                            }
                            agreementService.addObject(agreementEntity);
                        }
                    } catch (IllegalStateException ignored) {
                    }
                }
            }
        }
        workbook.close();
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

    /**
     * Sets indexes of columns in file into agreementIn array.
     * @param sheet - sheet from each indexes should be set
     * @return if all needed columns are in sheet
     */
    private boolean setIndexesAgreement(final Sheet sheet) {
        // Set all indexes to undefined
        for (int i = 0; i < AGREEMENT_ARRAY_SIZE; i++) {
            agreementIn[i] = -1;
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
                    for (int j = 0; j < AGREEMENT_ARRAY_SIZE; j++) {
                        if (name.equals(agreementColumns[j])) {
                            if (agreementIn[j] == -1) {
                                agreementIn[j] = i;
                            } else {
                                return false;
                            }
                        }
                    }
                } catch (IllegalStateException ignored) {
                }
            }
        }
        for (int i = 0; i < AGREEMENT_ARRAY_SIZE; i++) {
            if (i != 6 && agreementIn[i] == -1) { // only authorization_percent (6) can be null
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if all necessary fields have values in raw from argument.
     * @param row - row to check
     * @return true if all necessary fields have values
     */
    private boolean allNotNullAgreement(final Row row) {
        for (int j = 0; j < AGREEMENT_ARRAY_SIZE; j++) {
            //if field is not percent field (it allows null)
            // and is null - return false;
            if (j != 6 && row.getCell(agreementIn[j]) == null) {
                return false;
            }
        }
        return true;
    }
}
