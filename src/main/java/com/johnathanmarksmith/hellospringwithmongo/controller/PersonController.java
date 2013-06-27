package com.johnathanmarksmith.hellospringwithmongo.controller;

import com.johnathanmarksmith.hellospringwithmongo.model.Person;
import com.johnathanmarksmith.hellospringwithmongo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Date:   6/27/13 / 11:45 AM
 * Author: Johnathan Mark Smith
 * Email:  john@johnathanmarksmith.com
 * <p/>
 * Comments:
 *
 *  this is the main controller for the project
 *
 */


@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
     *
     * this will display a list
     *
     */
    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public String getPersonList(ModelMap model) {
        model.addAttribute("personList", personService.listPerson());
        return "output";
    }

    /**
     *
     * this is to save a person
     *
     */
    @RequestMapping(value = "/person/save", method = RequestMethod.POST)
    public View createPerson(@ModelAttribute Person person, ModelMap model) {
        if (StringUtils.hasText(person.getId())) {
            personService.updatePerson(person);
        } else {
            personService.addPerson(person);
        }
        return new RedirectView("/HelloSpringWithMongoDB/person");
    }

    /**
     *
     * this is to delete a person
     *
     */
    @RequestMapping(value = "/person/delete", method = RequestMethod.GET)
    public View deletePerson(@ModelAttribute Person person, ModelMap model) {
        personService.deletePerson(person);
        return new RedirectView("/HelloSpringWithMongoDB/person");
    }
}
