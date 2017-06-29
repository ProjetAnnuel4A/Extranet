package com.esgi.extranet.quizz.entities ;

import org.junit.Assert ;
import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

import java.util.ArrayList ;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT ;

/**
 * Created by Samuel Bijou on 01/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SurveyEntityTests
{

    @Test
    public void should_create_survey()
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


        Assert.assertNotNull(survey) ;
        Assert.assertNotNull(survey.getQuestions()) ;
        Assert.assertNotNull(survey.getMark()) ;
        Assert.assertNotNull(survey.getChances()) ;

        Assert.assertEquals(questions, survey.getQuestions()) ;

        Assert.assertEquals(q1, survey.getQuestions().get(0)) ;
        Assert.assertEquals(q2, survey.getQuestions().get(1)) ;
        Assert.assertEquals(q3, survey.getQuestions().get(2)) ;
    }

    @Test
    public void should_calculate_mark()
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

        survey.calculateMark() ;


        Assert.assertEquals(20, survey.getMark(), 0) ;
    }

    @Test
    public void should_calculate_mark_after_creation()
    {
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;

        QuestionEntity q1 = new QuestionEntity(new Long(1), "question", responses, correctResponses, 7, true, "") ;

        QuestionEntity q2 = new QuestionEntity(new Long(2), "question", responses, correctResponses, 8, true, "") ;

        QuestionEntity q3 = new QuestionEntity(new Long(3), "question", responses, correctResponses, 5, true, "") ;

        ArrayList<QuestionEntity> questions = new ArrayList<QuestionEntity>() ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;

        SurveyEntity survey = new SurveyEntity(new Long(1), "test", questions, 19, 1, null, "") ;


        Assert.assertEquals(20, survey.getMark(), 0) ;
    }

}
