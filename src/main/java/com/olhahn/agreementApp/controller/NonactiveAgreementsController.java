package com.olhahn.agreementApp.controller;

import com.olhahn.agreementApp.model.AgreementEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * agreement
 *
 * @autour Olha Hnatiuk on 5/3/18
 **/

@Controller
public class NonactiveAgreementsController extends AgreementsController {

    /**
     * Creates view for page "Nieaktwne umowy"
     * @param model
     * @return string - name of the view
     */
    @RequestMapping(value="/agreementsNonActive")
    public String activeAgreementsPage(Model model) {
        model.addAttribute("getAgreements", "/getNonActiveAgreements");
        model.addAttribute("typeOfAgreements", "Nieaktywne");
        return  "agreementsNonActive";
    }

    /**
     *
     * @return list of nonactive agreements
     */
    @RequestMapping(value = "/getNonActiveAgreements")
    @ResponseBody
    public List<AgreementEntity> getNonActiveAgreements() {
        return getEntities(false);
    }

}
