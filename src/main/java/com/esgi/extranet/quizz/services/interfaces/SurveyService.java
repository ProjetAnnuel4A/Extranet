package com.esgi.extranet.quizz.services.interfaces ;

import com.esgi.extranet.quizz.entities.SurveyEntity ;
import com.esgi.extranet.quizz.entities.QuestionEntity ;

import java.sql.Date ;
import java.util.List ;
import java.util.ArrayList ;

/**
 * Created by Samuel Bijou on 27/06/2017.
 */
public interface SurveyService
{

    List<SurveyEntity> getAll() ;

    SurveyEntity addSurvey(String name, float mark, int chances, Date deadLine, String imagePath) ;
    SurveyEntity updateSurvey(Long surveyId, String name, float mark, int chances, Date deadLine, String imagePath) ;
    boolean removeSurvey(Long surveyId) ;
    SurveyEntity getSurvey(Long surveyId) ;

    ArrayList<QuestionEntity> getQuestionsFromASurvey(Long surveyId) ;
    boolean addQuestionForASurvey(Long surveyId, Long questionId) ;
    boolean removeQuestionFromASurvey(Long surveyId, Long questionId) ;

}
