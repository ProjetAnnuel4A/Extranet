package com.esgi.extranet.administration.services;

import com.esgi.extranet.administration.entities.CourseEntity;
import com.esgi.extranet.login.UserEntity;

import java.util.List;

/**
 * @author timotheearnauld
 */
public interface CourseService {
    List<CourseEntity> getAll();
    CourseEntity addCourse(String coursename);
    boolean removeCourse(Long id);
    CourseEntity getCourse(Long courseId);
    List<UserEntity> getTeachersForACourse(Long courseId);
    boolean addTeacherForACourse(Long courseId, Long teacherId);
    boolean removeTeacherFromACourse(Long courseId, Long teacherId);
}
