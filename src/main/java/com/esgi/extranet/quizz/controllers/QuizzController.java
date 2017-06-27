package com.esgi.extranet.quizz.controllers ;

import com.esgi.extranet.quizz.entities.QuestionEntity ;
import com.esgi.extranet.quizz.entities.SurveyEntity ;

/**
 * Created by Samuel Bijou on 07/06/2017.
 */
public class QuizzController
{

    public static float calculateQuestionScore(QuestionEntity question, int[] userResponses)
    {
        float score = 0 ;

        int nbCorrectResponses = 0 ;

        if(question.isAllOrNot())
        {
            if(userResponses.length != question.getCorrectResponses().size())
            {
                return 0 ;
            }
        }

        for(int i = 0 ; i < userResponses.length ; i++)
        {
            boolean responseCorrect = false ;

            for(int j = 0 ; j < question.getCorrectResponses().size() ; j++)
            {
                if(userResponses[i] == question.getCorrectResponses().get(j))
                {
                    responseCorrect = true ;

                    nbCorrectResponses++ ;
                }
            }

            if(!responseCorrect)
            {
                return 0 ;
            }
        }

        if(question.isAllOrNot())
        {
            if(nbCorrectResponses == question.getCorrectResponses().size())
            {
                score = question.getPoints() ;
            }
        }

        else
        {
            score = (question.getPoints() / question.getCorrectResponses().size()) * nbCorrectResponses ;
        }

        return score ;
    }

    public static float CalculateSurveyScore(SurveyEntity survey, int[][] userResponses)
    {
        float score = 0 ;

        for(int i = 0 ; i < survey.getQuestions().size() ; i++)
        {
            score += calculateQuestionScore(survey.getQuestions().get(i), userResponses[i]) ;
        }

        return score ;
    }

}