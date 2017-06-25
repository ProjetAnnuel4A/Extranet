package com.esgi.extranet.school.services;

import com.esgi.extranet.administration.entities.TeacherEntity;
import com.esgi.extranet.school.entities.ClassmateEntity;
import com.esgi.extranet.school.entities.StudentEntity;

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
    List<TeacherEntity> getTeachersFromClassmate(Long idClassmate);
    List<ClassmateEntity> getClassmatesForTeacher(Long idTeacher);
}
