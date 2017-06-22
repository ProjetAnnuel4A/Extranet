package com.esgi.extranet.school.services;

import com.esgi.extranet.school.entities.MarkEntity;

/**
 * @author timotheearnauld
 */
public interface MarkService {
    MarkEntity addMark(Long idStudent, Long idTeacher, Long idCourse, Long mark);
    boolean removeMark(Long id);
}
