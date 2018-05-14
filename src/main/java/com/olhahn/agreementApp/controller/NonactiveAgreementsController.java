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
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Project: agreement.
 *
 * @author Olha Hnatiuk on 5/3/18
 * Controller for page "Nieaktwne umowy"
 **/
@Controller
public class NonactiveAgreementsController extends AgreementsController {

    /**
     * Creates view for page "Nieaktwne umowy".
     * @param model - model of the page
     * @return string - name of the view
     */
    @RequestMapping(value = "/agreementsNonActive")
    public String nonActiveAgreementsPage(final Model model) {
        model.addAttribute("getAgreements", "/getNonActiveAgreements");
        model.addAttribute("typeOfAgreements", "Nieaktywne");
        model.addAttribute("fileUploadAdress", "/fileUploadAgreementNonactive");
        return  "agreementsNonActive";
    }

    /**
     * Function to get list of nonactive agreements.
     * @return list of nonactive agreements
     */
    @RequestMapping(value = "/getNonActiveAgreements")
    @ResponseBody
    public List<AgreementEntity> getNonActiveAgreements() {
        return getEntities(false);
    }

    /**
     * Function for file uploading.
     * @param file - file to upload
     * @param model - model of the page
     * @return name of the view
     **/
    @RequestMapping(value = "/fileUploadAgreementNonactive", method = RequestMethod.POST)
    public String submit(@RequestParam("file") MultipartFile file, final Model model) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (extension != null && (extension.equals("xls") || extension.equals("xlsx"))) {
            loadFromFile(file);
        }
        return nonActiveAgreementsPage(model);
    }

}
