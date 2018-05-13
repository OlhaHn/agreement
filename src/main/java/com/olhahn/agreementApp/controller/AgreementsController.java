package com.olhahn.agreementApp.controller;

import com.olhahn.agreementApp.model.AgreementEntity;
import com.olhahn.agreementApp.model.SystemEntity;
import com.olhahn.agreementApp.service.AgreementService;
import com.olhahn.agreementApp.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Project: agreement
 *
 * @author Olha Hnatiuk on 5/2/18
 **/

/**
 * Base controller class for all agreements pages, gives common functionality
 */
@Controller
public class AgreementsController {


    private static final Logger logger = LoggerFactory.getLogger(AgreementsController.class);

    @Autowired
    private AgreementService agreementService;

    @Autowired
    private SystemService systemService;

    /**
     * setter
     * @param agreementService
     */
    public void setAgreementService(AgreementService agreementService) {
        this.agreementService = agreementService;
    }

    /**
     * setter
     * @param systemService
     */
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    /**
     * removes agreement from db
     * @param req request (id of agreement to delete in request parameter)
     * @param resp response
     * @return true if success, false otherwise
     */
    protected boolean removeAgreement(HttpServletRequest req, HttpServletResponse resp ) {
        String id = req.getParameter("id");
        if(id!=null){
            int id_value = Integer.parseInt(id);
            agreementService.removeObject(id_value);
            return true;
        }
        return true;
    }

    /**
     * Adds entity into db
     * @param req Http request (data of entity to add into req parameters)
     * @param resp Http response
     * @return true if success, false otherwise
     */
    protected boolean addEntity(HttpServletRequest req, HttpServletResponse resp) {
        try {
            AgreementEntity agreementEntity = getEntityFromRequest(req, false);
            agreementService.addObject(agreementEntity);
            return true;
        } catch (ParseException e){
        }
        return false;
    }

    /**
     * Updates entity from request
     * @param req http request
     * @param resp http response
     * @return true if updated, false if failed
     */
    protected boolean updateEnitity(HttpServletRequest req, HttpServletResponse resp) {
        try {
            AgreementEntity agreementEntity = getEntityFromRequest(req, true);
            agreementService.updateObject(agreementEntity);
            return true;
        } catch (ParseException e){
        }
        return false;
    }

    /**
     * Parses input got from page
     * @param req http request
     * @param shouldHaveId true if trying to update entity, false if trying to create
     * @return agreement entity made from input data, null if error
     */
    private AgreementEntity getEntityFromRequest(HttpServletRequest req, boolean shouldHaveId) throws ParseException {
        AgreementEntity resultEntity = new AgreementEntity();
        if(shouldHaveId){
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
    protected List<AgreementEntity> getEntities(Boolean isActive) {
        if(isActive==null){
            return agreementService.getAll();
        }
        else if(isActive.booleanValue()){
            return agreementService.getActive();
        }else{
            return agreementService.getNonactive();
        }
    }

    /**
     *
     * @return list of all systems
     */
    protected List<SystemEntity> getAllSystems() {
        return systemService.getAll();
    }
}
