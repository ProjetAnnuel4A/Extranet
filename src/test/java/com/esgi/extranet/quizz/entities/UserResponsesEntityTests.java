package com.esgi.extranet.quizz.entities ;

import com.esgi.extranet.school.entities.StudentEntity ;
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
public class UserResponsesEntityTests
{

    @Test
    public void should_create_user_responses()
    {
        LocalDate localDateTest = null ;
        StudentEntity student = new StudentEntity(new Long(2), new Long(1), "Testeur", "Test", "testeur@testmail.com", localDateTest, "", "") ;


        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;

        QuestionEntity q1 = new QuestionEntity(new Long(4), "question", responses, correctResponses, 6, true, "") ;

        ArrayList<QuestionEntity> questions = new ArrayList<QuestionEntity>() ;

        questions.add(q1) ;

        SurveyEntity survey = new SurveyEntity(new Long(3), "test", questions, 19, 1, null, "") ;


        UserResponsesEntity userResponses = new UserResponsesEntity(new Long(1), student.getId(), survey.getId(), q1.getId(), correctResponses) ;


        Assert.assertNotNull(userResponses) ;
        Assert.assertNotNull(userResponses.getUserId()) ;
        Assert.assertNotNull(userResponses.getSurveyId()) ;
        Assert.assertNotNull(userResponses.getQuestionId()) ;
        Assert.assertNotNull(userResponses.getResponses()) ;

        Assert.assertEquals(new Long(2), userResponses.getUserId()) ;
        Assert.assertEquals(new Long(3), userResponses.getSurveyId()) ;
        Assert.assertEquals(new Long(4), userResponses.getQuestionId()) ;
        Assert.assertEquals(correctResponses, userResponses.getResponses()) ;
    }

}
