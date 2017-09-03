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
    public QuestionEntity getQuestion(@RequestParam("questionId") Long questionId)
    {
        return questionService.getQuestion(questionId) ;
    }

    @PostMapping("/addQuestion")
    public QuestionEntity addQuestion(@RequestParam(name = "description") String description,
                                      @RequestParam(name = "points") float points,
                                      @RequestParam(name = "allOrNone") boolean allOrNone,
                                      @RequestParam(name = "imageId") Long imageId)
    {

        return questionService.addQuestion(description, points, allOrNone, imageId) ;
    }

    @PostMapping("/updateQuestion")
    public QuestionEntity updateQuestion(@RequestParam(name = "questionId") Long questionId,
                                         @RequestParam(name = "description") String description,
                                         @RequestParam(name = "points") float points,
                                         @RequestParam(name = "allOrNone") boolean allOrNone,
                                         @RequestParam(name = "imageId") Long imageId)
    {

        return questionService.updateQuestion(questionId, description, points, allOrNone, imageId) ;
    }

    @RequestMapping(value = "/removeQuestion", method = RequestMethod.POST)
    public boolean removeQuestion(@RequestParam(name = "questionId") Long questionId)
    {
        return questionService.removeQuestion(questionId) ;
    }


    @GetMapping("/getResponsesFromAQuestion")
    public List<ResponseEntity> getResponsesFromAQuestion(@RequestParam("questionId") Long questionId)
    {
        return questionService.getResponsesFromAQuestion(questionId) ;
    }

    @PostMapping("/addResponseForAQuestion")
    public boolean addResponseForAQuestion(@RequestParam("questionId") Long questionId,
                                           @RequestParam("responseId") Long responseId)
    {
        return questionService.addResponseForAQuestion(questionId, responseId) ;
    }

    @PostMapping("/removeResponseFromAQuestion")
    public boolean removeResponseFromAQuestion(@RequestParam("questionId") Long questionId,
                                               @RequestParam("responseId") Long responseId)
    {
        return questionService.removeResponseFromAQuestion(questionId, responseId) ;
    }


    @GetMapping("/getCorrectResponsesFromAQuestion")
    public List<Long> getCorrectResponsesFromAQuestion(@RequestParam("questionId") Long questionId)
    {
        return questionService.getCorrectResponsesFromAQuestion(questionId) ;
    }

    @PostMapping("/addCorrectResponseForAQuestion")
    public boolean addCorrectResponseForAQuestion(@RequestParam("questionId") Long questionId,
                                                  @RequestParam("correctResponseId") Long correctResponseId)
    {
        return questionService.addCorrectResponseForAQuestion(questionId, correctResponseId) ;
    }

    @PostMapping("/removeCorrectResponseFromAQuestion")
    public boolean removeCorrectResponseFromAQuestion(@RequestParam("questionId") Long questionId,
                                                      @RequestParam("correctResponseId") Long correctResponseId)
    {
        return questionService.removeCorrectResponseFromAQuestion(questionId, correctResponseId) ;
    }

}
