package com.esgi.extranet.quizz.services ;

import com.esgi.extranet.quizz.entities.QuestionEntity ;
import com.esgi.extranet.quizz.entities.ResponseEntity ;
import com.esgi.extranet.quizz.entities.SurveyEntity ;
import com.esgi.extranet.quizz.repositories.SurveyRepository ;
import com.esgi.extranet.quizz.services.implementations.SurveyServiceImpl ;
import org.junit.Assert ;
import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

import java.sql.Date ;
import java.util.ArrayList ;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {SurveyServiceImpl.class})
public class SurveyServiceTests
{

    @Autowired
    SurveyServiceImpl surveyService ;


    @Test
    public void should_add_survey() throws Exception
    {
        SurveyEntity survey = surveyService.addSurvey("Test", 0, 0, new Date(28/06/2017), "") ;

        SurveyEntity result = surveyService.getSurvey(survey.getId()) ;


        Assert.assertNotNull(survey) ;
        Assert.assertNotNull(result) ;

        Assert.assertEquals("Test", survey.getName()) ;
        Assert.assertEquals(0, survey.getChances()) ;
        Assert.assertEquals(0, survey.getChances()) ;
        Assert.assertEquals(new Date(28/06/2017), survey.getDeadLine()) ;
        Assert.assertEquals("", survey.getImagePath()) ;

        Assert.assertEquals(result.getName(), survey.getName()) ;
        Assert.assertEquals(result.getChances(), survey.getChances()) ;
        Assert.assertEquals(result.getChances(), survey.getChances()) ;
        Assert.assertEquals(result.getDeadLine(), survey.getDeadLine()) ;
        Assert.assertEquals(result.getImagePath(), survey.getImagePath()) ;
    }

    @Test
    public void should_update_survey() throws Exception
    {
        SurveyEntity survey = surveyService.addSurvey("Test", 0, 0, new Date(28/06/2017), "") ;

        SurveyEntity result = surveyService.updateSurvey(survey.getId(), "Test 2", 1, 1, new Date(29/06/2017), "test") ;


        Assert.assertNotNull(survey) ;
        Assert.assertNotNull(result) ;

        Assert.assertEquals("Test 2", survey.getName()) ;
        Assert.assertEquals(1, survey.getChances()) ;
        Assert.assertEquals(1, survey.getChances()) ;
        Assert.assertEquals(new Date(29/06/2017), survey.getDeadLine()) ;
        Assert.assertEquals("test", survey.getImagePath()) ;
    }

    @Test
    public void should_remove_survey() throws Exception
    {
        SurveyEntity survey = surveyService.addSurvey("Test", 0, 0, new Date(28/06/2017), "") ;


        Assert.assertNotNull(survey) ;


        boolean result = surveyService.removeSurvey(survey.getId()) ;


        Assert.assertTrue(result) ;
    }

    @Test
    public void should_get_survey() throws Exception
    {
        SurveyEntity survey = surveyService.addSurvey("Test", 0, 0, new Date(28/06/2017), "") ;


        Assert.assertNotNull(survey) ;

        Assert.assertNotNull(surveyService.getSurvey(survey.getId())) ;
    }


    @Test
    public void should_get_questions_from_a_survey() throws Exception
    {
        SurveyEntity survey = surveyService.addSurvey("Test", 0, 0, new Date(28/06/2017), "") ;

        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;

        QuestionEntity question = new QuestionEntity(new Long(1), "question", responses, correctResponses, 6, true, "") ;

        boolean result = surveyService.addQuestionForASurvey(survey.getId(), question.getId()) ;

        ArrayList<QuestionEntity> questions = surveyService.getQuestionsFromASurvey(survey.getId()) ;


        Assert.assertNotNull(survey) ;
        Assert.assertNotNull(question) ;
        Assert.assertNotNull(questions) ;

        Assert.assertTrue(result) ;

        Assert.assertEquals(1, questions.size()) ;
        Assert.assertEquals(question.getId(), questions.get(0).getId()) ;
    }

    @Test
    public void should_add_question_for_a_survey() throws Exception
    {
        SurveyEntity survey = surveyService.addSurvey("Test", 0, 0, new Date(28/06/2017), "") ;

        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;

        QuestionEntity question = new QuestionEntity(new Long(1), "question", responses, correctResponses, 6, true, "") ;

        boolean result = surveyService.addQuestionForASurvey(survey.getId(), question.getId()) ;


        Assert.assertNotNull(survey) ;
        Assert.assertNotNull(question) ;

        Assert.assertTrue(result) ;
    }

    @Test
    public void should_remove_question_from_a_survey() throws Exception
    {
        SurveyEntity survey = surveyService.addSurvey("Test", 0, 0, new Date(28/06/2017), "") ;

        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;

        QuestionEntity question = new QuestionEntity(new Long(1), "question", responses, correctResponses, 6, true, "") ;

        boolean resultAdd = surveyService.addQuestionForASurvey(survey.getId(), question.getId()) ;
        boolean resultRemove = surveyService.removeQuestionFromASurvey(survey.getId(), question.getId()) ;


        Assert.assertNotNull(survey) ;
        Assert.assertNotNull(question) ;

        Assert.assertTrue(resultAdd) ;
        Assert.assertTrue(resultRemove) ;
    }

}
