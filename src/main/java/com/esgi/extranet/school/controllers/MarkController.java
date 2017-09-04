package com.esgi.extranet.school.controllers;

import com.esgi.extranet.login.Service.UserServices;
import com.esgi.extranet.login.UserEntity;
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
    public MarkController(MarkService markService, UserServices userServices) {
        this.markService = markService;
    }

    @GetMapping("")
    public List<MarkEntity>getAll(){
        return markService.getAll();
    }

    @PostMapping("/addMark")
    public MarkEntity addMark(@RequestParam(name="idStudent")Long idStudent,
                              @RequestParam(name="idCourse")Long idCourse,
                              @RequestParam(name="mark")Float mark){
        return markService.addMark(idStudent, idCourse, mark);
    }

    @GetMapping("/getMarkForStudent")
    public List<MarkEntity> getMarkForStudent(@RequestParam(name="idStudent") int idStudent){
        return markService.getMarkForStudent(idStudent);
    }

    @RequestMapping(value = "/removeMark", method = RequestMethod.POST)
    public boolean removeMark(@RequestParam(name="id") Long id){
        return markService.removeMark(id);
    }
}
