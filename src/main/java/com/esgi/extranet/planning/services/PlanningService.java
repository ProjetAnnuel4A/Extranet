package com.esgi.extranet.planning.services;

import com.esgi.extranet.planning.entities.PlanningEntity;

import java.util.List;

/**
 * @author timotheearnauld
 */
public interface PlanningService {
    List<PlanningEntity> getAll();
    void find(Long id);
    boolean createPlanning(String name, Long idClassmate);
    boolean addCourse(Long idCourse, Long idTeacher, Long idClassmate, Long begin, Long end);
}
