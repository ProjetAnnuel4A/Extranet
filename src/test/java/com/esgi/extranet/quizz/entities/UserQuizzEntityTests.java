package com.esgi.extranet.quizz.entities ;

import com.esgi.extranet.login.UserEntity ;
import org.junit.Assert ;
import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.springframework.boot.test.context.SpringBootTest ;
import org.springframework.test.context.junit4.SpringRunner ;

import java.time.LocalDate ;
import java.util.ArrayList ;

import static com.esgi.extranet.login.Role.STUDENT ;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UserQuizzEntityTests
{

    @Test
    public void should_create_user_quizz()
    {
        LocalDate localDateTest = null ;
        UserEntity student = UserEntity.builder()
                .id(new Long(2))
                .idClassmate(new Long(1))
                .pseudo("Testeur1")
                .firstname("Testeur")
                .lastname("Test")
                .email("testeur@testmail.com")
                .birthday(localDateTest)
                .photo(null)
                .address("1 rue du test à Testville")
                .password("testeur")
                .role(STUDENT)
                .token(null)
                .build() ;


        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponses = new ArrayList<Long>() ;

        QuestionEntity question = QuestionEntity.builder()
                .id(new Long(4))
                .description("QuestionTest")
                .responses(responses)
                .correctResponses(correctResponses)
                .points(6)
                .allOrNot(true)
                .imageId(new Long(1))
                .build() ;

        ArrayList<QuestionEntity> questions = new ArrayList<QuestionEntity>() ;

        questions.add(question) ;

        SurveyEntity survey = SurveyEntity.builder()
                .id(new Long(3))
                .name("SurveyTest")
                .questions(questions)
                .mark(19)
                .chances(1)
                .deadLine(null)
                .imageId(new Long(1))
                .build() ;


        UserQuizzEntity userQuizz = UserQuizzEntity.builder()
                .id(new Long(1))
                .userId(student.getId())
                .surveyId(survey.getId())
                .questionId(question.getId())
                .responses(correctResponses)
                .count(1)
                .build() ;


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
        Assert.assertEquals(1, userQuizz.getCount()) ;
    }

}
