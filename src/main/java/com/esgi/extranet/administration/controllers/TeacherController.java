package com.esgi.extranet.administration.controllers;

import com.esgi.extranet.administration.services.TeacherService;
import com.esgi.extranet.login.UserEntity;
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
@RequestMapping(value = "/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @GetMapping("")
    public List<UserEntity> getAll(){
        return teacherService.getAll();
    }

    @GetMapping("/getTeacher")
    public UserEntity getTeacher(@RequestParam("id")Long id){
        return teacherService.getTeacher(id);
    }

    @PostMapping(value = "/addTeacher")
    public UserEntity addTeacher(@RequestParam(name = "firstname") String firstname,
                             @RequestParam(name = "lastname") String lastname,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "password") String password,
                             @RequestParam(name = "birthday") String birthday,
                             @RequestParam(name = "photo") String photo,
                             @RequestParam(name = "address") String address){
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.FRANCE);
        date = LocalDate.parse(birthday, formatter);
        return teacherService.addTeacher(firstname, lastname, email, password, date, photo, address);
    }

    @PostMapping("/updateTeacher")
    public UserEntity udpateTeacher(@RequestParam(name = "firstname") String firstname,
                                       @RequestParam(name = "lastname") String lastname,
                                       @RequestParam(name = "email") String email,
                                       @RequestParam(name = "password") String password,
                                       @RequestParam(name = "birthday") String birthday,
                                       @RequestParam(name = "photo") String photo,
                                       @RequestParam(name = "address") String address,
                                       @RequestParam(name = "id") Long id){
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.FRANCE);
        date = LocalDate.parse(birthday, formatter);
        return teacherService.updateTeacher(firstname, lastname, email, password, date, photo, address, id);
    }

    @RequestMapping(value = "/removeTeacher", method = RequestMethod.POST)
    public boolean removeTeacher(@RequestParam("id") Long id){
        return teacherService.removeTeacher(id);
    }

}
