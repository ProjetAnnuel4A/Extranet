package com.esgi.extranet ;

import com.esgi.extranet.quizz.entities.QuestionEntity ;
import com.esgi.extranet.quizz.entities.ResponseEntity ;
import com.esgi.extranet.quizz.entities.SurveyEntity ;
import com.esgi.extranet.quizz.repositories.QuestionRepository ;
import com.esgi.extranet.quizz.repositories.ResponseRepository ;
import com.esgi.extranet.quizz.repositories.SurveyRepository ;
import com.esgi.extranet.quizz.repositories.UserResponsesRepository ;

import java.util.ArrayList ;

/**
 * Created by Samuel Bijou on 29/06/2017.
 */
public class QuizzDatas
{
    public static ResponseRepository responseRepository ;
    public static QuestionRepository questionRepository ;
    public static SurveyRepository surveyRepository ;
    public static UserResponsesRepository userResponsesRepository ;


    public static void initialize_datas()
    {
        ResponseEntity r1 = new ResponseEntity(new Long(1), "ResponseTest 1", "") ;
        ResponseEntity r2 = new ResponseEntity(new Long(2), "ResponseTest 2", "") ;
        ResponseEntity r3 = new ResponseEntity(new Long(3), "ResponseTest 3", "") ;
        ResponseEntity r4 = new ResponseEntity(new Long(4), "ResponseTest 4", "") ;

        ArrayList<ResponseEntity> responses = new ArrayList<ResponseEntity>() ;

        responses.add(r1) ;
        responses.add(r2) ;
        responses.add(r3) ;
        responses.add(r4) ;


        ArrayList<Long> correctResponses = new ArrayList<Long>() ;

        correctResponses.add(new Long (2)) ;
        correctResponses.add(new Long (3)) ;


        QuestionEntity q1 = new QuestionEntity(new Long(1), "QuestionTest 1", responses, correctResponses, 6, true, "") ;
        QuestionEntity q2 = new QuestionEntity(new Long(2), "QuestionTest 2", responses, correctResponses, 8, true, "") ;
        QuestionEntity q3 = new QuestionEntity(new Long(3), "QuestionTest 3", responses, correctResponses, 6, true, "") ;

        ArrayList<QuestionEntity> questions = new ArrayList<QuestionEntity>() ;

        questions.add(q1) ;
        questions.add(q2) ;
        questions.add(q3) ;


        SurveyEntity survey = new SurveyEntity(new Long(1), "SurveyTest", questions, 19, 1, null, "") ;


        responseRepository.save(r1) ;
        responseRepository.save(r2) ;
        responseRepository.save(r3) ;
        responseRepository.save(r4) ;

        questionRepository.save(q1) ;
        questionRepository.save(q2) ;
        questionRepository.save(q3) ;

        surveyRepository.save(survey) ;
    }

}