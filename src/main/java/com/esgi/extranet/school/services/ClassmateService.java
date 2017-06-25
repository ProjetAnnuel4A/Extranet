package com.esgi.extranet.school.services;

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
    boolean getTeachersFromClassmate(Long idClassmate, Long idTeacher);
}
