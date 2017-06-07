package com.esgi.extranet.QCM ;

/**
 * Created by Samuel Bijou on 07/06/2017.
 */
public class QCMController
{

    public static float calculateQuestionScore(Question question, int[] userResponses)
    {
        float score = 0 ;

        int nbCorrectResponses = 0 ;

        for(int i = 0 ; i < userResponses.length ; i++)
        {
            boolean responseCorrect = false ;

            for(int j = 0 ; j < question.getIndexCorrectResponses().length ; j++)
            {
                if(userResponses[i] == question.getIndexCorrectResponses()[j])
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
            if(nbCorrectResponses == question.getIndexCorrectResponses().length)
            {
                score = question.getPoints() ;
            }
        }

        else
        {
            score = (question.getPoints() / question.getIndexCorrectResponses().length) * nbCorrectResponses ;
        }

        return score ;
    }

    public static float CalculateSurveyScore(Survey survey, int[][] userResponses)
    {
        float score = 0 ;

        for(int i = 0 ; i < survey.getQuestions().size() ; i++)
        {
            score += calculateQuestionScore(survey.getQuestions().get(i), userResponses[i]) ;
        }

        return score ;
    }

}
