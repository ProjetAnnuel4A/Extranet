package com.esgi.extranet.school.controllers;

import com.esgi.extranet.school.entities.StudentEntity;
import com.esgi.extranet.school.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author timotheearnauld
 */
@RestController
@RequestMapping(value="/students/")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public List<StudentEntity> getAll(){
        return studentService.getAll();
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public StudentEntity addStudent(@RequestParam(name = "firstname") String firstname,
                                    @RequestParam(name = "lastname") String lastname){
        return studentService.addStudent(firstname, lastname);
    }

    @RequestMapping(value = "/removeStudent", method = RequestMethod.POST)
    public boolean removeStudent(@RequestParam(name = "id") Long id){
        return studentService.removeStudent(id);
    }
}
