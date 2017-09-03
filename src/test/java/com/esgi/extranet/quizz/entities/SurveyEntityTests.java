package com.esgi.extranet.quizz.entities ;

import org.junit.Assert ;
import org.junit.Test ;
import org.junit.jupiter.api.BeforeAll ;
import org.junit.runner.RunWith ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

import java.sql.Date ;
import java.text.ParseException ;
import java.text.SimpleDateFormat ;
import java.util.ArrayList ;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT ;

/**
 * Created by Samuel Bijou on 01/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SurveyEntityTests
{

    private static ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;


    private static ArrayList<Long> correctResponses = new ArrayList<Long>() ;


    private static QuestionEntity q1 ;
    private static QuestionEntity q2 ;
    private static QuestionEntity q3 ;

    private static ArrayList<QuestionEntity> questions = new ArrayList<QuestionEntity>() ;


    private static SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd") ;

    private static Date deadLine = null ;

    private static SurveyEntity survey ;


    @BeforeAll
    public static void initialize_datas()
    {
        q1 = QuestionEntity.builder()
                .id(new Long(1))
                .description("QuestionTest 1")
                .responses(responses)
                .correctResponses(correctResponses)
                .points(6)
                .allOrNone(true)
                .imageId(new Long(1))
                .build() ;
        q2 = QuestionEntity.builder()
                .id(new Long(2))
                .description("QuestionTest 2")
                .responses(responses)
                .correctResponses(correctResponses)
                .points(6)
                .allOrNone(true)
                .imageId(new Long(1))
                .build() ;
        q3 = QuestionEntity.builder()
                .id(new Long(3))
                .description("QuestionTest 3")
                .responses(responses)
                .correctResponses(correctResponses)
                .points(6)
                .allOrNone(true)
                .imageId(new Long(1))
                .build() ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;


        try
        {
            deadLine = (Date) simpleDate.parse("2037-06-30") ;
        }

        catch (ParseException e)
        {
            e.printStackTrace() ;
        }

        survey = SurveyEntity.builder()
                .id(new Long(1))
                .name("SurveyTest")
                .questions(questions)
                .mark(19)
                .chances(1)
                .deadLine(deadLine)
                .imageId(new Long(1))
                .build() ;
    }


    @Test
    public void should_create_survey()
    {
        survey = SurveyEntity.builder()
                .id(new Long(1))
                .name("SurveyTest 2")
                .questions(questions)
                .mark(19)
                .chances(1)
                .deadLine(null)
                .imageId(new Long(1))
                .build() ;


        Assert.assertNotNull(survey) ;

        Assert.assertEquals("SurveyTest 2", survey.getName()) ;

        Assert.assertEquals(questions, survey.getQuestions()) ;

        Assert.assertEquals(q1, survey.getQuestions().get(0)) ;
        Assert.assertEquals(q2, survey.getQuestions().get(1)) ;
        Assert.assertEquals(q3, survey.getQuestions().get(2)) ;

        Assert.assertEquals(19.0, survey.getMark(), 0) ;
        Assert.assertEquals(1, survey.getChances(), 0) ;
        Assert.assertNull(survey.getDeadLine()) ;
        Assert.assertEquals(new Long(1), survey.getImageId()) ;
    }

    @Test
    public void should_calculate_mark()
    {
        survey.calculateMark() ;


        Assert.assertEquals(20.0, survey.getMark(), 0) ;
    }

    // @PostConstruct ne fonctionne pas
    /*
    @Test
    public void should_calculate_mark_after_creation()
    {
        q1 = new QuestionEntity(new Long(1), "QuestionTest 1", responses, correctResponses, 7, true, new Long(1)) ;

        q2 = new QuestionEntity(new Long(2), "QuestionTest 2", responses, correctResponses, 8, true, new Long(1)) ;

        q3 = new QuestionEntity(new Long(3), "QuestionTest 3", responses, correctResponses, 5, true, new Long(1)) ;

        questions = new ArrayList<QuestionEntity>() ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;

        survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 1, null, new Long(1)) ;


        Assert.assertEquals(20.0, survey.getMark(), 0) ;
    }
    */

    @Test
    public void should_be_infinite()
    {
        survey = SurveyEntity.builder()
                .id(new Long(1))
                .name("SurveyTest")
                .questions(questions)
                .mark(19)
                .chances(0)
                .deadLine(deadLine)
                .imageId(new Long(1))
                .build() ;


        Assert.assertTrue(survey.isInfinite()) ;
    }

    @Test
    public void should_not_be_infinite()
    {
        survey = SurveyEntity.builder()
                .id(new Long(1))
                .name("SurveyTest")
                .questions(questions)
                .mark(19)
                .chances(1)
                .deadLine(deadLine)
                .imageId(new Long(1))
                .build() ;


        Assert.assertFalse(survey.isInfinite()) ;
    }

    @Test
    public void should_be_open()
    {
        survey = SurveyEntity.builder()
                .id(new Long(1))
                .name("SurveyTest")
                .questions(questions)
                .mark(19)
                .chances(1)
                .deadLine(deadLine)
                .imageId(new Long(1))
                .build() ;


        Assert.assertTrue(survey.isOpen()) ;
    }

    @Test
    public void should_be_open_without_deadline()
    {
        survey = SurveyEntity.builder()
                .id(new Long(1))
                .name("SurveyTest")
                .questions(questions)
                .mark(19)
                .chances(1)
                .deadLine(null)
                .imageId(new Long(1))
                .build() ;


        Assert.assertTrue(survey.isOpen()) ;
    }

    @Test
    public void should_not_be_open()
    {
        try
        {
            deadLine = (Date) simpleDate.parse("2017-04-29") ;
        }

        catch (ParseException e)
        {
            e.printStackTrace() ;
        }

        survey = SurveyEntity.builder()
                .id(new Long(1))
                .name("SurveyTest")
                .questions(questions)
                .mark(19)
                .chances(1)
                .deadLine(deadLine)
                .imageId(new Long(1))
                .build() ;


        Assert.assertFalse(survey.isOpen()) ;
    }

}
