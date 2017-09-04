package com.esgi.extranet.quizz ;

import com.esgi.extranet.quizz.entities.*;

import java.sql.Date ;
import java.time.LocalDate ;
import java.util.ArrayList ;

/**
 * Created by Samuel Bijou on 07/06/2017.
 */
public class QuizzSystem
{

    public static float calculateQuestionScore(QuestionEntity question, UserQuizzResponsesEntity userQuizzResponses)
    {
        float score = 0 ;

        int nbCorrectResponses = 0 ;

        if(question.isAllOrNone())
        {
            if(userQuizzResponses.getResponses().size() != question.getCorrectResponses().size())
            {
                return 0 ;
            }
        }

        for(int i = 0 ; i < userQuizzResponses.getResponses().size() ; i++)
        {
            boolean responseCorrect = false ;

            if(question.getCorrectResponses().contains(userQuizzResponses.getResponses().get(i)))
            {
                responseCorrect = true ;

                nbCorrectResponses++ ;
            }

            if(!responseCorrect)
            {
                return 0 ;
            }
        }

        if(question.isAllOrNone())
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

    public static float calculateQuestionScore(QuestionEntity question, ArrayList<ResponseEntity> userResponses)
    {
        float score = 0 ;

        int nbCorrectResponses = 0 ;

        if(question.isAllOrNone())
        {
            if(userResponses.size() != question.getCorrectResponses().size())
            {
                return 0 ;
            }
        }

        for(int i = 0 ; i < userResponses.size() ; i++)
        {
            boolean responseCorrect = false ;

            for(int j = 0 ; j < question.getCorrectResponses().size() ; j++)
            {
                if(userResponses.get(i).getId() == question.getCorrectResponses().get(j).getId())
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

        if(question.isAllOrNone())
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

    public static float calculateQuestionScore(QuestionEntity question, ResponseEntity[] userResponses)
    {
        float score = 0 ;

        int nbCorrectResponses = 0 ;

        if(question.isAllOrNone())
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
                if(userResponses[i].getId() == question.getCorrectResponses().get(j).getId())
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

        if(question.isAllOrNone())
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

    public static float calculateSurveyScore(SurveyEntity survey, UserQuizzResponsesEntity[] userQuizzResponses)
    {
        float score = 0 ;

        for(int i = 0 ; i < survey.getQuestions().size() ; i++)
        {
            score += calculateQuestionScore(survey.getQuestions().get(i), userQuizzResponses[i]) ;
        }

        return score ;
    }

    public static float calculateSurveyScore(SurveyEntity survey, ArrayList<ArrayList<ResponseEntity>> userResponses)
    {
        float score = 0 ;

        for(int i = 0 ; i < survey.getQuestions().size() ; i++)
        {
            score += calculateQuestionScore(survey.getQuestions().get(i), userResponses.get(i)) ;
        }

        return score ;
    }

    public static float calculateSurveyScore(SurveyEntity survey, ResponseEntity[][] userResponses)
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
        boolean beginLineOk = false ;
        boolean deadLineOk = false ;

        LocalDate today = LocalDate.now() ;
        Date todayConverted = Date.valueOf(today) ;

        if(survey.getBeginLine() == null)
        {
            beginLineOk = true ;
        }

        else
        {
            if(!(todayConverted.before(survey.getBeginLine())))
            {
                beginLineOk = true ;
            }
        }

        if(survey.getDeadLine() == null)
        {
            deadLineOk = true ;
        }

        else
        {
            if(!(todayConverted.after(survey.getDeadLine())))
            {
                deadLineOk =  true ;
            }
        }

        return (beginLineOk && deadLineOk) ;
    }

    public static boolean userQuizzCanAnswerSurvey(int tries, SurveyEntity survey)
    {
        if(surveyIsOpen(survey))
        {
            if(survey.getChances() != 0)
            {
                if(tries >= survey.getChances())
                {
                    return false ;
                }
            }

            return true ;
        }

        return false ;
    }
    
}
