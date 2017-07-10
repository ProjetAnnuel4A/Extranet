package com.esgi.extranet.school.services;

import com.esgi.extranet.administration.entities.CourseEntity;
import com.esgi.extranet.login.UserEntity;
import com.esgi.extranet.school.entities.ClassmateEntity;

import java.util.List;

/**
 * @author timotheearnauld
 */
public interface ClassmateService {
    List<ClassmateEntity> getAll();
    ClassmateEntity addClassmate(String classmateName);
    boolean removeClassmate(Long id);
    ClassmateEntity getClassmate(Long id);
    boolean removeTeachersFromClassmate(Long idClassmate, Long idTeacher);
    boolean addTeacherForClassmate(Long idClassmate, Long idTeacher);
    List<UserEntity> getTeachersFromClassmate(Long idClassmate);
    List<ClassmateEntity> getClassmatesForTeacher(Long idTeacher);
    List<CourseEntity> getCoursesForClassmate(Long idClassmate);
    boolean addCoursesForClassmate(Long idClassmate, Long idCourse);
    boolean removeCoursesForClassmate(Long idClassmate, Long idCourse);
}
