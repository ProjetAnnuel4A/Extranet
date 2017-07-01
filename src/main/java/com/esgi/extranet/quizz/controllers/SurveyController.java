package com.esgi.extranet.quizz.controllers ;

import com.esgi.extranet.quizz.entities.QuestionEntity ;
import com.esgi.extranet.quizz.entities.SurveyEntity ;
import com.esgi.extranet.quizz.services.interfaces.SurveyService ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.web.bind.annotation.* ;

import java.sql.Date ;
import java.util.List ;

/**
 * Created by Samuel Bijou on 27/06/2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value="/surveys")
public class SurveyController
{

    private final SurveyService surveyService ;


    @Autowired
    public SurveyController(SurveyService surveyService)
    {
        this.surveyService = surveyService ;
    }


    @GetMapping("")
    public List<SurveyEntity> getAll()
    {
        return surveyService.getAll() ;
    }

    @GetMapping("/getSurvey")
    public SurveyEntity getSurvey(@RequestParam("surveyId" ) Long surveyId)
    {
        return surveyService.getSurvey(surveyId) ;
    }

    @PostMapping("/addSurvey")
    public SurveyEntity addSurvey(@RequestParam(name = "name") String name,
                                  @RequestParam(name = "mark") float mark,
                                  @RequestParam(name = "chances") int chances,
                                  @RequestParam(name = "deadLine") Date deadLine,
                                  @RequestParam(name = "imageId") Long imageId)
    {

        return surveyService.addSurvey(name, mark, chances, deadLine, imageId) ;
    }

    @PostMapping("/updateSurvey")
    public SurveyEntity updateSurvey(@RequestParam(name = "surveyId") Long surveyId,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "mark") float mark,
                                     @RequestParam(name = "chances") int chances,
                                     @RequestParam(name = "deadLine") Date deadLine,
                                     @RequestParam(name = "imageId") Long imageId)
    {

        return surveyService.updateSurvey(surveyId, name, mark, chances, deadLine, imageId) ;
    }

    @RequestMapping(value = "/removeSurvey", method = RequestMethod.POST)
    public boolean removeSurvey(@RequestParam(name = "surveyId") Long surveyId)
    {
        return surveyService.removeSurvey(surveyId) ;
    }


    @GetMapping("/getQuestionsFromASurvey")
    public List<QuestionEntity> getQuestionsFromASurvey(@RequestParam("surveyId") Long surveyId)
    {
        return surveyService.getQuestionsFromASurvey(surveyId) ;
    }

    @PostMapping("/addQuestionForASurvey")
    public boolean addQuestionForASurvey(@RequestParam("surveyId") Long surveyId,
                                         @RequestParam("questionId") Long questionId)
    {
        return surveyService.addQuestionForASurvey(surveyId, questionId) ;
    }

    @PostMapping("/removeQuestionFromASurvey")
    public boolean removeQuestionFromASurvey(@RequestParam("surveyId") Long surveyId,
                                             @RequestParam("questionId") Long questionId)
    {
        return surveyService.removeQuestionFromASurvey(surveyId, questionId) ;
    }

}
