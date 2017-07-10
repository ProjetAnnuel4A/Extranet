package com.esgi.extranet.administration.services;

import com.esgi.extranet.login.UserEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author timotheearnauld
 */
public interface TeacherService {
    List<UserEntity> getAll();
    UserEntity addTeacher(String firstname, String lastname, String email, LocalDate birthday, String photo, String address);
    UserEntity updateTeacher(String firstname, String lastname, String email, LocalDate date, String photo, String address, Long id);
    boolean removeTeacher(Long id);
    UserEntity getTeacher(Long id);
}
