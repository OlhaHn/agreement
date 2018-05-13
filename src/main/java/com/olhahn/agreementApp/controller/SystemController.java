package com.olhahn.agreementApp.controller;

import com.olhahn.agreementApp.model.SystemEntity;
import com.olhahn.agreementApp.service.FileReader;
import com.olhahn.agreementApp.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * agreement
 *
 * @autour Olha Hnatiuk on 5/3/18
 **/
@Controller
public class SystemController {

    @Autowired
    SystemService systemService;

    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    @Autowired
    FileReader fileReader;

    public void setFileReader(FileReader fileReaderIn) {
        this.fileReader = fileReaderIn;
    }

    /**
     *
     * @return view for page "Systemy"
     */
    @RequestMapping(value="/systemsPage")
    public ModelAndView activeAgreementsPage() throws Exception{
        try {
            fileReader.readSystemFile("/home/olga/3_year/work/agreement/Skoroszyt.xlsx");
        }catch (Exception e){
            throw e;
        }
        return new ModelAndView("systemsPage");
    }

    /**
     * Function to delete system, id set as parameter in http request
     * @param req http request
     * @param resp http response
     * @return true if deleted system, false otherwise
     */
    @RequestMapping(value = "/deleteSystem")
    @ResponseBody
    public boolean deleteSystem(HttpServletRequest req, HttpServletResponse resp ) {
        int id = Integer.parseInt(req.getParameter("id"));
        systemService.removeObject(id);
        return true;
    }

    @RequestMapping(value = "/updateSystem")
    @ResponseBody
    public boolean updateSystem(HttpServletRequest req, HttpServletResponse resp ) {
        SystemEntity entity = getEntityFromRequest(req, true);
        systemService.updateObject(entity);
        return true;
    }

    @RequestMapping(value = "/insertSystem")
    @ResponseBody
    public boolean insertSystem(HttpServletRequest req, HttpServletResponse resp ) {
        SystemEntity entity = getEntityFromRequest(req, false);
        systemService.addObject(entity);
        return true;
    }

    private SystemEntity getEntityFromRequest(HttpServletRequest req, boolean shouldHaveId) {
        SystemEntity systemEntity = new SystemEntity();
        if( shouldHaveId ) {
            systemEntity.setId(Integer.parseInt(req.getParameter("id")));
        }
        systemEntity.setName(req.getParameter("name"));
        systemEntity.setDescription(req.getParameter("description"));
        systemEntity.setOwner(Integer.parseInt(req.getParameter("owner")));
        return systemEntity;
    }
}
