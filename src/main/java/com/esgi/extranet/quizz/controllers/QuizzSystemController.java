package com.esgi.extranet.quizz.controllers ;

import com.esgi.extranet.quizz.QuizzSystem ;
import com.esgi.extranet.quizz.entities.QuestionEntity ;
import com.esgi.extranet.quizz.entities.SurveyEntity ;
import com.esgi.extranet.quizz.entities.UserQuizzEntity ;
import com.esgi.extranet.quizz.entities.UserQuizzResponsesEntity ;
import com.esgi.extranet.quizz.services.interfaces.QuestionService ;
import com.esgi.extranet.quizz.services.interfaces.SurveyService ;
import com.esgi.extranet.quizz.services.interfaces.UserQuizzResponsesService ;
import com.esgi.extranet.quizz.services.interfaces.UserQuizzService ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.web.bind.annotation.* ;

import java.util.List;

/**
 * Created by Samuel Bijou on 18/07/2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value="/quizzSystem")
public class QuizzSystemController
{

    private final SurveyService surveyService ;
    private final QuestionService questionService ;

    private final UserQuizzService userQuizzService ;
    private final UserQuizzResponsesService userQuizzResponsesService ;


    @Autowired
    public QuizzSystemController(SurveyService surveyService, QuestionService questionService, UserQuizzService userQuizzService, UserQuizzResponsesService userQuizzResponsesService)
    {
        this.surveyService = surveyService ;
        this.questionService = questionService ;

        this.userQuizzService = userQuizzService ;
        this.userQuizzResponsesService = userQuizzResponsesService ;
    }


    @PostMapping("/calculateQuestionScoreWithUserQuizzId")
    public float calculateQuestionScoreWithUserQuizzId(@RequestParam("questionId") Long questionId,
                                                       @RequestParam("userQuizzId") Long userQuizzId)
    {
        QuestionEntity question = questionService.getQuestion(questionId) ;

        return QuizzSystem.calculateQuestionScore(question, userQuizzService.getUserQuizzResponsesFromAnUserQuizz(userQuizzId, questionId)) ;
    }

    @PostMapping("/calculateQuestionScoreWithUserQuizzResponsesId")
    public float calculateQuestionScoreWithUserQuizzResponsesId(@RequestParam("questionId") Long questionId,
                                                                @RequestParam("userQuizzResponsesId") Long userQuizzResponsesId)
    {
        QuestionEntity question = questionService.getQuestion(questionId) ;

        return QuizzSystem.calculateQuestionScore(question, userQuizzResponsesService.getUserQuizzResponses(userQuizzResponsesId)) ;
    }

    @PostMapping("/calculateSurveyScoreWithUserQuizzIdAndSurveyId")
    public float calculateSurveyScoreWithUserQuizzIdAndSurveyId(@RequestParam("userQuizzId") Long userQuizzId,
                                                                @RequestParam("surveyId") Long surveyId)
    {
        SurveyEntity survey = surveyService.getSurvey(surveyId) ;
        UserQuizzResponsesEntity[] userQuizzResponses = new UserQuizzResponsesEntity[survey.getQuestions().size()] ;

        for(int i = 0 ; i < survey.getQuestions().size() ; i++)
        {
            userQuizzResponses[i] = userQuizzService.getUserQuizzResponsesFromAnUserQuizz(userQuizzId, survey.getQuestions().get(i).getId()) ;
        }

        return QuizzSystem.calculateSurveyScore(survey, userQuizzResponses) ;
    }


    @PostMapping("/calculateSurveyMark")
    public void calculateSurveyMark(@RequestParam("surveyId") Long surveyId)
    {
        SurveyEntity survey = surveyService.getSurvey(surveyId) ;

        QuizzSystem.calculateSurveyMark(survey) ;
    }


    @PostMapping("/checkIfSurveyIsInfinite")
    public boolean checkIfSurveyIsInfinite(@RequestParam("surveyId") Long surveyId)
    {
        SurveyEntity survey = surveyService.getSurvey(surveyId) ;

        return QuizzSystem.surveyIsInfinite(survey) ;
    }

    @PostMapping("/checkIfSurveyIsOpen")
    public boolean checkIfSurveyIsOpen(@RequestParam("surveyId") Long surveyId)
    {
        SurveyEntity survey = surveyService.getSurvey(surveyId) ;

        return QuizzSystem.surveyIsOpen(survey) ;
    }


    @PostMapping("/checkIfUserCanAnswerSurveyWithNumberOfTries")
    public boolean checkIfUserCanAnswerSurveyWithNumberOfTries(@RequestParam("tries") int tries,
                                                               @RequestParam("surveyId") Long surveyId)
    {
        SurveyEntity survey = surveyService.getSurvey(surveyId) ;

        return QuizzSystem.userQuizzCanAnswerSurvey(tries, survey) ;
    }

    @PostMapping("/checkIfUserCanAnswerSurveyWithUserIdAndSurveyId")
    public boolean checkIfUserCanAnswerSurveyWithUserIdAndSurveyId(@RequestParam("userId") Long userId,
                                                                   @RequestParam("surveyId") Long surveyId)
    {
        SurveyEntity survey = surveyService.getSurvey(surveyId) ;
        List<UserQuizzEntity> userQuizzs = userQuizzService.getUserQuizzsByUserIdAndSurveyId(userId, surveyId) ;

        return QuizzSystem.userQuizzCanAnswerSurvey(userQuizzs.size(), survey) ;
    }

}
