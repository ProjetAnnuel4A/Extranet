package com.esgi.extranet.quizz.entities ;

import org.junit.Assert ;
import org.junit.Test ;

import java.util.ArrayList ;

/**
 * Created by Samuel Bijou on 01/05/2017.
 */
public class QuestionEntityTests
{

    @Test
    public void should_create_question()
    {
        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;

        QuestionEntity question = new QuestionEntity(new Long(1), "question", responses, correctResponses, 6, true, "") ;

        Assert.assertNotNull(question) ;

        Assert.assertEquals("question", question.getDescription()) ;
        Assert.assertEquals(responses, question.getResponses()) ;
        Assert.assertEquals(correctResponses, question.getCorrectResponses()) ;
        Assert.assertTrue(6 == question.getPoints()) ;
        Assert.assertTrue(question.isAllOrNot()) ;
        Assert.assertEquals("", question.getImagePath()) ;
    }

}
