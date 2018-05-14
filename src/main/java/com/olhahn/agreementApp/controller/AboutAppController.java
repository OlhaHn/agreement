package com.olhahn.agreementApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Project: agreement.
 *
 * @author Olha Hnatiuk on 5/3/18
 * Controller for page "O aplikacji"
 **/
@Controller
public class AboutAppController {

    /**
     * Mapping for view of the page.
     * @return view of the page
     */
    @RequestMapping(value = "/aboutApp")
    public ModelAndView activeAgreementsPage() {
        return new ModelAndView("aboutApp");
    }

}
