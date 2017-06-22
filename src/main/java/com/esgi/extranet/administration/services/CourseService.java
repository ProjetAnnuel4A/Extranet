package com.esgi.extranet.administration.services;

import com.esgi.extranet.administration.entities.CourseEntity;

import java.util.List;

/**
 * @author timotheearnauld
 */
public interface CourseService {
    List<CourseEntity> getAll();
    CourseEntity addCourse(String coursename);
    boolean removeCourse(Long id);
}
