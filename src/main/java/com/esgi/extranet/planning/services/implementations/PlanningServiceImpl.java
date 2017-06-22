package com.esgi.extranet.planning.services.implementations;

import com.esgi.extranet.planning.repositories.PlanningRepository;
import com.esgi.extranet.planning.services.PlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    @Transactional
    public void find(Long id) {
        planningRepository.findById(id);
    }
}
