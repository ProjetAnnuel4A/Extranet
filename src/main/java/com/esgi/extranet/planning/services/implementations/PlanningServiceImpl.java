package com.esgi.extranet.planning.services.implementations;

import com.esgi.extranet.planning.entities.ScheduleEntity;
import com.esgi.extranet.planning.entities.PlanningEntity;
import com.esgi.extranet.planning.repositories.PlanningRepository;
import com.esgi.extranet.planning.services.PlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author timotheearnauld
 */
@Service
public class PlanningServiceImpl implements PlanningService{
    private final PlanningRepository planningRepository;

    @Autowired
    public PlanningServiceImpl(PlanningRepository planningRepository) {
        this.planningRepository = planningRepository;
    }

    @Override
    public List<PlanningEntity> getAll() {
        return planningRepository.findAll();
    }

    @Override
    @Transactional
    public void find(Long id) {
        planningRepository.findById(id);
    }

    @Override
    public boolean createPlanning(String name, Long idClassmate) {
        PlanningEntity planningEntity = PlanningEntity.builder()
                .name(name)
                .idClassmate(idClassmate)
                .build();
        planningRepository.save(planningEntity);
        return true;
    }

    @Override
    public boolean addCourse(Long idCourse, Long idTeacher, Long idClassmate, Long begin, Long end) {
        ScheduleEntity scheduleEntity = ScheduleEntity.builder()
                .idCourseEntity(idCourse)
                .idTeacherEntity(idTeacher)
                .idClassmateEntity(idClassmate)
                .begin(begin)
                .end(end)
                .build();


        return false;
    }
}
