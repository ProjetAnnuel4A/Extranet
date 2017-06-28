package com.esgi.extranet.quizz.services ;

import com.esgi.extranet.quizz.entities.QuestionEntity ;
import com.esgi.extranet.quizz.entities.ResponseEntity ;
import com.esgi.extranet.quizz.repositories.QuestionRepository ;
import com.esgi.extranet.quizz.services.implementations.QuestionServiceImpl ;
import org.junit.Assert ;
import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

import java.util.ArrayList ;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {QuestionServiceImpl.class})
public class QuestionServiceTests
{

    @Autowired
    QuestionServiceImpl questionService ;


    @Test
    public void should_add_question() throws Exception
    {
        QuestionEntity question = questionService.addQuestion("Test", 0, true, "") ;

        QuestionEntity result = questionService.getQuestion(question.getId()) ;


        Assert.assertNotNull(question) ;
        Assert.assertNotNull(result) ;

        Assert.assertEquals("Test", question.getDescription()) ;
        Assert.assertEquals(0, question.getPoints()) ;
        Assert.assertEquals(true, question.isAllOrNot()) ;
        Assert.assertEquals("", question.getImagePath()) ;

        Assert.assertEquals(result.getDescription(), question.getDescription()) ;
        Assert.assertEquals(result.getPoints(), question.getPoints()) ;
        Assert.assertEquals(result.isAllOrNot(), question.isAllOrNot()) ;
        Assert.assertEquals(result.getImagePath(), question.getImagePath()) ;
    }

    @Test
    public void should_update_question() throws Exception
    {
        QuestionEntity question = questionService.addQuestion("Test", 0, true, "") ;

        QuestionEntity result = questionService.updateQuestion(question.getId(), "Test 2", 1, false, "test") ;


        Assert.assertNotNull(question) ;
        Assert.assertNotNull(result) ;

        Assert.assertEquals("Test 2", question.getDescription()) ;
        Assert.assertEquals(1, question.getPoints()) ;
        Assert.assertEquals(false, question.isAllOrNot()) ;
        Assert.assertEquals("test", question.getImagePath()) ;
    }

    @Test
    public void should_remove_question() throws Exception
    {
        QuestionEntity question = questionService.addQuestion("Test", 0, true, "") ;


        Assert.assertNotNull(question) ;


        boolean result = questionService.removeQuestion(question.getId()) ;


        Assert.assertTrue(result) ;
    }

    @Test
    public void should_get_question() throws Exception
    {
        QuestionEntity question = questionService.addQuestion("Test", 0, true, "") ;


        Assert.assertNotNull(question) ;

        Assert.assertNotNull(questionService.getQuestion(question.getId())) ;
    }


    @Test
    public void should_get_response_from_a_question() throws Exception
    {
        QuestionEntity question = questionService.addQuestion("Test", 0, true, "") ;

        ResponseEntity response = new ResponseEntity(new Long(1), "Test", "") ;

        boolean result = questionService.addResponseForAQuestion(question.getId(), response.getId()) ;

        ArrayList<ResponseEntity> responses = questionService.getResponsesFromAQuestion(question.getId()) ;


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
        QuestionEntity question = questionService.addQuestion("Test", 0, true, "") ;

        ResponseEntity response = new ResponseEntity(new Long(1), "Test", "") ;

        boolean result = questionService.addResponseForAQuestion(question.getId(), response.getId()) ;


        Assert.assertNotNull(question) ;
        Assert.assertNotNull(response) ;

        Assert.assertTrue(result) ;
    }

    @Test
    public void should_remove_response_from_a_question() throws Exception
    {
        QuestionEntity question = questionService.addQuestion("Test", 0, true, "") ;

        ResponseEntity response = new ResponseEntity(new Long(1), "Test", "") ;

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
        QuestionEntity question = questionService.addQuestion("Test", 0, true, "") ;

        Long correctResponse = new Long(1) ;

        boolean result = questionService.addCorrectResponseForAQuestion(question.getId(), correctResponse) ;

        ArrayList<Long> correctResponses = questionService.getCorrectResponsesFromAQuestion(question.getId()) ;


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
        QuestionEntity question = questionService.addQuestion("Test", 0, true, "") ;

        Long correctResponse = new Long(1) ;

        boolean result = questionService.addCorrectResponseForAQuestion(question.getId(), correctResponse) ;


        Assert.assertNotNull(question) ;
        Assert.assertNotNull(correctResponse) ;

        Assert.assertTrue(result) ;
    }

    @Test
    public void should_remove_correct_response_from_a_question() throws Exception
    {
        QuestionEntity question = questionService.addQuestion("Test", 0, true, "") ;

        Long correctResponse = new Long(1) ;

        boolean resultAdd = questionService.addCorrectResponseForAQuestion(question.getId(), correctResponse) ;
        boolean resultRemove = questionService.removeCorrectResponseFromAQuestion(question.getId(), correctResponse) ;


        Assert.assertNotNull(question) ;
        Assert.assertNotNull(correctResponse) ;

        Assert.assertTrue(resultAdd) ;
        Assert.assertTrue(resultRemove) ;
    }

}
