package com.esgi.extranet.QCM ;

import org.junit.Assert ;
import org.junit.Test ;

import java.util.ArrayList ;

/**
 * Created by Samuel Bijou on 07/06/2017.
 */
public class QCMControllerTests
{

    @Test
    public void should_calculate_question_score()
    {
        ArrayList<Response> responses = new ArrayList<Response>() ;
        int[] correctResponses = new int[2] ;
        correctResponses[0] = 2 ;
        correctResponses[1] = 3 ;

        Question question = new Question("question", responses, correctResponses, 6, true, "") ;

        int[] userResponses = new int[2] ;
        userResponses[0] = 2 ;
        userResponses[1] = 3 ;

        Assert.assertNotNull(QCMController.calculateQuestionScore(question, userResponses)) ;
    }

    @Test
    public void should_give_all_question_points()
    {
        ArrayList<Response> responses = new ArrayList<Response>() ;
        int[] correctResponses = new int[2] ;
        correctResponses[0] = 2 ;
        correctResponses[1] = 3 ;

        Question question = new Question("question", responses, correctResponses, 6, true, "") ;

        int[] userResponses = new int[2] ;
        userResponses[0] = 2 ;
        userResponses[1] = 3 ;

        Assert.assertTrue(6 == QCMController.calculateQuestionScore(question, userResponses)) ;
    }

    @Test
    public void should_give_some_question_points()
    {
        ArrayList<Response> responses = new ArrayList<Response>() ;
        int[] correctResponses = new int[2] ;
        correctResponses[0] = 2 ;
        correctResponses[1] = 3 ;

        Question question = new Question("question", responses, correctResponses, 6, false, "") ;

        int[] userResponses = new int[1] ;
        userResponses[0] = 2 ;

        Assert.assertTrue(3 == QCMController.calculateQuestionScore(question, userResponses)) ;
    }

    @Test
    public void should_not_give_question_points()
    {
        ArrayList<Response> responses = new ArrayList<Response>() ;
        int[] correctResponses = new int[2] ;
        correctResponses[0] = 2 ;
        correctResponses[1] = 3 ;

        Question question = new Question("question", responses, correctResponses, 6, true, "") ;

        int[] userResponses = new int[2] ;
        userResponses[0] = 1 ;
        userResponses[1] = 4 ;

        Assert.assertTrue(0 == QCMController.calculateQuestionScore(question, userResponses)) ;
    }

    @Test
    public void should_calculate_survey_score()
    {
        ArrayList<Response> responses = new ArrayList<Response>() ;
        int[] correctResponses = new int[2] ;
        correctResponses[0] = 2 ;
        correctResponses[1] = 3 ;

        Question q1 = new Question("question", responses, correctResponses, 6, true, "") ;

        Question q2 = new Question("question", responses, correctResponses, 8, true, "") ;

        Question q3 = new Question("question", responses, correctResponses, 6, true, "") ;

        ArrayList<Question> questions = new ArrayList<Question>() ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;

        Survey survey = new Survey("test", questions, 19, 1, null, "") ;

        int[][] userResponses = new int[3][2] ;
        userResponses[0][0] = 2 ;
        userResponses[0][1] = 3 ;
        userResponses[1][0] = 2 ;
        userResponses[1][1] = 3 ;
        userResponses[2][0] = 2 ;
        userResponses[2][1] = 3 ;

        Assert.assertNotNull(QCMController.CalculateSurveyScore(survey, userResponses)) ;
    }

    @Test
    public void should_give_all_survey_points()
    {
        ArrayList<Response> responses = new ArrayList<Response>() ;
        int[] correctResponses = new int[2] ;
        correctResponses[0] = 2 ;
        correctResponses[1] = 3 ;

        Question q1 = new Question("question", responses, correctResponses, 6, true, "") ;

        Question q2 = new Question("question", responses, correctResponses, 8, true, "") ;

        Question q3 = new Question("question", responses, correctResponses, 6, true, "") ;

        ArrayList<Question> questions = new ArrayList<Question>() ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;

        Survey survey = new Survey("test", questions, 19, 1, null, "") ;

        int[][] userResponses = new int[3][2] ;
        userResponses[0][0] = 2 ;
        userResponses[0][1] = 3 ;
        userResponses[1][0] = 2 ;
        userResponses[1][1] = 3 ;
        userResponses[2][0] = 2 ;
        userResponses[2][1] = 3 ;

        Assert.assertTrue(20 == QCMController.CalculateSurveyScore(survey, userResponses)) ;
    }

    @Test
    public void should_give_some_survey_points()
    {
        ArrayList<Response> responses = new ArrayList<Response>() ;
        int[] correctResponses = new int[2] ;
        correctResponses[0] = 2 ;
        correctResponses[1] = 3 ;

        Question q1 = new Question("question", responses, correctResponses, 6, true, "") ;

        Question q2 = new Question("question", responses, correctResponses, 8, true, "") ;

        Question q3 = new Question("question", responses, correctResponses, 6, true, "") ;

        ArrayList<Question> questions = new ArrayList<Question>() ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;

        Survey survey = new Survey("test", questions, 19, 1, null, "") ;

        int[][] userResponses = new int[3][2] ;
        userResponses[0][0] = 2 ;
        userResponses[0][1] = 3 ;
        userResponses[1][0] = 1 ;
        userResponses[1][1] = 3 ;
        userResponses[2][0] = 2 ;
        userResponses[2][1] = 4 ;

        Assert.assertTrue(6 == QCMController.CalculateSurveyScore(survey, userResponses)) ;
    }

    @Test
    public void should_not_give_survey_points()
    {
        ArrayList<Response> responses = new ArrayList<Response>() ;
        int[] correctResponses = new int[2] ;
        correctResponses[0] = 2 ;
        correctResponses[1] = 3 ;

        Question q1 = new Question("question", responses, correctResponses, 6, true, "") ;

        Question q2 = new Question("question", responses, correctResponses, 8, true, "") ;

        Question q3 = new Question("question", responses, correctResponses, 6, true, "") ;

        ArrayList<Question> questions = new ArrayList<Question>() ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;

        Survey survey = new Survey("test", questions, 19, 1, null, "") ;

        int[][] userResponses = new int[3][2] ;
        userResponses[0][0] = 4 ;
        userResponses[0][1] = 4 ;
        userResponses[1][0] = 4 ;
        userResponses[1][1] = 4 ;
        userResponses[2][0] = 4 ;
        userResponses[2][1] = 4 ;

        Assert.assertTrue(0 == QCMController.CalculateSurveyScore(survey, userResponses)) ;
    }

}
