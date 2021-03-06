package com.esgi.extranet.school.services;

import com.esgi.extranet.school.entities.MarkEntity;

import java.util.List;

/**
 * @author timotheearnauld
 */
public interface MarkService {
    List<MarkEntity> getAll();
    MarkEntity addMark(Long idStudent, Long idCourse, Float mark);
    boolean removeMark(Long id);
    List<MarkEntity> getMarkForStudent(int idStudent);
}
