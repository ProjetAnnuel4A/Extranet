package com.esgi.extranet.planning.controller;

import com.esgi.extranet.planning.entities.PlanningEntity;
import com.esgi.extranet.planning.services.PlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

    @RequestMapping("")
    public List<PlanningEntity>getAll(){
        return planningService.getAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public boolean createPlanning(@RequestParam("name") String name, @RequestParam("classmate")Long idClassmate){
        return planningService.createPlanning(name, idClassmate);
    }

    @RequestMapping(value = "/addCourse/{id}", method = RequestMethod.POST)
    public boolean addCourse(@PathVariable("id") Long idPlanning,
                             @RequestParam("date") Date date,
                             @RequestParam("idCourse")Long idCourse,
                             @RequestParam("idTeacher")Long idTeacher,
                             @RequestParam("idClassmate")Long idClassmate,
                             @RequestParam("begin")Long begin,
                             @RequestParam("end")Long end){
        return planningService.addCourse(idPlanning, date, idCourse, idTeacher, idClassmate, begin, end);
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public void find(Long id){
        planningService.find(id);
    }
}
