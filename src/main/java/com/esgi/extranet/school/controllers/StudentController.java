package com.esgi.extranet.school.controllers;

import com.esgi.extranet.school.entities.StudentEntity;
import com.esgi.extranet.school.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * @author timotheearnauld
 */
@CrossOrigin
@RestController
@RequestMapping(value="/students")
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

    @GetMapping("/getStudent")
    public StudentEntity getStudent(@RequestParam("id")Long id){
        return studentService.getStudent(id);
    }

    @PostMapping("/addClassmateForStudent")
    public boolean addClassmateForStudent(@RequestParam("studentId")Long studentId,
                                          @RequestParam("classmateId")Long classmateId){
        return studentService.addClassmateForStudent(studentId, classmateId);
    }

    @PostMapping("/removeClassmateForStudent")
    public boolean removeClassmateForStudent(@RequestParam("studentId")Long studentId){
        return studentService.removeClassmateForStudent(studentId);
    }

    @GetMapping("/getStudentsForClassmate")
    public List<StudentEntity>getStudentsForClassmate(@RequestParam("idClassmate")Long idClassmate){
        return studentService.getStudentsForClassmate(idClassmate);
    }

    @GetMapping("/getStudentsWithoutClassmate")
    public List<StudentEntity>getStudentsWithoutClassmate(){
        return studentService.getStudentsWithoutClassmate();
    }

    @PostMapping("/addStudent")
    public StudentEntity addStudent(@RequestParam(name = "firstname") String firstname,
                             @RequestParam(name = "lastname") String lastname,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "birthday") String birthday,
                             @RequestParam(name = "photo") String photo,
                             @RequestParam(name = "address") String address){
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.FRANCE);
        date = LocalDate.parse(birthday, formatter);
        return studentService.addStudent(firstname, lastname, email, date, photo, address);
    }

    @PostMapping("/updateStudent")
    public StudentEntity udpateStudent(@RequestParam(name = "firstname") String firstname,
                              @RequestParam(name = "lastname") String lastname,
                              @RequestParam(name = "email") String email,
                              @RequestParam(name = "birthday") String birthday,
                              @RequestParam(name = "photo") String photo,
                              @RequestParam(name = "address") String address,
                              @RequestParam(name = "id") Long id){
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.FRANCE);
        date = LocalDate.parse(birthday, formatter);
        return studentService.updateStudent(firstname, lastname, email, date, photo, address, id);
    }

    @RequestMapping(value = "/removeStudent", method = RequestMethod.POST)
    public boolean removeStudent(@RequestParam(name = "id") Long id){
        return studentService.removeStudent(id);
    }
}
