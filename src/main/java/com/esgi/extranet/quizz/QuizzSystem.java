package com.esgi.extranet.quizz ;

import com.esgi.extranet.quizz.entities.QuestionEntity ;
import com.esgi.extranet.quizz.entities.SurveyEntity ;
import com.esgi.extranet.quizz.entities.UserQuizzEntity ;

import java.sql.Date ;
import java.time.LocalDate ;

/**
 * Created by Samuel Bijou on 07/06/2017.
 */
public class QuizzSystem
{

    public static float calculateQuestionScore(QuestionEntity question, UserQuizzEntity userQuizzEntity)
    {
        float score = 0 ;

        int nbCorrectResponses = 0 ;

        if(question.isAllOrNot())
        {
            if(userQuizzEntity.getResponses().size() != question.getCorrectResponses().size())
            {
                return 0 ;
            }
        }

        for(int i = 0 ; i < userQuizzEntity.getResponses().size() ; i++)
        {
            boolean responseCorrect = false ;

            if(question.getCorrectResponses().contains(userQuizzEntity.getResponses().get(i)))
            {
                responseCorrect = true ;

                nbCorrectResponses++ ;
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

    public static float calculateSurveyScore(SurveyEntity survey, UserQuizzEntity[] userQuizzEntity)
    {
        float score = 0 ;

        for(int i = 0 ; i < survey.getQuestions().size() ; i++)
        {
            score += calculateQuestionScore(survey.getQuestions().get(i), userQuizzEntity[i]) ;
        }

        return score ;
    }

    public static float calculateSurveyScore(SurveyEntity survey, int[][] userResponses)
    {
        float score = 0 ;

        for(int i = 0 ; i < survey.getQuestions().size() ; i++)
        {
            score += calculateQuestionScore(survey.getQuestions().get(i), userResponses[i]) ;
        }

        return score ;
    }


    public static void calculateSurveyMark(SurveyEntity survey)
    {
        survey.setMark(0) ;

        int questionsNumber = survey.getQuestions().size() ;

        if(questionsNumber > 0)
        {
            for(int i = 0 ; i < questionsNumber ; i++)
            {
                survey.setMark(survey.getMark() + survey.getQuestions().get(i).getPoints()) ;
            }
        }
    }

    public static boolean surveyIsInfinite(SurveyEntity survey)
    {
        return (survey.getChances() == 0) ;
    }

    public static boolean surveyIsOpen(SurveyEntity survey)
    {
        if(survey.getDeadLine() == null)
        {
            return true ;
        }

        LocalDate today = LocalDate.now() ;
        Date todayConverted = Date.valueOf(today) ;

        return !(todayConverted.after(survey.getDeadLine())) ;
    }

    public static boolean userQuizzCanAnswerSurvey(UserQuizzEntity userQuizzEntity, SurveyEntity survey)
    {
        if(surveyIsOpen(survey))
        {
            if(survey.getChances() != 0)
            {
                if(userQuizzEntity.getCount() >= survey.getChances())
                {
                    return false ;
                }
            }

            return true ;
        }

        return false ;
    }
    
}
