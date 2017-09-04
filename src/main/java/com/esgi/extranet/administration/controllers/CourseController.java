package com.esgi.extranet.administration.controllers;

import com.esgi.extranet.administration.entities.CourseEntity;
import com.esgi.extranet.administration.services.CourseService;
import com.esgi.extranet.login.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/getCourse")
    public CourseEntity getCourse(@RequestParam("courseId")Long courseId){
        return courseService.getCourse(courseId);
    }

    @GetMapping("/getTeachersForACourse")
    public List<UserEntity>getTeachersForACourse(@RequestParam("courseId")Long courseId){
        return courseService.getTeachersForACourse(courseId);
    }

    @GetMapping("/addTeacherForACourse")
    public boolean addTeacherForACourse(@RequestParam("courseId")Long courseId,
                                        @RequestParam("teacherId")Long teacherId){
        return courseService.addTeacherForACourse(courseId, teacherId);
    }

    @GetMapping("/removeTeacherFromACourse")
    public boolean removeTeacherFromACourse(@RequestParam("courseId")Long courseId,
                                            @RequestParam("teacherId")Long teacherId){
        return courseService.removeTeacherFromACourse(courseId, teacherId);
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
