package com.esgi.extranet.quizz ;

import com.esgi.extranet.quizz.QuizzSystem ;
import com.esgi.extranet.quizz.entities.QuestionEntity ;
import com.esgi.extranet.quizz.entities.ResponseEntity ;
import com.esgi.extranet.quizz.entities.SurveyEntity ;
import org.junit.Assert ;
import org.junit.Test ;

import java.util.ArrayList ;

/**
 * Created by Samuel Bijou on 07/06/2017.
 */
public class QuizzSystemTests
{

    @Test
    public void should_calculate_question_score()
    {
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;
        correctResponses.add(new Long (2)) ;
        correctResponses.add(new Long (3)) ;

        QuestionEntity question = new QuestionEntity(new Long(1), "question", responses, correctResponses, 6, true, "") ;

        int[] userResponses = new int[2] ;
        userResponses[0] = 2 ;
        userResponses[1] = 3 ;

        Assert.assertNotNull(QuizzSystem.calculateQuestionScore(question, userResponses)) ;
    }

    @Test
    public void should_give_all_question_points()
    {
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;
        correctResponses.add(new Long (2)) ;
        correctResponses.add(new Long (3)) ;

        QuestionEntity question = new QuestionEntity(new Long(1), "question", responses, correctResponses, 6, true, "") ;

        int[] userResponses = new int[2] ;
        userResponses[0] = 2 ;
        userResponses[1] = 3 ;

        Assert.assertTrue(6 == QuizzSystem.calculateQuestionScore(question, userResponses)) ;
    }

    @Test
    public void should_give_some_question_points()
    {
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;
        correctResponses.add(new Long (2)) ;
        correctResponses.add(new Long (3)) ;

        QuestionEntity question = new QuestionEntity(new Long(1), "question", responses, correctResponses, 6, false, "") ;

        int[] userResponses = new int[1] ;
        userResponses[0] = 2 ;

        Assert.assertTrue(3 == QuizzSystem.calculateQuestionScore(question, userResponses)) ;
    }

    @Test
    public void should_not_give_question_points()
    {
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;
        correctResponses.add(new Long (2)) ;
        correctResponses.add(new Long (3)) ;

        QuestionEntity question = new QuestionEntity(new Long(1), "question", responses, correctResponses, 6, true, "") ;

        int[] userResponses = new int[2] ;
        userResponses[0] = 1 ;
        userResponses[1] = 4 ;

        Assert.assertTrue(0 == QuizzSystem.calculateQuestionScore(question, userResponses)) ;
    }

    @Test
    public void should_calculate_survey_score()
    {
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;
        correctResponses.add(new Long (2)) ;
        correctResponses.add(new Long (3)) ;

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

        Assert.assertNotNull(QuizzSystem.calculateSurveyScore(survey, userResponses)) ;
    }

    @Test
    public void should_give_all_survey_points()
    {
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;
        correctResponses.add(new Long (2)) ;
        correctResponses.add(new Long (3)) ;

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

        Assert.assertTrue(20 == QuizzSystem.calculateSurveyScore(survey, userResponses)) ;
    }

    @Test
    public void should_give_some_survey_points()
    {
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;
        correctResponses.add(new Long (2)) ;
        correctResponses.add(new Long (3)) ;

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

        Assert.assertTrue(6 == QuizzSystem.calculateSurveyScore(survey, userResponses)) ;
    }

    @Test
    public void should_not_give_survey_points()
    {
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;
        correctResponses.add(new Long (2)) ;
        correctResponses.add(new Long (3)) ;

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

        Assert.assertTrue(0 == QuizzSystem.calculateSurveyScore(survey, userResponses)) ;
    }


    @Test
    public void should_calculate_survey_mark()
    {
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;

        QuestionEntity q1 = new QuestionEntity(new Long(1), "question", responses, correctResponses, 6, true, "") ;

        QuestionEntity q2 = new QuestionEntity(new Long(2), "question", responses, correctResponses, 8, true, "") ;

        QuestionEntity q3 = new QuestionEntity(new Long(3), "question", responses, correctResponses, 6, true, "") ;

        ArrayList<QuestionEntity> questions = new ArrayList<QuestionEntity>() ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;

        SurveyEntity survey = new SurveyEntity(new Long(1), "test", questions, 19, 1, null, "") ;

        QuizzSystem.calculateSurveyMark(survey) ;

        Assert.assertEquals(20, survey.getMark(), 0) ;
    }

}
