package com.esgi.extranet.quizz.services ;

import com.esgi.extranet.quizz.entities.QuestionEntity ;
import com.esgi.extranet.quizz.entities.ResponseEntity ;
import com.esgi.extranet.quizz.repositories.QuestionRepository ;
import com.esgi.extranet.quizz.repositories.ResponseRepository ;
import com.esgi.extranet.quizz.services.implementations.QuestionServiceImpl ;
import org.junit.Assert ;
import org.junit.BeforeClass ;
import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

import java.util.ArrayList ;
import java.util.List ;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {QuestionServiceImpl.class})
public class QuestionServiceTests
{

    @Autowired
    public static QuestionServiceImpl questionService ;

    @Autowired
    public static QuestionRepository questionRepository ;

    @Autowired
    public static ResponseRepository responseRepository ;


    private static QuestionEntity question ;


    @BeforeClass
    public static void initialize_datas()
    {
        questionService = new QuestionServiceImpl(questionRepository, responseRepository) ;

        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;
        QuestionEntity question = new QuestionEntity(new Long(1), "QuestionTest", responses, correctResponses, 0, true, new Long(1)) ;

        questionRepository.save(question) ;
    }


    @Test
    public void should_add_question() throws Exception
    {
        QuestionEntity result = questionService.getQuestion(question.getId()) ;


        Assert.assertNotNull(question) ;
        Assert.assertNotNull(result) ;

        Assert.assertEquals("QuestionTest", question.getDescription()) ;
        Assert.assertEquals(0.0, question.getPoints(), 0) ;
        Assert.assertEquals(true, question.isAllOrNot()) ;
        Assert.assertEquals(new Long(1), question.getImageId()) ;

        Assert.assertEquals(result.getDescription(), question.getDescription()) ;
        Assert.assertEquals(result.getPoints(), question.getPoints(), 0) ;
        Assert.assertEquals(result.isAllOrNot(), question.isAllOrNot()) ;
        Assert.assertEquals(result.getImageId(), question.getImageId()) ;
    }

    @Test
    public void should_update_question() throws Exception
    {
        QuestionEntity result = questionService.updateQuestion(question.getId(), "QuestionTest 2", 1, false, new Long(2)) ;


        Assert.assertNotNull(question) ;
        Assert.assertNotNull(result) ;

        Assert.assertEquals("QuestionTest 2", question.getDescription()) ;
        Assert.assertEquals(1.0, question.getPoints(), 0) ;
        Assert.assertEquals(false, question.isAllOrNot()) ;
        Assert.assertEquals(new Long(2), question.getImageId()) ;
    }

    @Test
    public void should_remove_question() throws Exception
    {
        Assert.assertNotNull(question) ;


        boolean result = questionService.removeQuestion(question.getId()) ;


        Assert.assertTrue(result) ;
    }

    @Test
    public void should_get_question() throws Exception
    {
        QuestionEntity result = questionRepository.findById(new Long(1)) ;


        Assert.assertNotNull(result) ;

        Assert.assertNotNull(questionService.getQuestion(result.getId())) ;
    }


    @Test
    public void should_get_response_from_a_question() throws Exception
    {
        ResponseEntity response = new ResponseEntity(new Long(1), "ResponseTest", new Long(1)) ;

        boolean result = questionService.addResponseForAQuestion(question.getId(), response.getId()) ;

        List<ResponseEntity> responses = questionService.getResponsesFromAQuestion(question.getId()) ;


        Assert.assertNotNull(question) ;
        Assert.assertNotNull(response) ;
        Assert.assertNotNull(responses) ;

        Assert.assertTrue(result) ;

        Assert.assertEquals(1, responses.size()) ;
        Assert.assertEquals(response.getId(), responses.get(0).getId()) ;
    }

    @Test
    public void should_add_response_for_a_question() throws Exception
    {
        ResponseEntity response = new ResponseEntity(new Long(1), "ResponseTest", new Long(1)) ;

        boolean result = questionService.addResponseForAQuestion(question.getId(), response.getId()) ;


        Assert.assertNotNull(question) ;
        Assert.assertNotNull(response) ;

        Assert.assertTrue(result) ;
    }

    @Test
    public void should_remove_response_from_a_question() throws Exception
    {
        ResponseEntity response = new ResponseEntity(new Long(1), "ResponseTest", new Long(1)) ;

        boolean resultAdd = questionService.addResponseForAQuestion(question.getId(), response.getId()) ;
        boolean resultRemove = questionService.removeResponseFromAQuestion(question.getId(), response.getId()) ;


        Assert.assertNotNull(question) ;
        Assert.assertNotNull(response) ;

        Assert.assertTrue(resultAdd) ;
        Assert.assertTrue(resultRemove) ;
    }


    @Test
    public void should_get_correct_responses_from_a_question() throws Exception
    {
        Long correctResponse = new Long(1) ;

        boolean result = questionService.addCorrectResponseForAQuestion(question.getId(), correctResponse) ;

        List<Long> correctResponses = questionService.getCorrectResponsesFromAQuestion(question.getId()) ;


        Assert.assertNotNull(question) ;
        Assert.assertNotNull(correctResponse) ;
        Assert.assertNotNull(correctResponses) ;

        Assert.assertTrue(result) ;

        Assert.assertEquals(1, correctResponses.size()) ;
        Assert.assertEquals(correctResponse, correctResponses.get(0)) ;
    }

    @Test
    public void should_add_correct_response_for_a_question() throws Exception
    {
        Long correctResponse = new Long(1) ;

        boolean result = questionService.addCorrectResponseForAQuestion(question.getId(), correctResponse) ;


        Assert.assertNotNull(question) ;
        Assert.assertNotNull(correctResponse) ;

        Assert.assertTrue(result) ;
    }

    @Test
    public void should_remove_correct_response_from_a_question() throws Exception
    {
        Long correctResponse = new Long(1) ;

        boolean resultAdd = questionService.addCorrectResponseForAQuestion(question.getId(), correctResponse) ;
        boolean resultRemove = questionService.removeCorrectResponseFromAQuestion(question.getId(), correctResponse) ;


        Assert.assertNotNull(question) ;
        Assert.assertNotNull(correctResponse) ;

        Assert.assertTrue(resultAdd) ;
        Assert.assertTrue(resultRemove) ;
    }

}
