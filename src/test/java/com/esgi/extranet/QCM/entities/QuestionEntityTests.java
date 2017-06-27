package com.esgi.extranet.QCM.entities ;

import com.esgi.extranet.QCM.entities.QuestionEntity ;
import com.esgi.extranet.QCM.entities.ResponseEntity ;
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
        int[] correctResponses = new int[4] ;

        QuestionEntity question = new QuestionEntity(new Long(1), "question", responses, correctResponses, 6, true, "") ;

        Assert.assertNotNull(question) ;

        Assert.assertEquals("question", question.getDescription()) ;
        Assert.assertEquals(responses, question.getResponses()) ;
        Assert.assertArrayEquals(correctResponses, question.getIndexCorrectResponses()) ;
        Assert.assertTrue(6 == question.getPoints()) ;
        Assert.assertTrue(question.isAllOrNot()) ;
        Assert.assertEquals("", question.getImagePath()) ;
    }

}
