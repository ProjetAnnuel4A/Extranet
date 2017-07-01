package com.esgi.extranet.quizz.services.interfaces ;

import com.esgi.extranet.quizz.entities.UserQuizzEntity ;

import java.util.List ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
public interface UserQuizzService
{

    List<UserQuizzEntity> getAll() ;

    UserQuizzEntity addUserQuizz(Long userId, Long surveyId, Long questionId, int count) ;
    UserQuizzEntity updateUserQuizz(Long userQuizzId, Long userId, Long surveyId, Long questionId, int count) ;
    boolean removeUserQuizz(Long userQuizzId) ;
    UserQuizzEntity getUserQuizz(Long userQuizzId) ;

    List<Long> getResponsesFromAnUserQuizz(Long userQuizzId) ;
    boolean addResponseForAnUserQuizz(Long userQuizzId, Long responsesId) ;
    boolean removeResponseFromAnUserQuizz(Long userQuizzId, Long responsesId) ;

}
