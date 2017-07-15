package com.esgi.extranet.school.services;

import com.esgi.extranet.login.UserEntity;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * @author timotheearnauld
 */
public interface StudentService {
    List<UserEntity> getAllStudents();
    UserEntity addStudent(String firstname, String lastname, String email, String password, LocalDate birthday, String photo, String address);
    UserEntity updateStudent(String firstname, String lastname, String email, LocalDate date, String address, Long id);
    boolean removeStudent(Long id);
    UserEntity getStudent(Long id);
    boolean addClassmateForStudent(Long studentId, Long classmateId);
    boolean removeClassmateForStudent(Long studentId);
    List<UserEntity> getStudentsForClassmate(Long idClassmate);
    List<UserEntity> getStudentsWithoutClassmate();
}
