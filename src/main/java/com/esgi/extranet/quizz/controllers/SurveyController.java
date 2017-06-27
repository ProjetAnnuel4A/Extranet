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
    public SurveyEntity getSurvey(@RequestParam("id")Long id)
    {
        return surveyService.getSurvey(id) ;
    }

    @PostMapping("/addSurvey")
    public SurveyEntity addSurvey(@RequestParam(name = "id") Long id,
                                  @RequestParam(name = "name") String name,
                                  @RequestParam(name = "mark") float mark,
                                  @RequestParam(name = "chances") int chances,
                                  @RequestParam(name = "deadLine") Date deadLine,
                                  @RequestParam(name = "imagePath") String imagePath)
    {

        return surveyService.addSurvey(id, name, mark, chances, deadLine, imagePath) ;
    }

    @PostMapping("/updateSurvey")
    public SurveyEntity updateSurvey(@RequestParam(name = "id") Long id,
                                       @RequestParam(name = "name") String name,
                                       @RequestParam(name = "mark") float mark,
                                       @RequestParam(name = "chances") int chances,
                                       @RequestParam(name = "deadLine") Date deadLine,
                                       @RequestParam(name = "imagePath") String imagePath)
    {

        return surveyService.updateSurvey(id, name, mark, chances, deadLine, imagePath) ;
    }

    @RequestMapping(value = "/removeSurvey", method = RequestMethod.POST)
    public boolean removeSurvey(@RequestParam(name = "id") Long id)
    {
        return surveyService.removeSurvey(id) ;
    }


    @GetMapping("/getQuestionsFromASurvey")
    public List<QuestionEntity> getQuestionsFromASurvey(@RequestParam("idQuestion") Long idQuestion)
    {
        return surveyService.getQuestionsFromASurvey(idQuestion) ;
    }

    @PostMapping("/addQuestionForASurvey")
    public boolean addQuestionForASurvey(@RequestParam("idSurvey") Long idSurvey,
                                         @RequestParam("idQuestion") Long idQuestion)
    {
        return surveyService.addQuestionForASurvey(idSurvey, idQuestion) ;
    }

    @PostMapping("/removeQuestionFromASurvey")
    public boolean removeQuestionFromASurvey(@RequestParam("idSurvey") Long idSurvey,
                                             @RequestParam("idQuestion") Long idQuestion)
    {
        return surveyService.removeQuestionFromASurvey(idSurvey, idQuestion) ;
    }

}
