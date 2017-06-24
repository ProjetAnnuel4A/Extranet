package com.esgi.extranet.administration.controllers;

import com.esgi.extranet.administration.entities.CourseEntity;
import com.esgi.extranet.administration.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author timotheearnauld
 */
@CrossOrigin
@Controller
@RequestMapping(value="/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("")
    @ResponseBody
    public List<CourseEntity> getAll(){
        return courseService.getAll();
    }

    @PostMapping(value = "/addCourse")
    public String addTeacher(@RequestParam("coursename") String coursename){
        courseService.addCourse(coursename);
        return "redirect:../home";
    }

    @RequestMapping(value = "/removeCourse", method = RequestMethod.POST)
    @ResponseBody
    public boolean removeCourse(@RequestParam("id") Long id){
        return courseService.removeCourse(id);
    }
}
