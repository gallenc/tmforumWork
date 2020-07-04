/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opennms.tmforum.tmf656.web;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.opennms.tmforum.tmf656.simulator.dao.ServiceProblemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author gallenc
 */
@Controller
@RequestMapping("/")
public class ViewController {
    
    private static final Logger LOG = LoggerFactory.getLogger(ViewController.class);


    {
        LOG.debug("ViewController created");
    }

    // This serviceFacade object is injected by Spring
    @Autowired(required = true)
    private ServiceProblemRepository serviceProblemRepository = null;


//    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
//    public String home(Model model,
//            @RequestParam(value = "symbol", required = false, defaultValue = "") String symbol,
//            @RequestParam(value = "synonymSymbol", required = false, defaultValue = "") String synonymSymbol,
//            @RequestParam(value = "scientificNamewithAuthor", required = false, defaultValue = "") String scientificNamewithAuthor,
//            @RequestParam(value = "commonName", required = false, defaultValue = "") String commonName,
//            @RequestParam(value = "family", required = false, defaultValue = "") String family,
//            @RequestParam(value = "page", required = false, defaultValue = "1") String page ) {
//
//        LOG.debug("home called");
//        if (serviceFacade == null) {
//            throw new RuntimeException("serviceFacade==null and has not been initialised");
//        }
//
//         LOG.debug("getting all families");
//        List<String> familiesList = serviceFacade.getAllFamilies();
//        model.addAttribute("familiesList", familiesList);
//        LOG.debug("getting find like ");
//
//        List<Flower> flowerList = null;
//
//        Flower searchFlower = new Flower();
//        searchFlower.setCommonName(commonName);
//        searchFlower.setSymbol(symbol);
//        searchFlower.setSynonymSymbol(synonymSymbol);
//        searchFlower.setFamily(family);
//        searchFlower.setScientificNamewithAuthor(scientificNamewithAuthor);
//
//        LOG.debug("getting find like "+searchFlower);
//        // get initial flowerlist
//        flowerList = serviceFacade.findLike(searchFlower);
//        LOG.debug("found flowers:+"+flowerList.size());
//         model.addAttribute("resultSize", flowerList.size());
//
//        // sort into pages and select page
//        List<List<Flower>> flowerPages = new ArrayList();
//        List<Flower> pagelist = new ArrayList();
//        for (int i = 0; i < flowerList.size(); i++) {
//            if ((i % 200) == 0) {
//                //LOG.debug("new arraylist i="+i);
//                pagelist = new ArrayList();
//                flowerPages.add(pagelist);
//            }
//            Flower f = flowerList.get(i);
//            pagelist.add(f);
//            //LOG.debug("pagelist i="+i);
//        }
//        
//        LOG.debug("number of pages: "+flowerPages.size());
//        model.addAttribute("numpages", flowerPages.size());
//        
//        Integer pageNo = Integer.parseInt( page );
//        model.addAttribute("page", pageNo);
//        flowerList  = flowerPages.get(pageNo-1);
//        model.addAttribute("flowerList", flowerList);
//        
//        model.addAttribute("family", family);
//        model.addAttribute("symbol", symbol);
//        model.addAttribute("synonymSymbol", synonymSymbol);
//        model.addAttribute("scientificNamewithAuthor", scientificNamewithAuthor);
//        model.addAttribute("commonName", commonName);
//
//        return "home";
//    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(Model model) {
        return "home";
    }
    
    @RequestMapping(value = {"/swaggerUI"}, method = RequestMethod.GET)
    public String swaggerUI(Model model) {
        return "swaggerUI";
    }

    @RequestMapping(value = {"/about"}, method = RequestMethod.GET)
    public String about(Model model) {
        return "about";
    }

    @RequestMapping(value = {"/contact"}, method = RequestMethod.GET)
    public String contact(Model model) {
        return "contact";
    }

}
