package com.esgi.extranet.planning.controllers;

import com.esgi.extranet.planning.entities.PlanningEntity;
import com.esgi.extranet.planning.entities.ScheduleEntity;
import com.esgi.extranet.planning.services.PlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public boolean createPlanning(@RequestParam("name") String name,
                                  @RequestParam("classmate")Long idClassmate){
        return planningService.createPlanning(name, idClassmate);
    }

    @GetMapping("/getCourseForClassmate")
    public List<ScheduleEntity>getCourseForClassmate(@RequestParam("idClassmate")Long idClassmate){
        return planningService.getCourseForClassmate(idClassmate);
    }

    @GetMapping("/removeCourse")
    public boolean removeCourse(@RequestParam("idClassmate")Long idClassmate,
                                @RequestParam("idCourse")Long idCourse){
        return planningService.removeCourse(idClassmate, idCourse);
    }

    @PostMapping("/addCourse")
    public boolean addCourse(@RequestParam("idClassmate")Long idClassmate,
                             @RequestParam("start")String start,
                             @RequestParam("end")String end,
                            @RequestParam("title")String title){
        return planningService.addCourse(idClassmate, start, end, title);
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public void find(Long id){
        planningService.find(id);
    }
}
