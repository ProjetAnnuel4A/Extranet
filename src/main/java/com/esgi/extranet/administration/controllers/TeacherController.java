package com.esgi.extranet.administration.controllers;

import com.esgi.extranet.administration.services.TeacherService;
import com.esgi.extranet.login.UserEntity;
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
@RequestMapping(value = "/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @GetMapping("")
    public List<UserEntity> getAll(){
        return teacherService.getAllTeachers();
    }

    @GetMapping("/getTeacher")
    public UserEntity getTeacher(@RequestParam("id")Long id){
        return teacherService.getTeacher(id);
    }

    @PostMapping(value = "/addTeacher")
    public UserEntity addTeacher(@RequestParam(name = "firstname") String firstname,
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
        return teacherService.addTeacher(firstname, lastname, password, email, date, "", address);
    }

    @PostMapping("/updateTeacher")
    public UserEntity udpateTeacher(@RequestParam(name = "firstname") String firstname,
                                       @RequestParam(name = "lastname") String lastname,
                                       @RequestParam(name = "email") String email,
                                       @RequestParam(name = "birthday") String birthday,
                                       @RequestParam(name = "address") String address,
                                       @RequestParam(name = "id") Long id){
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.FRANCE);
        date = LocalDate.parse(birthday, formatter);
        return teacherService.updateTeacher(firstname, lastname, email, date, address, id);
    }

    @RequestMapping(value = "/removeTeacher", method = RequestMethod.POST)
    public boolean removeTeacher(@RequestParam("id") Long id){
        return teacherService.removeTeacher(id);
    }

}
