package com.esgi.extranet.school.services;

import com.esgi.extranet.school.entities.StudentEntity;

import java.util.List;

/**
 * @author timotheearnauld
 */
public interface StudentService {
    List<StudentEntity> getAll();
    StudentEntity addStudent(String firstname, String lastname);
    boolean removeStudent(Long id);
}
