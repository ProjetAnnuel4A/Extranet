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
 * Created by Samuel Bijou on 18/07/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UserQuizzResponsesEntityTests
{

    @Test
    public void should_create_user_quizz_responses()
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
        ArrayList<ResponseEntity> correctResponses = new ArrayList<ResponseEntity>() ;
        ArrayList<Long> correctResponsesId = new ArrayList<Long>() ;

        QuestionEntity question = QuestionEntity.builder()
                .id(new Long(4))
                .description("QuestionTest")
                .responses(responses)
                .correctResponses(correctResponses)
                .points(6)
                .allOrNone(true)
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
                .score(new Float(1))
                .build() ;

        UserQuizzResponsesEntity userQuizzResponses = UserQuizzResponsesEntity.builder()
                .id(new Long(1))
                .userQuizzId(userQuizz.getId())
                .questionId(question.getId())
                .responses(correctResponses)
                .build() ;


        Assert.assertNotNull(userQuizzResponses) ;
        Assert.assertNotNull(userQuizzResponses.getUserQuizzId()) ;
        Assert.assertNotNull(userQuizzResponses.getQuestionId()) ;
        Assert.assertNotNull(userQuizzResponses.getResponses()) ;

        Assert.assertEquals(new Long(1), userQuizzResponses.getUserQuizzId()) ;
        Assert.assertEquals(new Long(4), userQuizzResponses.getQuestionId()) ;
        Assert.assertEquals(correctResponses, userQuizzResponses.getResponses()) ;
    }

}
