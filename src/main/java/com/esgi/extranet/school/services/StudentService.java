package com.esgi.extranet.school.services;

import com.esgi.extranet.school.entities.StudentEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author timotheearnauld
 */
public interface StudentService {
    List<StudentEntity> getAll();
    StudentEntity addStudent(String firstname, String lastname, String email, LocalDate birthday, String photo, String address);
    StudentEntity updateStudent(String firstname, String lastname, String email, LocalDate date, String photo, String address, Long id);
    boolean removeStudent(Long id);
    StudentEntity getStudent(Long id);
    boolean addClassmateForStudent(Long studentId, Long classmateId);
    boolean removeClassmateForStudent(Long studentId);
    List<StudentEntity> getStudentsForClassmate(Long idClassmate);
    List<StudentEntity> getStudentsWithoutClassmate();
}
