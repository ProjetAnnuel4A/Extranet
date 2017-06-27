package com.esgi.extranet.administration.controllers;

import com.esgi.extranet.administration.entities.TeacherEntity;
import com.esgi.extranet.administration.services.TeacherService;
import com.esgi.extranet.school.entities.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
    public List<TeacherEntity> getAll(){
        return teacherService.getAll();
    }

    @GetMapping("/getTeacher")
    public TeacherEntity getTeacher(@RequestParam("id")Long id){
        return teacherService.getTeacher(id);
    }

    @PostMapping(value = "/addTeacher")
    public TeacherEntity addTeacher(@RequestParam(name = "firstname") String firstname,
                             @RequestParam(name = "lastname") String lastname,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "birthday") String birthday,
                             @RequestParam(name = "photo") String photo,
                             @RequestParam(name = "address") String address){
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.FRANCE);
        date = LocalDate.parse(birthday, formatter);
        return teacherService.addTeacher(firstname, lastname, email, date, photo, address);
    }

    @PostMapping("/updateTeacher")
    public TeacherEntity udpateTeacher(@RequestParam(name = "firstname") String firstname,
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
        return teacherService.updateTeacher(firstname, lastname, email, date, photo, address, id);
    }

    @RequestMapping(value = "/removeTeacher", method = RequestMethod.POST)
    public boolean removeTeacher(@RequestParam("id") Long id){
        return teacherService.removeTeacher(id);
    }

}
