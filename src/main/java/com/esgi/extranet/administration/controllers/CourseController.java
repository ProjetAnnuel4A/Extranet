package com.esgi.extranet.administration.controllers;

import com.esgi.extranet.administration.entities.CourseEntity;
import com.esgi.extranet.administration.entities.TeacherEntity;
import com.esgi.extranet.administration.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author timotheearnauld
 */
@RestController
@RequestMapping(value="/courses/")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public CourseEntity addTeacher(@RequestParam("coursename") String coursename){
        return courseService.addCourse(coursename);
    }

    @RequestMapping(value = "/removeCourse", method = RequestMethod.POST)
    public boolean removeCourse(@RequestParam("id") Long id){
        return courseService.removeCourse(id);
    }
}
