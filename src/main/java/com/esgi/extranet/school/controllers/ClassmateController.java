package com.esgi.extranet.school.controllers;

import com.esgi.extranet.administration.entities.TeacherEntity;
import com.esgi.extranet.administration.services.TeacherService;
import com.esgi.extranet.school.entities.ClassmateEntity;
import com.esgi.extranet.school.entities.StudentEntity;
import com.esgi.extranet.school.services.ClassmateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author timotheearnauld
 */
@CrossOrigin
@RestController
@RequestMapping(value="/classmates")
public class ClassmateController {
    private final ClassmateService classmateService;

    @Autowired
    public ClassmateController(ClassmateService classmateService, TeacherService teacherService) {
        this.classmateService = classmateService;
    }

    @GetMapping("")
    public List<ClassmateEntity>getAll(){
        return classmateService.getAll();
    }

    @GetMapping("/getClassmate")
    public ClassmateEntity getClassmate(@RequestParam("id")Long id){
        return classmateService.getClassmate(id);
    }

    @GetMapping("/getTeachersForClassmate")
    public List<TeacherEntity> getTeachersForClassmate(@RequestParam("idClassmate")Long idClassmate){
        ClassmateEntity teachersId = classmateService.getClassmate(idClassmate);
        return teachersId.getTeacherEntities();
    }

    @GetMapping("/deleteTeachersFromClassmate")
    public boolean getTeachersFromClassmate(@RequestParam("idClassmate")Long idClassmate,
                                                        @RequestParam("idTeacher") Long idTeacher){
        return classmateService.getTeachersFromClassmate(idClassmate, idTeacher);
    }

    @PostMapping("/addClassmate")
    public ClassmateEntity addClassmate(@RequestParam(name = "classmateName") String classmateName){
        return classmateService.addClassmate(classmateName);
    }

    @RequestMapping(value = "/removeClassmate", method = RequestMethod.POST)
    public boolean removeClassmate(@RequestParam(name = "id") Long id){
        return classmateService.removeClassmate(id);
    }
}
