package com.olhahn.agreementApp.controller;

import com.olhahn.agreementApp.model.AgreementEntity;
import com.olhahn.agreementApp.model.SystemEntity;
import com.olhahn.agreementApp.service.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;


/**
 * Project: agreement
 *
 * @author Olha Hnatiuk on 5/1/18
 **/

/**
 * Controller for page "Aktywne umowy"
 */
@Controller
public class ActiveAgreementsController extends AgreementsController{

    private static final Logger logger = LoggerFactory.getLogger(ActiveAgreementsController.class);

    @Autowired
    private FileReader fileReader;

    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    /**
     * Mapping view of page "Aktywne umowy"
     * @return view of "Aktywne umowy"
     */
    @RequestMapping(value = "/agreementsActive", method = RequestMethod.GET)
    public String activeAgreementsPage(Model model) {
        try {
            fileReader.readAgreementFile("/home/olga/3_year/work/agreement/Skoroszyt1.xlsx");
        } catch (Exception e){}
        model.addAttribute("getAgreements", "/getActiveAgreements");
        model.addAttribute("typeOfAgreements", "Aktywne");
        return  "agreementsActive";
    }

    /**
     * Answer for Ajax call for fill the table of active agreements
     * @param req request
     * @param resp response
     * @return list of active agreements
     */
    @RequestMapping(value = "/getActiveAgreements", method = RequestMethod.POST)
    @ResponseBody
    public List<AgreementEntity> getAllClients(HttpServletRequest req, HttpServletResponse resp) {
        return getEntities(true);
    }


}
