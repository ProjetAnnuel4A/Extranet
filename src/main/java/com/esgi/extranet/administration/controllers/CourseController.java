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
@RestController
@RequestMapping(value="/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("")
    public List<CourseEntity> getAll(){
        return courseService.getAll();
    }

    @PostMapping(value = "/addCourse")
    public CourseEntity addCourseName(@RequestParam("coursename") String coursename){
        return courseService.addCourse(coursename);
    }

    @RequestMapping(value = "/removeCourse", method = RequestMethod.POST)
    public boolean removeCourse(@RequestParam("id") Long id){
        return courseService.removeCourse(id);
    }
}
