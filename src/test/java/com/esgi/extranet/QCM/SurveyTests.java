package com.esgi.extranet.QCM ;

import org.junit.Assert ;
import org.junit.Test ;

import java.util.ArrayList ;

/**
 * Created by Samuel Bijou on 01/05/2017.
 */
public class SurveyTests
{

    @Test
    public void should_create_survey()
    {
        ArrayList<Response> responses = new ArrayList<Response>() ;
        int[] correctResponses = new int[4] ;

        Question q1 = new Question("question", responses, correctResponses, 6, true, "") ;

        Question q2 = new Question("question", responses, correctResponses, 8, true, "") ;

        Question q3 = new Question("question", responses, correctResponses, 6, true, "") ;

        ArrayList<Question> questions = new ArrayList<Question>() ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;

        Survey survey = new Survey("test", questions, 19, 1, null, "") ;

        Assert.assertNotNull(survey) ;
        Assert.assertNotNull(survey.getQuestions()) ;
        Assert.assertNotNull(survey.getMark()) ;
        Assert.assertNotNull(survey.getChances()) ;

        Assert.assertEquals(questions, survey.getQuestions()) ;

        Assert.assertEquals(q1, survey.getQuestions().get(0)) ;
        Assert.assertEquals(q2, survey.getQuestions().get(1)) ;
        Assert.assertEquals(q3, survey.getQuestions().get(2)) ;
    }

    @Test
    public void should_calculate_mark()
    {
        ArrayList<Response> responses = new ArrayList<Response>() ;
        int[] correctResponses = new int[4] ;

        Question q1 = new Question("question", responses, correctResponses, 6, true, "") ;

        Question q2 = new Question("question", responses, correctResponses, 8, true, "") ;

        Question q3 = new Question("question", responses, correctResponses, 6, true, "") ;

        ArrayList<Question> questions = new ArrayList<Question>() ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;

        Survey survey = new Survey("test", questions, 19, 1, null, "") ;

        survey.calculateMark() ;

        Assert.assertEquals(20, survey.getMark(), 0) ;
    }

    // @PostConstruct ne fonctionne pas, nécessite peut-être le contexte de Spring
    /*
    @Test
    public void should_calculate_mark_after_creation()
    {
        ArrayList<Response> responses = new ArrayList<Response>() ;
        int[] correctResponses = new int[4] ;

        Question q1 = new Question("question", responses, correctResponses, 7, true, "") ;

        Question q2 = new Question("question", responses, correctResponses, 8, true, "") ;

        Question q3 = new Question("question", responses, correctResponses, 5, true, "") ;

        ArrayList<Question> questions = new ArrayList<Question>() ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;

        Survey survey = new Survey("test", questions, 19, 1, null, "") ;

        Assert.assertEquals(20, survey.getMark(), 0) ;
    }
    */

}
