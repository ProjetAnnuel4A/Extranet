package com.esgi.extranet.school.controllers;

import com.esgi.extranet.school.entities.StudentEntity;
import com.esgi.extranet.school.services.StudentService;
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
@Controller
@RequestMapping(value="/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    @ResponseBody
    public List<StudentEntity> getAll(){
        return studentService.getAll();
    }

    @GetMapping("/getStudent")
    @ResponseBody
    public StudentEntity getStudent(@RequestParam("id")Long id){
        return studentService.getStudent(id);
    }

    @PostMapping("/addStudent")
    public String addStudent(@RequestParam(name = "firstname") String firstname,
                             @RequestParam(name = "lastname") String lastname,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "birthday") String birthday,
                             @RequestParam(name = "photo") String photo,
                             @RequestParam(name = "address") String address){
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.FRANCE);
        date = LocalDate.parse(birthday, formatter);
        studentService.addStudent(firstname, lastname, email, date, photo, address);
        return "redirect:../home";
    }

    @PostMapping("/updateStudent")
    @ResponseBody
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
    @ResponseBody
    public boolean removeStudent(@RequestParam(name = "id") Long id){
        return studentService.removeStudent(id);
    }
}
