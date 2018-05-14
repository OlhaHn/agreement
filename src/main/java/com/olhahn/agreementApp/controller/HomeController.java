package com.olhahn.agreementApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Project: agreement.
 *
 * @author Olha Hnatiuk on 5/1/18
 * Controller class for home page
 */
@Controller
public class HomeController {


    /**
     * Sets view for home page.
     * @return view
     */
    @RequestMapping(value = "/")
    public ModelAndView activeAgreementsPage() {
        return new ModelAndView("homePage");
    }

}
