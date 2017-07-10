package com.esgi.extranet.quizz.entities ;

import com.esgi.extranet.login.UserEntity;
import org.junit.Assert ;
import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

import java.time.LocalDate ;
import java.util.ArrayList ;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UserEntityQuizzEntityTests
{

    @Test
    public void should_create_user_quizz()
    {
        LocalDate localDateTest = null ;
        //UserEntity student = new UserEntity(new Long(2), new Long(1), "Testeur", "Test", "testeur@testmail.com", localDateTest, "", "") ;


        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;

        QuestionEntity question = new QuestionEntity(new Long(4), "QuestionTest", responses, correctResponses, 6, true, new Long(1)) ;

        ArrayList<QuestionEntity> questions = new ArrayList<QuestionEntity>() ;

        questions.add(question) ;

        SurveyEntity survey = new SurveyEntity(new Long(3), "SurveyTest", questions, 19, 1, null, new Long(1)) ;


        /*UserQuizzEntity userQuizz = new UserQuizzEntity(new Long(1), student.getId(), survey.getId(), question.getId(), correctResponses, 1) ;


        Assert.assertNotNull(userQuizz) ;
        Assert.assertNotNull(userQuizz.getUserId()) ;
        Assert.assertNotNull(userQuizz.getSurveyId()) ;
        Assert.assertNotNull(userQuizz.getQuestionId()) ;
        Assert.assertNotNull(userQuizz.getResponses()) ;
        Assert.assertNotNull(userQuizz.getCount()) ;

        Assert.assertEquals(new Long(2), userQuizz.getUserId()) ;
        Assert.assertEquals(new Long(3), userQuizz.getSurveyId()) ;
        Assert.assertEquals(new Long(4), userQuizz.getQuestionId()) ;
        Assert.assertEquals(correctResponses, userQuizz.getResponses()) ;
        Assert.assertEquals(1, userQuizz.getCount()) ;*/
    }

}
