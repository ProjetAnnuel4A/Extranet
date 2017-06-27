package com.esgi.extranet.QCM ;

import org.junit.Assert ;
import org.junit.Test ;

import java.util.ArrayList ;

/**
 * Created by Samuel Bijou on 01/05/2017.
 */
public class QuestionTests
{

    @Test
    public void should_create_question()
    {
        ArrayList<Response> responses = new ArrayList<Response>() ;
        int[] correctResponses = new int[4] ;

        Question question = new Question("question", responses, correctResponses, 6, true, "") ;

        Assert.assertNotNull(question) ;

        Assert.assertEquals("question", question.getDescription()) ;
        Assert.assertEquals(responses, question.getResponses()) ;
        Assert.assertArrayEquals(correctResponses, question.getIndexCorrectResponses()) ;
        Assert.assertTrue(6 == question.getPoints()) ;
        Assert.assertTrue(question.isAllOrNot()) ;
        Assert.assertEquals("", question.getImagePath()) ;
    }

}
