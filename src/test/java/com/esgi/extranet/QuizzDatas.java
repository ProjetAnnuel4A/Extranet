package com.esgi.extranet ;

import com.esgi.extranet.login.UserEntity ;
import com.esgi.extranet.quizz.entities.*;
import com.esgi.extranet.quizz.repositories.* ;
import org.springframework.beans.factory.annotation.Autowired ;

import java.time.LocalDate ;
import java.util.ArrayList ;

import static com.esgi.extranet.login.Role.STUDENT ;

/**
 * Created by Samuel Bijou on 29/06/2017.
 */
public class QuizzDatas
{

    @Autowired
    public static ResponseRepository responseRepository ;

    @Autowired
    public static QuestionRepository questionRepository ;

    @Autowired
    public static SurveyRepository surveyRepository ;

    @Autowired
    public static UserQuizzRepository userQuizzRepository ;

    @Autowired
    public static UserQuizzResponsesRepository userQuizzResponsesRepository ;


    public static void initialize_datas()
    {
        /* PARTIE SURVEY */

        ResponseEntity r1 = ResponseEntity.builder()
                .id(new Long(1))
                .description("ResponseTest 1")
                .imageId(new Long(1))
                .build() ;
        ResponseEntity r2 = ResponseEntity.builder()
                .id(new Long(2))
                .description("ResponseTest 2")
                .imageId(new Long(1))
                .build() ;
        ResponseEntity r3 = ResponseEntity.builder()
                .id(new Long(3))
                .description("ResponseTest 3")
                .imageId(new Long(1))
                .build() ;
        ResponseEntity r4 = ResponseEntity.builder()
                .id(new Long(4))
                .description("ResponseTest 4")
                .imageId(new Long(1))
                .build() ;

        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;

        responses.add(r1) ;
        responses.add(r2) ;
        responses.add(r3) ;
        responses.add(r4) ;


        ArrayList<ResponseEntity> correctResponses = new ArrayList<ResponseEntity>() ;

        correctResponses.add(r2) ;
        correctResponses.add(r3) ;

        ArrayList<Long> correctResponsesId = new ArrayList<Long>() ;

        correctResponsesId.add(r2.getId()) ;
        correctResponsesId.add(r3.getId()) ;


        QuestionEntity q1 = QuestionEntity.builder()
                .id(new Long(1))
                .description("QuestionTest 1")
                .responses(responses)
                .correctResponses(correctResponses)
                .points(6)
                .allOrNone(true)
                .imageId(new Long(1))
                .build() ;
        QuestionEntity q2 = QuestionEntity.builder()
                .id(new Long(2))
                .description("QuestionTest 2")
                .responses(responses)
                .correctResponses(correctResponses)
                .points(8)
                .allOrNone(true)
                .imageId(new Long(1))
                .build() ;
        QuestionEntity q3 = QuestionEntity.builder()
                .id(new Long(3))
                .description("QuestionTest 3")
                .responses(responses)
                .correctResponses(correctResponses)
                .points(6)
                .allOrNone(true)
                .imageId(new Long(1))
                .build() ;

        ArrayList<QuestionEntity> questions = new ArrayList<QuestionEntity>() ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;


        SurveyEntity survey = SurveyEntity.builder()
                .id(new Long(1))
                .name("SurveyTest")
                .questions(questions)
                .mark(19)
                .chances(1)
                .deadLine(null)
                .imageId(new Long(1))
                .build() ;


        responseRepository.save(r1) ;
        responseRepository.save(r2) ;
        responseRepository.save(r3) ;
        responseRepository.save(r4) ;

        questionRepository.save(q1) ;
        questionRepository.save(q2) ;
        questionRepository.save(q3) ;

        surveyRepository.save(survey) ;


        /* PARTIE USER */

        LocalDate localDateTest = null ;
        UserEntity student = UserEntity.builder()
                .id(new Long(1))
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


        /* PARTIE USERQUIZZ */

        UserQuizzEntity userQuizz = UserQuizzEntity.builder()
                .id(new Long(1))
                .userId(student.getId())
                .surveyId(survey.getId())
                .score(1)
                .build() ;

        ArrayList<Long> userResponses = new ArrayList<Long>() ;

        userResponses.add(new Long(2)) ;

        UserQuizzResponsesEntity userQuizzResponses = UserQuizzResponsesEntity.builder()
                .id(new Long(1))
                .userQuizzId(userQuizz.getId())
                .questionId(q1.getId())
                .responses(correctResponses)
                .build() ;


        userQuizzRepository.save(userQuizz) ;

        userQuizzResponsesRepository.save(userQuizzResponses) ;
    }

}
