package com.esgi.extranet.administration.services;

import com.esgi.extranet.administration.entities.TeacherEntity;

import java.util.List;

/**
 * @author timotheearnauld
 */
public interface TeacherService {
    List<TeacherEntity> getAll();
    TeacherEntity addTeacher(String firstname, String lastname);
    boolean removeTeacher(Long id);
}
