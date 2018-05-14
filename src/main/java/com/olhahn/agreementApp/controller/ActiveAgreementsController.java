package com.olhahn.agreementApp.controller;

import com.olhahn.agreementApp.model.AgreementEntity;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Project: agreement.
 *
 * @author Olha Hnatiuk on 5/1/18
 * Controller for page "Aktywne umowy"
 **/
@Controller
public class ActiveAgreementsController extends AgreementsController {

    /**
     * Mapping view of page "Aktywne umowy".
     * @param model  - model of the page
     * @return view of "Aktywne umowy"
     */
    @RequestMapping(value = "/agreementsActive", method = RequestMethod.GET)
    public String activeAgreementsPage(final Model model) {
        model.addAttribute("getAgreements", "/getActiveAgreements");
        model.addAttribute("typeOfAgreements", "Aktywne");
        model.addAttribute("fileUploadAdress", "/fileUploadAgreementActive");
        return  "agreementsActive";
    }

    /**
     * Function for file uploading.
     * @param file - file to upload
     * @param model - model of the page
     * @return name of the view
     **/
    @RequestMapping(value = "/fileUploadAgreementActive", method = RequestMethod.POST)
    public String submit(@RequestParam("file") MultipartFile file, final Model model) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (extension != null && (extension.equals("xls") || extension.equals("xlsx"))) {
            loadFromFile(file);
        }
        return activeAgreementsPage(model);
    }

    /**
     * Answer for Ajax call for fill the table of active agreements.
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
