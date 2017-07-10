package com.esgi.extranet.school.services;

import com.esgi.extranet.login.UserEntity;

import java.time.LocalDate;
import java.util.List;

/**
 * @author timotheearnauld
 */
public interface StudentService {
    List<UserEntity> getAll();
    UserEntity addStudent(String firstname, String lastname, String email, String password, LocalDate birthday, String photo, String address);
    UserEntity updateStudent(String firstname, String lastname, String email, String password, LocalDate date, String photo, String address, Long id);
    boolean removeStudent(Long id);
    UserEntity getStudent(Long id);
    boolean addClassmateForStudent(Long studentId, Long classmateId);
    boolean removeClassmateForStudent(Long studentId);
    List<UserEntity> getStudentsForClassmate(Long idClassmate);
    List<UserEntity> getStudentsWithoutClassmate();
}
