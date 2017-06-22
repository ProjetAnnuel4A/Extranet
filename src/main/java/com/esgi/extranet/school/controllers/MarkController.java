package com.esgi.extranet.school.controllers;

import com.esgi.extranet.school.entities.MarkEntity;
import com.esgi.extranet.school.services.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author timotheearnauld
 */
@RestController
@RequestMapping(value="/marks")
public class MarkController {
    private final MarkService markService;

    @Autowired
    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @GetMapping("")
    public List<MarkEntity>getAll(){
        return markService.getAll();
    }

    @RequestMapping(value = "/addMark", method = RequestMethod.POST)
    public MarkEntity addMark(@RequestParam(name="idStudent") Long idStudent,
                              @RequestParam(name="idTeacher") Long idTeacher,
                              @RequestParam(name="idCourse") Long idCourse,
                              @RequestParam(name="mark") Long mark){
        return markService.addMark(idStudent, idTeacher, idCourse, mark);
    }

    @RequestMapping(value = "/removeMark", method = RequestMethod.POST)
    public boolean removeMark(@RequestParam(name="id") Long id){
        return markService.removeMark(id);
    }
}
