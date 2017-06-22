package com.esgi.extranet.administration.services;

import com.esgi.extranet.administration.entities.CourseEntity;

/**
 * @author timotheearnauld
 */
public interface CourseService {
    CourseEntity addCourse(String coursename);
    boolean removeCourse(Long id);
}
