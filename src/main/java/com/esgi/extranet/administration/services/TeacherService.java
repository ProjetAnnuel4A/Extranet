package com.esgi.extranet.administration.services;

import com.esgi.extranet.login.UserEntity;

import java.time.LocalDate;
import java.util.List;

/**
 * @author timotheearnauld
 */
public interface TeacherService {
    List<UserEntity> getAllTeachers();
    UserEntity addTeacher(String firstname, String lastname, String email, String password, LocalDate birthday, String photo, String address);
    UserEntity updateTeacher(String firstname, String lastname, String email, LocalDate date, String address, Long id);
    boolean removeTeacher(Long id);
    UserEntity getTeacher(Long id);
}
