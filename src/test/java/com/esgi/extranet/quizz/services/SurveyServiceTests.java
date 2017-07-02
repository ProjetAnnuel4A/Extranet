package com.esgi.extranet.quizz.services ;

import com.esgi.extranet.quizz.entities.QuestionEntity ;
import com.esgi.extranet.quizz.entities.ResponseEntity ;
import com.esgi.extranet.quizz.entities.SurveyEntity ;
import com.esgi.extranet.quizz.repositories.QuestionRepository ;
import com.esgi.extranet.quizz.repositories.SurveyRepository ;
import com.esgi.extranet.quizz.services.implementations.SurveyServiceImpl ;
import org.junit.Assert ;
import org.junit.BeforeClass ;
import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

import java.sql.Date ;
import java.util.ArrayList ;
import java.util.List ;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {SurveyServiceImpl.class})
public class SurveyServiceTests
{

    @Autowired
    public static SurveyServiceImpl surveyService ;

    @Autowired
    public static SurveyRepository surveyRepository ;

    @Autowired
    public static QuestionRepository questionRepository ;


    private static SurveyEntity survey ;


    @BeforeClass
    public static void initialize_datas()
    {
        surveyService = new SurveyServiceImpl(surveyRepository, questionRepository) ;

        ArrayList<QuestionEntity> questions = new ArrayList<QuestionEntity>() ;
        SurveyEntity survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 0, 0, new Date(117, 6, 28), new Long(1)) ;

        surveyRepository.save(survey) ;
    }


    @Test
    public void should_add_survey() throws Exception
    {
        SurveyEntity result = surveyService.getSurvey(survey.getId()) ;


        Assert.assertNotNull(survey) ;
        Assert.assertNotNull(result) ;

        Assert.assertEquals("SurveyTest", survey.getName()) ;
        Assert.assertEquals(0.0, survey.getMark(), 0) ;
        Assert.assertEquals(0, survey.getChances()) ;
        Assert.assertEquals(new Date(2017, 6, 28), survey.getDeadLine()) ;
        Assert.assertEquals(new Long(1), survey.getImageId()) ;

        Assert.assertEquals(result.getName(), survey.getName()) ;
        Assert.assertEquals(result.getChances(), survey.getMark(), 0) ;
        Assert.assertEquals(result.getChances(), survey.getChances()) ;
        Assert.assertEquals(result.getDeadLine(), survey.getDeadLine()) ;
        Assert.assertEquals(result.getImageId(), survey.getImageId()) ;
    }

    @Test
    public void should_update_survey() throws Exception
    {
        SurveyEntity result = surveyService.updateSurvey(survey.getId(), "SurveyTest 2", 1, 1, new Date(117, 6, 29), new Long(2)) ;


        Assert.assertNotNull(survey) ;
        Assert.assertNotNull(result) ;

        Assert.assertEquals("SurveyTest 2", survey.getName()) ;
        Assert.assertEquals(1.0, survey.getMark(), 0) ;
        Assert.assertEquals(1, survey.getChances()) ;
        Assert.assertEquals(new Date(2017, 6, 29), survey.getDeadLine()) ;
        Assert.assertEquals(new Long(2), survey.getImageId()) ;
    }

    @Test
    public void should_remove_survey() throws Exception
    {
        Assert.assertNotNull(survey) ;


        boolean result = surveyService.removeSurvey(survey.getId()) ;


        Assert.assertTrue(result) ;
    }

    @Test
    public void should_get_survey() throws Exception
    {
        SurveyEntity result = surveyRepository.findById(new Long(1)) ;


        Assert.assertNotNull(result) ;

        Assert.assertNotNull(surveyService.getSurvey(result.getId())) ;
    }


    @Test
    public void should_get_questions_from_a_survey() throws Exception
    {
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;

        QuestionEntity question = new QuestionEntity(new Long(1), "QuestionTest", responses, correctResponses, 6, true, new Long(1)) ;

        boolean result = surveyService.addQuestionForASurvey(survey.getId(), question.getId()) ;

        List<QuestionEntity> questions = surveyService.getQuestionsFromASurvey(survey.getId()) ;


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
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;

        QuestionEntity question = new QuestionEntity(new Long(1), "QuestionTest", responses, correctResponses, 6, true, new Long(1)) ;

        boolean result = surveyService.addQuestionForASurvey(survey.getId(), question.getId()) ;


        Assert.assertNotNull(survey) ;
        Assert.assertNotNull(question) ;

        Assert.assertTrue(result) ;
    }

    @Test
    public void should_remove_question_from_a_survey() throws Exception
    {
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;

        QuestionEntity question = new QuestionEntity(new Long(1), "QuestionTest", responses, correctResponses, 6, true, new Long(1)) ;

        boolean resultAdd = surveyService.addQuestionForASurvey(survey.getId(), question.getId()) ;
        boolean resultRemove = surveyService.removeQuestionFromASurvey(survey.getId(), question.getId()) ;


        Assert.assertNotNull(survey) ;
        Assert.assertNotNull(question) ;

        Assert.assertTrue(resultAdd) ;
        Assert.assertTrue(resultRemove) ;
    }

}
