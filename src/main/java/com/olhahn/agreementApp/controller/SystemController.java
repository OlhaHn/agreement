package com.olhahn.agreementApp.controller;

import com.olhahn.agreementApp.model.SystemEntity;
import com.olhahn.agreementApp.service.FileReaderService;
import com.olhahn.agreementApp.service.SystemService;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Project: agreement.
 *
 * @author Olha Hnatiuk on 5/3/18
 * Controller for page "Systemy"
 **/
@Controller
public class SystemController {

    /**
     * Service for working with systems.
     */
    @Autowired
    private SystemService systemService;

    /**
     * Setter for field systemService.
     * @param systemServiceIn - new value for the field.
     */
    public void setSystemService(final SystemService systemServiceIn) {
        this.systemService = systemServiceIn;
    }

    /**
     * Service for file upload.
     */
    @Autowired
    private FileReaderService fileReaderService;

    /**
     * Setter for the fileReaderService field.
     * @param fileReaderIn - new value for the field
     */
    public void setFileReader(final FileReaderService fileReaderIn) {
        this.fileReaderService = fileReaderIn;
    }

    /**
     * View resolver.
     * @return view for page "Systemy".
     */
    @RequestMapping(value = "/systemsPage")
    public ModelAndView systemsPage() {
        return new ModelAndView("systemsPage");
    }

    /**
     * Function to delete system, id set as parameter in http request.
     * @param req http request
     * @param resp http response
     * @return true if deleted system, false otherwise
     */
    @RequestMapping(value = "/deleteSystem")
    @ResponseBody
    public boolean deleteSystem(final HttpServletRequest req, final HttpServletResponse resp ) {
        int id = Integer.parseInt(req.getParameter("id"));
        systemService.removeObject(id);
        return true;
    }

    /**
     * Function for updating system.
     * @param req - http request
     * @param resp - http response
     * @return true
     */
    @RequestMapping(value = "/updateSystem")
    @ResponseBody
    public boolean updateSystem(final HttpServletRequest req, final HttpServletResponse resp ) {
        SystemEntity entity = getEntityFromRequest(req, true);
        systemService.updateObject(entity);
        return true;
    }

    /**
     * Function to insert new system into DB.
     * @param req - request
     * @param resp - response
     * @return true
     */
    @RequestMapping(value = "/insertSystem")
    @ResponseBody
    public boolean insertSystem(final HttpServletRequest req, final HttpServletResponse resp ) {
        SystemEntity entity = getEntityFromRequest(req, false);
        systemService.addObject(entity);
        return true;
    }

    /**
     * Function for file uploading.
     * @param file - file to upload
     * @return name of the view
     **/
    @RequestMapping(value = "/systemUpload", method = RequestMethod.POST)
    public String submit(@RequestParam("file") MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (extension != null && (extension.equals("xls") || extension.equals("xlsx"))) {
            loadFromFile(file);
        }
        return "systemsPage";
    }

    /**
     * Function to put data from uploaded file into DB.
     * @param file - uploaded file
     */
    private void loadFromFile(final MultipartFile file) {
        try {
            File javaFile = multipartToFile(file); // create File object
            fileReaderService.readSystemFile(javaFile); // read into DB
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

    /**
     * Creates system entity from data in the request.
     * @param req - request
     * @param shouldHaveId - true if should have id (updated entity)
     * @return entity created from that data
     */
    private SystemEntity getEntityFromRequest(final HttpServletRequest req, final boolean shouldHaveId) {
        SystemEntity systemEntity = new SystemEntity();
        if (shouldHaveId) {
            systemEntity.setId(Integer.parseInt(req.getParameter("id")));
        }
        systemEntity.setName(req.getParameter("name"));
        systemEntity.setDescription(req.getParameter("description"));
        systemEntity.setOwner(Integer.parseInt(req.getParameter("owner")));
        return systemEntity;
    }
}
