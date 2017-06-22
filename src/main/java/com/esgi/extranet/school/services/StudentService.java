package com.esgi.extranet.school.services;

import com.esgi.extranet.school.entities.StudentEntity;

/**
 * @author timotheearnauld
 */
public interface StudentService {
    StudentEntity addStudent(String firstname, String lastname);
    boolean removeStudent(Long id);
}
