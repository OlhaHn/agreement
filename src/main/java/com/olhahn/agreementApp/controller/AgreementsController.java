package com.olhahn.agreementApp.controller;

import com.olhahn.agreementApp.model.AgreementEntity;
import com.olhahn.agreementApp.model.SystemEntity;
import com.olhahn.agreementApp.service.AgreementService;
import com.olhahn.agreementApp.service.FileReaderService;
import com.olhahn.agreementApp.service.SystemService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Project: agreement.
 *
 * @author Olha Hnatiuk on 5/2/18
 * Base controller class for all agreements pages, gives common functionality
 **/
@Controller
public class AgreementsController {


    /**
     * Service for working with agreements.
     */
    @Autowired
    private AgreementService agreementService;

    /**
     * Service for working with systems.
     */
    @Autowired
    private SystemService systemService;

    /**
     * Service for uploading files.
     */
    @Autowired
    private FileReaderService fileReaderService;

    /**
     * Setter for agreementService filed.
     * @param agreementServiceIn new value for agreementService filed
     */
    public void setAgreementService(final AgreementService agreementServiceIn) {
        this.agreementService = agreementServiceIn;
    }

    /**
     * Setter for field systemService.
     * @param systemService - new value for the field.
     */
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    /**
     * Setter for field fileReaderService.
     * @param fileReaderService - new value for the field
     */
    public void setFileReaderService(FileReaderService fileReaderService) {
        this.fileReaderService = fileReaderService;
    }

    /**
     * Removes agreement from db.
     * @param req request (id of agreement to delete in request parameter)
     * @param resp response
     * @return true if success, false otherwise
     */
    boolean removeAgreement(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        if (id != null) {
            int idValue = Integer.parseInt(id);
            agreementService.removeObject(idValue);
            return true;
        }
        return true;
    }

    /**
     * Adds entity into db.
     * @param req Http request (data of entity to add into req parameters)
     * @param resp Http response
     * @return true if success, false otherwise
     */
    boolean addEntity(HttpServletRequest req, HttpServletResponse resp) {
        try {
            AgreementEntity agreementEntity = getEntityFromRequest(req, false);
            agreementService.addObject(agreementEntity);
            return true;
        } catch (ParseException ignore) {
        }
        return false;
    }

    /**
     * Updates entity from request.
     * @param req http request
     * @param resp http response
     * @return true if updated, false if failed
     */
    boolean updateEnitity(HttpServletRequest req, HttpServletResponse resp) {
        try {
            AgreementEntity agreementEntity = getEntityFromRequest(req, true);
            agreementService.updateObject(agreementEntity);
            return true;
        } catch (ParseException ignore) {
        }
        return false;
    }

    /**
     * Parses input got from page.
     * @param req http request
     * @param shouldHaveId true if trying to update entity, false if trying to create
     * @return agreement entity made from input data, null if error
     * @throws ParseException - improper input
     */
    private AgreementEntity getEntityFromRequest(HttpServletRequest req, boolean shouldHaveId) throws ParseException {
        AgreementEntity resultEntity = new AgreementEntity();
        if (shouldHaveId) {
            resultEntity.setId(Integer.parseInt(req.getParameter("id"))); // error should not appear, user does not change id
        }

        resultEntity.setRequest(Integer.parseInt(req.getParameter("request")));
        resultEntity.setPeriod(req.getParameter("period"));
        resultEntity.setPercent(Float.parseFloat(req.getParameter("percent")));
        resultEntity.setNumber(Integer.parseInt(req.getParameter("number")));
        resultEntity.setAmountType(req.getParameter("amountType"));
        resultEntity.setAmount(Float.parseFloat(req.getParameter("amount")));
        resultEntity.setActive(req.getParameter("active").equals("on"));

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        resultEntity.setDateTo(format.parse(req.getParameter("dateTo")));
        resultEntity.setDateFrom(format.parse(req.getParameter("dateFrom")));

        SystemEntity system = systemService.getSystemByName(req.getParameter("system"));
        resultEntity.setSystem(system);

        return resultEntity;
    }

    /**
     *
     * @param isActive null if should return all,
     *                 true if should return active,
     *                 false if should return nonactive
     * @return list of agreements
     */
    List<AgreementEntity> getEntities(Boolean isActive) {
        if (isActive == null) {
            return agreementService.getAll();
        } else if (isActive) {
            return agreementService.getActive();
        } else {
            return agreementService.getNonactive();
        }
    }

    /**
     * Function to get list of all systems.
     * @return list of all systems
     */
    List<SystemEntity> getAllSystems() {
        return systemService.getAll();
    }

    /**
     * Function to put data from uploaded file into DB.
     * @param file - uploaded file
     */
    void loadFromFile(MultipartFile file) {
        try {
            File javaFile = multipartToFile(file); // create File object
            fileReaderService.readAgreementFile(javaFile); // read into DB
        } catch (InvalidFormatException ignored) {
        } catch (IllegalStateException ignored) {
        } catch (IOException ignored) { // siply do not react on
        }
    }

    /**
     * Function to transform MultipartFile into File.
     * @param multipart - MultipartFile object
     * @return - object of type File created from the input file.
     * @throws IllegalStateException - problems with input
     * @throws IOException - problems with input
     */
    private File multipartToFile(final MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File(multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }
}
