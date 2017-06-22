package com.esgi.extranet.administration.controllers;

import com.esgi.extranet.administration.entities.TeacherEntity;
import com.esgi.extranet.administration.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author timotheearnauld
 */
@RestController
@RequestMapping(value = "/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @RequestMapping(value = "/addTeacher", method = RequestMethod.POST)
    public TeacherEntity addTeacher(@RequestParam("firstname") String firstname,
                                    @RequestParam("lastname") String lastname){
        return teacherService.addTeacher(firstname, lastname);
    }

    @RequestMapping(value = "/removeTeacher", method = RequestMethod.POST)
    public boolean removeTeacher(@RequestParam("id") Long id){
        return teacherService.removeTeacher(id);
    }

}
