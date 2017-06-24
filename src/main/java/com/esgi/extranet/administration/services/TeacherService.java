package com.esgi.extranet.administration.services;

import com.esgi.extranet.administration.entities.TeacherEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author timotheearnauld
 */
public interface TeacherService {
    List<TeacherEntity> getAll();
    TeacherEntity addTeacher(String firstname, String lastname, String email, LocalDate birthday, String photo, String address);
    boolean removeTeacher(Long id);
}
