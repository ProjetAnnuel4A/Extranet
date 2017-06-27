package com.esgi.extranet.planning.services;

import com.esgi.extranet.planning.entities.PlanningEntity;
import com.esgi.extranet.planning.entities.ScheduleEntity;

import java.util.List;

/**
 * @author timotheearnauld
 */
public interface PlanningService {
    List<PlanningEntity> getAll();
    void find(Long id);
    boolean createPlanning(String name, Long idClassmate);
    boolean addCourse(Long idClassmate, String start, String end, String title);
    boolean removeCourse(Long idClassmate, Long idCourse);
    List<ScheduleEntity> getCourseForClassmate(Long idClassmate);
}
