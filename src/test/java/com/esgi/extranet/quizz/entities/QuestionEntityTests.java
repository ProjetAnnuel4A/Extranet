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
public class QuestionEntityTests
{

    @Test
    public void should_create_question()
    {
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;

        QuestionEntity question = new QuestionEntity(new Long(1), "QuestionTest", responses, correctResponses, 6, true, "") ;


        Assert.assertNotNull(question) ;

        Assert.assertEquals("QuestionTest", question.getDescription()) ;
        Assert.assertEquals(responses, question.getResponses()) ;
        Assert.assertEquals(correctResponses, question.getCorrectResponses()) ;
        Assert.assertTrue(6 == question.getPoints()) ;
        Assert.assertTrue(question.isAllOrNot()) ;
        Assert.assertEquals("", question.getImagePath()) ;
    }

}
