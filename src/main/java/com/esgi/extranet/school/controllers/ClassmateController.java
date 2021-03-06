package com.esgi.extranet.school.controllers;

import com.esgi.extranet.administration.entities.CourseEntity;
import com.esgi.extranet.administration.services.TeacherService;
import com.esgi.extranet.login.UserEntity;
import com.esgi.extranet.school.entities.ClassmateEntity;
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
    public List<UserEntity> getTeachersForClassmate(@RequestParam("idClassmate")Long idClassmate){
        ClassmateEntity teachersId = classmateService.getClassmate(idClassmate);
        return teachersId.getTeacherEntities();
    }

    @GetMapping("/deleteTeachersFromClassmate")
    public boolean removeTeachersFromClassmate(@RequestParam("idClassmate")Long idClassmate,
                                                        @RequestParam("idTeacher") Long idTeacher){
        return classmateService.removeTeachersFromClassmate(idClassmate, idTeacher);
    }

    @GetMapping("/getCoursesForClassmate")
    public List<CourseEntity> getCoursesForClassmate(@RequestParam("idClassmate")Long idClassmate){
        return classmateService.getCoursesForClassmate(idClassmate);
    }

    @GetMapping("/addCoursesForClassmate")
    public boolean addCoursesForClassmate(@RequestParam("idClassmate")Long idClassmate,
                                          @RequestParam("idCourse") Long idCourse){
        return classmateService.addCoursesForClassmate(idClassmate, idCourse);
    }

    @GetMapping("/removeCoursesForClassmate")
    public boolean removeCoursesForClassmate(@RequestParam("idClassmate") Long idClassmate,
                                             @RequestParam("idCourse")Long idCourse){
        return classmateService.removeCoursesForClassmate(idClassmate, idCourse);
    }

    @GetMapping("/getTeachersFromClassmate")
    public List<UserEntity>getTeachersFromClassmate(@RequestParam("idClassmate")Long idClassmate){
        return classmateService.getTeachersFromClassmate(idClassmate);
    }

    @GetMapping("/addTeacherForClassmate")
    public boolean addTeacherForClassmate(@RequestParam("idClassmate")Long idClassmate,
                                            @RequestParam("idTeacher") Long idTeacher){
        return classmateService.addTeacherForClassmate(idClassmate, idTeacher);
    }

    @PostMapping("/addClassmate")
    public ClassmateEntity addClassmate(@RequestParam(name = "classmateName") String classmateName){
        return classmateService.addClassmate(classmateName);
    }

    @GetMapping("/getClassmatesForTeacher")
    public List<ClassmateEntity> getClassmatesForTeacher(@RequestParam(name = "idTeacher")Long idTeacher){
        return classmateService.getClassmatesForTeacher(idTeacher);
    }

    @RequestMapping(value = "/removeClassmate", method = RequestMethod.POST)
    public boolean removeClassmate(@RequestParam(name = "id") Long id){
        return classmateService.removeClassmate(id);
    }
}
