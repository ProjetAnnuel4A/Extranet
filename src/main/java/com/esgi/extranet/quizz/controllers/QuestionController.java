package com.esgi.extranet.quizz.controllers ;

import com.esgi.extranet.quizz.entities.QuestionEntity ;
import com.esgi.extranet.quizz.entities.ResponseEntity ;
import com.esgi.extranet.quizz.services.interfaces.QuestionService ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.web.bind.annotation.* ;

import java.util.List ;

/**
 * Created by Samuel Bijou on 27/06/2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value="/questions")
public class QuestionController
{

    private final QuestionService questionService ;


    @Autowired
    public QuestionController(QuestionService questionService)
    {
        this.questionService = questionService ;
    }


    @GetMapping("")
    public List<QuestionEntity> getAll()
    {
        return questionService.getAll() ;
    }

    @GetMapping("/getQuestion")
    public QuestionEntity getQuestion(@RequestParam("id")Long id)
    {
        return questionService.getQuestion(id) ;
    }

    @PostMapping("/addQuestion")
    public QuestionEntity addQuestion(@RequestParam(name = "id") Long id,
                                      @RequestParam(name = "description") String description,
                                      @RequestParam(name = "points") float points,
                                      @RequestParam(name = "allOrNot") boolean allOrNot,
                                      @RequestParam(name = "imagePath") String imagePath)
    {

        return questionService.addQuestion(id, description, points, allOrNot, imagePath) ;
    }

    @PostMapping("/updateQuestion")
    public QuestionEntity updateQuestion(@RequestParam(name = "id") Long id,
                                         @RequestParam(name = "description") String description,
                                         @RequestParam(name = "points") float points,
                                         @RequestParam(name = "allOrNot") boolean allOrNot,
                                         @RequestParam(name = "imagePath") String imagePath)
    {

        return questionService.updateQuestion(id, description, points, allOrNot, imagePath) ;
    }

    @RequestMapping(value = "/removeQuestion", method = RequestMethod.POST)
    public boolean removeQuestion(@RequestParam(name = "id") Long id)
    {
        return questionService.removeQuestion(id) ;
    }


    @GetMapping("/getResponsesFromAQuestion")
    public List<ResponseEntity> getResponsesFromAQuestion(@RequestParam("idQuestion") Long idQuestion)
    {
        return questionService.getResponsesFromAQuestion(idQuestion) ;
    }

    @PostMapping("/addResponseForAQuestion")
    public boolean addResponseForAQuestion(@RequestParam("idQuestion") Long idQuestion,
                                           @RequestParam("idResponse") Long idResponse)
    {
        return questionService.addResponseForAQuestion(idQuestion, idResponse) ;
    }

    @PostMapping("/removeResponseFromAQuestion")
    public boolean removeResponseFromAQuestion(@RequestParam("idQuestion") Long idQuestion,
                                               @RequestParam("idResponse") Long idResponse)
    {
        return questionService.removeResponseFromAQuestion(idQuestion, idResponse) ;
    }


    @GetMapping("/getCorrectResponsesFromAQuestion")
    public List<Long> getCorrectResponsesFromAQuestion(@RequestParam("idQuestion") Long idQuestion)
    {
        return questionService.getCorrectResponsesFromAQuestion(idQuestion) ;
    }

    @PostMapping("/addCorrectResponseForAQuestion")
    public boolean addCorrectResponseForAQuestion(@RequestParam("idQuestion") Long idQuestion,
                                                  @RequestParam("idCorrectResponse") Long idCorrectResponse)
    {
        return questionService.addResponseForAQuestion(idQuestion, idCorrectResponse) ;
    }

    @PostMapping("/removeCorrectResponseFromAQuestion")
    public boolean removeCorrectResponseFromAQuestion(@RequestParam("idQuestion") Long idQuestion,
                                                      @RequestParam("idCorrectResponse") Long idCorrectResponse)
    {
        return questionService.removeResponseFromAQuestion(idQuestion, idCorrectResponse) ;
    }

}
