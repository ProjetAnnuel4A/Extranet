package com.esgi.extranet.school.controllers;

import com.esgi.extranet.login.UserEntity;
import com.esgi.extranet.school.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.SecureRandom;
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
    public List<UserEntity> getAll(){
        return studentService.getAllStudents();
    }

    @GetMapping("/getStudent")
    public UserEntity getStudent(@RequestParam("id")Long id){
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
    public List<UserEntity>getStudentsForClassmate(@RequestParam("idClassmate")Long idClassmate){
        return studentService.getStudentsForClassmate(idClassmate);
    }

    @GetMapping("/getStudentsWithoutClassmate")
    public List<UserEntity>getStudentsWithoutClassmate(){
        return studentService.getStudentsWithoutClassmate();
    }

    @PostMapping("/addStudent")
    public UserEntity addStudent(@RequestParam(name = "firstname") String firstname,
                             @RequestParam(name = "lastname") String lastname,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "birthday") String birthday,
                             @RequestParam(name = "address") String address){
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.FRANCE);
        date = LocalDate.parse(birthday, formatter);
        SecureRandom random = new SecureRandom();
        String password = new BigInteger(130, random).toString(32);
        return studentService.addStudent(firstname, lastname, email, password, date, "", address);
    }

    @PostMapping("/updateStudent")
    public UserEntity udpateStudent(@RequestParam(name = "firstname") String firstname,
                              @RequestParam(name = "lastname") String lastname,
                              @RequestParam(name = "email") String email,
                              @RequestParam(name = "birthday") String birthday,
                              @RequestParam(name = "address") String address,
                              @RequestParam(name = "id") Long id){
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.FRANCE);
        date = LocalDate.parse(birthday, formatter);
        return studentService.updateStudent(firstname, lastname, email, date, address, id);
    }

    @RequestMapping(value = "/removeStudent", method = RequestMethod.POST)
    public boolean removeStudent(@RequestParam(name = "id") Long id){
        return studentService.removeStudent(id);
    }

    @RequestMapping(value = "/getIdForToken", method = RequestMethod.POST)
    public int getIdForToken(@RequestParam(name = "token") String token){
        return studentService.getIdForToken(token);
    }

    @RequestMapping(value = "/getClassmateFromToken", method = RequestMethod.POST)
    public int getClassmateFromToken(@RequestParam("token") String token){
        int id = studentService.getIdForToken(token);
        return studentService.getClassmateForId(id);
    }
}
