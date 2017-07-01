package com.esgi.extranet ;

import com.esgi.extranet.quizz.entities.QuestionEntity ;
import com.esgi.extranet.quizz.entities.ResponseEntity ;
import com.esgi.extranet.quizz.entities.SurveyEntity ;
import com.esgi.extranet.quizz.entities.UserQuizzEntity ;
import com.esgi.extranet.quizz.repositories.QuestionRepository ;
import com.esgi.extranet.quizz.repositories.ResponseRepository ;
import com.esgi.extranet.quizz.repositories.SurveyRepository ;
import com.esgi.extranet.quizz.repositories.UserQuizzRepository ;
import com.esgi.extranet.school.entities.StudentEntity ;
import org.springframework.beans.factory.annotation.Autowired ;

import java.time.LocalDate ;
import java.util.ArrayList ;

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


    public static void initialize_datas()
    {
        ResponseEntity r1 = new ResponseEntity(new Long(1), "ResponseTest 1", new Long(1)) ;
        ResponseEntity r2 = new ResponseEntity(new Long(2), "ResponseTest 2", new Long(1)) ;
        ResponseEntity r3 = new ResponseEntity(new Long(3), "ResponseTest 3", new Long(1)) ;
        ResponseEntity r4 = new ResponseEntity(new Long(4), "ResponseTest 4", new Long(1)) ;

        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;

        responses.add(r1) ;
        responses.add(r2) ;
        responses.add(r3) ;
        responses.add(r4) ;


        ArrayList<Long> correctResponses = new ArrayList<Long>() ;

        correctResponses.add(new Long (2)) ;
        correctResponses.add(new Long (3)) ;


        QuestionEntity q1 = new QuestionEntity(new Long(1), "QuestionTest 1", responses, correctResponses, 6, true, new Long(1)) ;
        QuestionEntity q2 = new QuestionEntity(new Long(2), "QuestionTest 2", responses, correctResponses, 8, true, new Long(1)) ;
        QuestionEntity q3 = new QuestionEntity(new Long(3), "QuestionTest 3", responses, correctResponses, 6, true, new Long(1)) ;

        ArrayList<QuestionEntity> questions = new ArrayList<QuestionEntity>() ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;


        SurveyEntity survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 1, null, new Long(1)) ;


        responseRepository.save(r1) ;
        responseRepository.save(r2) ;
        responseRepository.save(r3) ;
        responseRepository.save(r4) ;

        questionRepository.save(q1) ;
        questionRepository.save(q2) ;
        questionRepository.save(q3) ;

        surveyRepository.save(survey) ;


        LocalDate localDateTest = null ;
        StudentEntity student = new StudentEntity(new Long(2), new Long(1), "Testeur", "Test", "testeur@testmail.com", localDateTest, "", "") ;


        ArrayList<Long> userResponses = new ArrayList<Long>() ;

        userResponses.add(new Long(2)) ;


        UserQuizzEntity userQuizz = new UserQuizzEntity(new Long(1), student.getId(), survey.getId(), q1.getId(), userResponses, 1) ;


        userQuizzRepository.save(userQuizz) ;
    }

}
