package com.esgi.extranet.planning.services.implementations;

import com.esgi.extranet.planning.entities.ScheduleEntity;
import com.esgi.extranet.planning.entities.PlanningEntity;
import com.esgi.extranet.planning.repositories.PlanningRepository;
import com.esgi.extranet.planning.repositories.ScheduleRepository;
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
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public PlanningServiceImpl(PlanningRepository planningRepository, ScheduleRepository scheduleRepository) {
        this.planningRepository = planningRepository;
        this.scheduleRepository = scheduleRepository;
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
    public boolean addCourse(Long idClassmate, String start, String end, String title) {
        PlanningEntity planningEntity = planningRepository.findById(idClassmate);
        ScheduleEntity scheduleEntity = ScheduleEntity.builder()
                .start(start)
                .end(end)
                .title(title)
                .build();
        scheduleRepository.save(scheduleEntity);
        planningEntity.getScheduleEntities().add(scheduleEntity);
        planningRepository.save(planningEntity);
        return false;
    }

    @Override
    public boolean removeCourse(Long idClassmate, Long idCourse) {
        PlanningEntity planningEntity = planningRepository.findById(idClassmate);
        List<ScheduleEntity>scheduleEntities = planningEntity.getScheduleEntities();

        for(int i = 0; i < scheduleEntities.size(); i++){
            if(scheduleEntities.get(i).getId().equals(idCourse)){
                scheduleEntities.remove(i);
                break;
            }
        }
        planningRepository.save(planningEntity);
        return false;
    }

    @Override
    public List<ScheduleEntity> getCourseForClassmate(Long idClassmate) {
        PlanningEntity planningEntity = planningRepository.findByIdClassmate(idClassmate);
        return planningEntity.getScheduleEntities();
    }

}
