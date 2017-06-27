package com.esgi.extranet.QCM.controllers ;

import com.esgi.extranet.QCM.controllers.QCMController ;
import com.esgi.extranet.QCM.entities.QuestionEntity ;
import com.esgi.extranet.QCM.entities.ResponseEntity ;
import com.esgi.extranet.QCM.entities.SurveyEntity ;
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
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        int[] correctResponses = new int[2] ;
        correctResponses[0] = 2 ;
        correctResponses[1] = 3 ;

        QuestionEntity question = new QuestionEntity(new Long(1), "question", responses, correctResponses, 6, true, "") ;

        int[] userResponses = new int[2] ;
        userResponses[0] = 2 ;
        userResponses[1] = 3 ;

        Assert.assertNotNull(QCMController.calculateQuestionScore(question, userResponses)) ;
    }

    @Test
    public void should_give_all_question_points()
    {
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        int[] correctResponses = new int[2] ;
        correctResponses[0] = 2 ;
        correctResponses[1] = 3 ;

        QuestionEntity question = new QuestionEntity(new Long(1), "question", responses, correctResponses, 6, true, "") ;

        int[] userResponses = new int[2] ;
        userResponses[0] = 2 ;
        userResponses[1] = 3 ;

        Assert.assertTrue(6 == QCMController.calculateQuestionScore(question, userResponses)) ;
    }

    @Test
    public void should_give_some_question_points()
    {
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        int[] correctResponses = new int[2] ;
        correctResponses[0] = 2 ;
        correctResponses[1] = 3 ;

        QuestionEntity question = new QuestionEntity(new Long(1), "question", responses, correctResponses, 6, false, "") ;

        int[] userResponses = new int[1] ;
        userResponses[0] = 2 ;

        Assert.assertTrue(3 == QCMController.calculateQuestionScore(question, userResponses)) ;
    }

    @Test
    public void should_not_give_question_points()
    {
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        int[] correctResponses = new int[2] ;
        correctResponses[0] = 2 ;
        correctResponses[1] = 3 ;

        QuestionEntity question = new QuestionEntity(new Long(1), "question", responses, correctResponses, 6, true, "") ;

        int[] userResponses = new int[2] ;
        userResponses[0] = 1 ;
        userResponses[1] = 4 ;

        Assert.assertTrue(0 == QCMController.calculateQuestionScore(question, userResponses)) ;
    }

    @Test
    public void should_calculate_survey_score()
    {
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        int[] correctResponses = new int[2] ;
        correctResponses[0] = 2 ;
        correctResponses[1] = 3 ;

        QuestionEntity q1 = new QuestionEntity(new Long(1), "question", responses, correctResponses, 6, true, "") ;

        QuestionEntity q2 = new QuestionEntity(new Long(2), "question", responses, correctResponses, 8, true, "") ;

        QuestionEntity q3 = new QuestionEntity(new Long(3), "question", responses, correctResponses, 6, true, "") ;

        ArrayList<QuestionEntity> questions = new ArrayList<QuestionEntity>() ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;

        SurveyEntity survey = new SurveyEntity(new Long(1), "test", questions, 19, 1, null, "") ;

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
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        int[] correctResponses = new int[2] ;
        correctResponses[0] = 2 ;
        correctResponses[1] = 3 ;

        QuestionEntity q1 = new QuestionEntity(new Long(1), "question", responses, correctResponses, 6, true, "") ;

        QuestionEntity q2 = new QuestionEntity(new Long(2), "question", responses, correctResponses, 8, true, "") ;

        QuestionEntity q3 = new QuestionEntity(new Long(3), "question", responses, correctResponses, 6, true, "") ;

        ArrayList<QuestionEntity> questions = new ArrayList<QuestionEntity>() ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;

        SurveyEntity survey = new SurveyEntity(new Long(1), "test", questions, 19, 1, null, "") ;

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
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        int[] correctResponses = new int[2] ;
        correctResponses[0] = 2 ;
        correctResponses[1] = 3 ;

        QuestionEntity q1 = new QuestionEntity(new Long(1), "question", responses, correctResponses, 6, true, "") ;

        QuestionEntity q2 = new QuestionEntity(new Long(2), "question", responses, correctResponses, 8, true, "") ;

        QuestionEntity q3 = new QuestionEntity(new Long(3), "question", responses, correctResponses, 6, true, "") ;

        ArrayList<QuestionEntity> questions = new ArrayList<QuestionEntity>() ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;

        SurveyEntity survey = new SurveyEntity(new Long(1), "test", questions, 19, 1, null, "") ;

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
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        int[] correctResponses = new int[2] ;
        correctResponses[0] = 2 ;
        correctResponses[1] = 3 ;

        QuestionEntity q1 = new QuestionEntity(new Long(1), "question", responses, correctResponses, 6, true, "") ;

        QuestionEntity q2 = new QuestionEntity(new Long(2), "question", responses, correctResponses, 8, true, "") ;

        QuestionEntity q3 = new QuestionEntity(new Long(3), "question", responses, correctResponses, 6, true, "") ;

        ArrayList<QuestionEntity> questions = new ArrayList<QuestionEntity>() ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;

        SurveyEntity survey = new SurveyEntity(new Long(1), "test", questions, 19, 1, null, "") ;

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
