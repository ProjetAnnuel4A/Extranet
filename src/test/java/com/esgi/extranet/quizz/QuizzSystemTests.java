package com.esgi.extranet.quizz ;

import com.esgi.extranet.quizz.QuizzSystem ;
import com.esgi.extranet.quizz.entities.QuestionEntity ;
import com.esgi.extranet.quizz.entities.ResponseEntity ;
import com.esgi.extranet.quizz.entities.SurveyEntity ;
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
 * Created by Samuel Bijou on 07/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class QuizzSystemTests
{

    private ResponseEntity r1 ;
    private ResponseEntity r2 ;
    private ResponseEntity r3 ;
    private ResponseEntity r4 ;

    private ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;


    private ArrayList<Long> correctResponses = new ArrayList<Long>() ;


    private QuestionEntity question ;

    private QuestionEntity q1 ;
    private QuestionEntity q2 ;
    private QuestionEntity q3 ;

    private ArrayList<QuestionEntity> questions = new ArrayList<QuestionEntity>() ;


    private SurveyEntity survey ;


    @Before
    public void initialize_datas()
    {
        r1 = new ResponseEntity(new Long(1), "ResponseTest 1", "") ;
        r2 = new ResponseEntity(new Long(2), "ResponseTest 2", "") ;
        r3 = new ResponseEntity(new Long(3), "ResponseTest 3", "") ;
        r4 = new ResponseEntity(new Long(4), "ResponseTest 4", "") ;

        responses.add(r1) ;
        responses.add(r2) ;
        responses.add(r3) ;
        responses.add(r4) ;


        correctResponses.add(new Long (2)) ;
        correctResponses.add(new Long (3)) ;


        question = new QuestionEntity(new Long(1), "QuestionTest", responses, correctResponses, 6, true, "") ;

        q1 = new QuestionEntity(new Long(1), "QuestionTest 1", responses, correctResponses, 6, true, "") ;
        q2 = new QuestionEntity(new Long(2), "QuestionTest 2", responses, correctResponses, 8, true, "") ;
        q3 = new QuestionEntity(new Long(3), "QuestionTest 3", responses, correctResponses, 6, true, "") ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;


        survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 1, null, "") ;
    }


    @Test
    public void should_calculate_question_score()
    {
        int[] userResponses = new int[2] ;
        userResponses[0] = 2 ;
        userResponses[1] = 3 ;


        Assert.assertNotNull(QuizzSystem.calculateQuestionScore(question, userResponses)) ;
    }

    @Test
    public void should_give_all_question_points()
    {
        int[] userResponses = new int[2] ;
        userResponses[0] = 2 ;
        userResponses[1] = 3 ;


        Assert.assertTrue(6 == QuizzSystem.calculateQuestionScore(question, userResponses)) ;
    }

    @Test
    public void should_give_some_question_points()
    {
        question.setAllOrNot(false) ;

        int[] userResponses = new int[1] ;
        userResponses[0] = 2 ;


        Assert.assertTrue(3 == QuizzSystem.calculateQuestionScore(question, userResponses)) ;
    }

    @Test
    public void should_not_give_question_points()
    {
        int[] userResponses = new int[2] ;
        userResponses[0] = 1 ;
        userResponses[1] = 4 ;


        Assert.assertTrue(0 == QuizzSystem.calculateQuestionScore(question, userResponses)) ;
    }

    @Test
    public void should_calculate_survey_score()
    {
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
        QuizzSystem.calculateSurveyMark(survey) ;


        Assert.assertEquals(20, survey.getMark(), 0) ;
    }

    @Test
    public void should_survey_is_open()
    {
        survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 1, new Date(30/06/2037), "") ;


        Assert.assertTrue(QuizzSystem.surveyIsOpen(survey)) ;
    }

    @Test
    public void should_survey_is_open_without_deadline()
    {
        survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 1, null, "") ;


        Assert.assertTrue(QuizzSystem.surveyIsOpen(survey)) ;
    }

    @Test
    public void should_survey_is_not_open()
    {
        survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 1, new Date(29/06/2017), "") ;


        Assert.assertFalse(QuizzSystem.surveyIsOpen(survey)) ;
    }

}
