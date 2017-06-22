package com.esgi.extranet.planning.controller;

import com.esgi.extranet.planning.services.PlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author timotheearnauld
 */
@RestController
@RequestMapping(value="/planning")
public class PlanningController {
    private final PlanningService planningService;

    @Autowired
    public PlanningController(PlanningService planningService) {
        this.planningService = planningService;
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public void find(Long id){
        planningService.find(id);
    }
}
