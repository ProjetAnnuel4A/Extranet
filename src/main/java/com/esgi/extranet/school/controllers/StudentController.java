package com.esgi.extranet.school.controllers;

import com.esgi.extranet.school.entities.StudentEntity;
import com.esgi.extranet.school.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author timotheearnauld
 */
@RestController
@RequestMapping(name="/students/")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(name = "/addStudent", method = RequestMethod.POST)
    public StudentEntity addStudent(@RequestParam(name = "firstname") String firstname,
                                    @RequestParam(name = "lastname") String lastname){
        return studentService.addStudent(firstname, lastname);
    }

    @RequestMapping(name = "/removeStudent", method = RequestMethod.POST)
    public boolean removeStudent(@RequestParam(name = "id") Long id){
        return studentService.removeStudent(id);
    }
}
