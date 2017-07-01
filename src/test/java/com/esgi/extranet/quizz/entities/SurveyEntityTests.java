package com.esgi.extranet.quizz.entities ;

import org.junit.Assert ;
import org.junit.Before ;
import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

import java.sql.Date ;
import java.util.ArrayList ;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT ;

/**
 * Created by Samuel Bijou on 01/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SurveyEntityTests
{

    private ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;


    private ArrayList<Long> correctResponses = new ArrayList<Long>() ;


    private QuestionEntity q1 ;
    private QuestionEntity q2 ;
    private QuestionEntity q3 ;

    private ArrayList<QuestionEntity> questions = new ArrayList<QuestionEntity>() ;


    private SurveyEntity survey ;


    @Before
    public void initialize_datas()
    {
        q1 = new QuestionEntity(new Long(1), "QuestionTest 1", responses, correctResponses, 6, true, "") ;
        q2 = new QuestionEntity(new Long(2), "QuestionTest 2", responses, correctResponses, 8, true, "") ;
        q3 = new QuestionEntity(new Long(3), "QuestionTest 3", responses, correctResponses, 6, true, "") ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;


        survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 1, null, "") ;
    }


    @Test
    public void should_create_survey()
    {
        survey = new SurveyEntity(new Long(1), "SurveyTest 2", questions, 19, 1, null, "") ;


        Assert.assertNotNull(survey) ;

        Assert.assertEquals("SurveyTest 2", survey.getName()) ;

        Assert.assertEquals(questions, survey.getQuestions()) ;

        Assert.assertEquals(q1, survey.getQuestions().get(0)) ;
        Assert.assertEquals(q2, survey.getQuestions().get(1)) ;
        Assert.assertEquals(q3, survey.getQuestions().get(2)) ;

        Assert.assertEquals(19, survey.getMark(), 0) ;
        Assert.assertEquals(1, survey.getChances(), 0) ;
        Assert.assertNull(survey.getDeadLine()) ;
        Assert.assertEquals("", survey.getImagePath()) ;
    }

    @Test
    public void should_calculate_mark()
    {
        survey.calculateMark() ;


        Assert.assertEquals(20, survey.getMark(), 0) ;
    }

    @Test
    public void should_calculate_mark_after_creation()
    {
        q1 = new QuestionEntity(new Long(1), "QuestionTest 1", responses, correctResponses, 7, true, "") ;

        q2 = new QuestionEntity(new Long(2), "QuestionTest 2", responses, correctResponses, 8, true, "") ;

        q3 = new QuestionEntity(new Long(3), "QuestionTest 3", responses, correctResponses, 5, true, "") ;

        questions = new ArrayList<QuestionEntity>() ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;

        survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 1, null, "") ;


        Assert.assertEquals(20, survey.getMark(), 0) ;
    }

    @Test
    public void should_be_infinite()
    {
        survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 0, new Date(30/06/2037), "") ;


        Assert.assertTrue(survey.isInfinite()) ;
    }

    @Test
    public void should_not_be_infinite()
    {
        survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 1, new Date(30/06/2037), "") ;


        Assert.assertFalse(survey.isInfinite()) ;
    }

    @Test
    public void should_be_open()
    {
        survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 1, new Date(30/06/2037), "") ;


        Assert.assertTrue(survey.isOpen()) ;
    }

    @Test
    public void should_be_open_without_deadline()
    {
        survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 1, null, "") ;


        Assert.assertTrue(survey.isOpen()) ;
    }

    @Test
    public void should_not_be_open()
    {
        survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 1, new Date(29/06/2017), "") ;


        Assert.assertTrue(survey.isOpen()) ;
    }

}
