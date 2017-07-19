package com.esgi.extranet.quizz.services.interfaces ;

import com.esgi.extranet.quizz.entities.UserQuizzEntity ;
import com.esgi.extranet.quizz.entities.UserQuizzResponsesEntity ;

import java.util.List ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
public interface UserQuizzService
{

    List<UserQuizzEntity> getAll() ;

    UserQuizzEntity addUserQuizz(Long userId, Long surveyId, int count) ;
    UserQuizzEntity updateUserQuizz(Long userQuizzId, Long userId, Long surveyId, int count) ;
    boolean removeUserQuizz(Long userQuizzId) ;
    UserQuizzEntity getUserQuizz(Long userQuizzId) ;
    UserQuizzEntity getUsersQuizzByUserIdAndSurveyId(Long userId, Long surveyId) ;

    List<UserQuizzResponsesEntity> getAllResponsesFromAnUserQuizz(Long userQuizzId) ;
    boolean removeAllUserQuizzResponses(Long userQuizzId) ;
    List<Long> getResponsesFromAnUserQuizz(Long userQuizzId, Long questionId) ;
    boolean addResponseForAnUserQuizz(Long userQuizzId, Long questionId, Long responsesId) ;
    boolean removeResponseFromAnUserQuizz(Long userQuizzId, Long questionId, Long responsesId) ;

}
