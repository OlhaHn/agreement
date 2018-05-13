package com.olhahn.agreementApp.controller;

import com.olhahn.agreementApp.model.AgreementEntity;
import com.olhahn.agreementApp.model.SystemEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Project: agreement
 *
 * @author Olha Hnatiuk on 5/3/18
 **/

/**
 * Controller class for page "Wszystkie umowy"
 */
@Controller
public class AllAgreementsController extends AgreementsController {

    /**
     * Mapping view of page "Wszystkie umowy"
     * @return view of "Wszystkie umowy"
     */
    @RequestMapping(value = "/agreementsAll", method = RequestMethod.GET)
    public String activeAgreementsPage(Model model) {
        model.addAttribute("getAgreements", "/getAllAgreements");
        model.addAttribute("typeOfAgreements", "Wszystkie");
        return  "agreementsAll";
    }

    /**
     *
     * @return list of all agreements
     */
    @RequestMapping(value = "/getAllAgreements")
    @ResponseBody
    public List<AgreementEntity> getAllAgreements() {
        return getEntities(null);
    }

    /**
     * Answer of ajax call for adding new agreement (data given as parameter of request)
     * @param req request
     * @param resp response
     * @return true if was added, false otherwise
     */
    @RequestMapping(value = "/insertAgreement", method = RequestMethod.POST)
    @ResponseBody
    public boolean insertAgreement(HttpServletRequest req, HttpServletResponse resp ) {
        return addEntity(req, resp);
    }

    /**
     * Answer for ajax call for deleting an agreement (id given as parameter)
     * @param req request
     * @param resp response
     * @return true if deleted, false otherwise
     */
    @RequestMapping(value = "/deleteActive", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteAgreement(HttpServletRequest req, HttpServletResponse resp ) {
        return removeAgreement(req, resp);
    }

    /**
     * Updates agreement, new data and id in request properties
     * @param req request
     * @param resp response
     * @return true if updated entity, false if failed
     */
    @RequestMapping(value = "/updateAgreement", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateAgreement(HttpServletRequest req, HttpServletResponse resp ) {
        return updateEnitity(req, resp);
    }

    /**
     *
     * @param req request
     * @param resp response
     * @return list of all systems
     */
    @RequestMapping(value="/getSystems", method=RequestMethod.POST)
    @ResponseBody
    public List<SystemEntity> getSystems(HttpServletRequest req, HttpServletResponse resp ) {
        return getAllSystems();
    }

}
