package com.esgi.extranet.administration.services;

import com.esgi.extranet.administration.entities.TeacherEntity;

/**
 * @author timotheearnauld
 */
public interface TeacherService {
    TeacherEntity addTeacher(String firstname, String lastname);
    boolean removeTeacher(Long id);
}
