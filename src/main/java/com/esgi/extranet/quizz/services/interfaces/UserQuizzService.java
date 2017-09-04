package com.esgi.extranet.quizz.services.interfaces ;

import com.esgi.extranet.quizz.entities.ResponseEntity;
import com.esgi.extranet.quizz.entities.UserQuizzEntity ;
import com.esgi.extranet.quizz.entities.UserQuizzResponsesEntity ;

import java.util.List ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
public interface UserQuizzService
{

    List<UserQuizzEntity> getAll() ;

    UserQuizzEntity addUserQuizz(Long userId, Long surveyId, float score) ;
    UserQuizzEntity updateUserQuizz(Long userQuizzId, Long userId, Long surveyId, float score) ;
    float updateUserQuizzScore(Long userQuizzId, float score) ;
    boolean removeUserQuizz(Long userQuizzId) ;
    List<UserQuizzEntity> getAllUsersQuizzBySurveyId(Long surveyId) ;
    List<UserQuizzEntity> getAllUsersQuizzByUserId(Long userId) ;
    UserQuizzEntity getUserQuizz(Long userQuizzId) ;
    List<UserQuizzEntity> getUserQuizzsByUserIdAndSurveyId(Long userId, Long surveyId) ;

    List<UserQuizzResponsesEntity> getAllUsersQuizzResponsesFromAnUserQuizz(Long userQuizzId) ;
    boolean removeAllUsersQuizzResponsesFromAnUserQuizz(Long userQuizzId) ;
    UserQuizzResponsesEntity getUserQuizzResponsesFromAnUserQuizz(Long userQuizzId, Long questionId) ;
    List<ResponseEntity> getResponsesFromAnUserQuizz(Long userQuizzId, Long questionId) ;
    boolean addResponseForAnUserQuizz(Long userQuizzId, Long questionId, Long responsesId) ;
    boolean removeResponseFromAnUserQuizz(Long userQuizzId, Long questionId, Long responsesId) ;

}
