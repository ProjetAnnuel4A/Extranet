package com.esgi.extranet.school.controllers;

import com.esgi.extranet.school.entities.ClassmateEntity;
import com.esgi.extranet.school.services.ClassmateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author timotheearnauld
 */
@RestController
@RequestMapping(value="/classmates/")
public class ClassmateController {
    private final ClassmateService classmateService;

    @Autowired
    public ClassmateController(ClassmateService classmateService) {
        this.classmateService = classmateService;
    }

    @RequestMapping(value = "/addClassmate", method = RequestMethod.POST)
    public ClassmateEntity addClassmate(@RequestParam(name = "classmateName") String classmateName){
        return classmateService.addClassmate(classmateName);
    }

    @RequestMapping(value = "/removeClassmate", method = RequestMethod.POST)
    public boolean removeClassmate(@RequestParam(name = "id") Long id){
        return classmateService.removeClassmate(id);
    }
}
