package com.esgi.extranet.quizz.services.interfaces ;

import com.esgi.extranet.quizz.entities.UserQuizzEntity ;
import com.esgi.extranet.quizz.entities.UserQuizzResponsesEntity ;

import java.util.List ;

/**
 * Created by Samuel Bijou on 16/07/2017.
 */
public interface UserQuizzResponsesService
{

    List<UserQuizzResponsesEntity> getAll() ;

    UserQuizzResponsesEntity addUserQuizzResponses(Long userQuizzId, Long questionId) ;
    UserQuizzResponsesEntity updateUserQuizzResponses(Long userQuizzResponsesId, Long userQuizzId, Long questionId) ;
    boolean removeUserQuizzResponses(Long userQuizzResponsesId) ;
    UserQuizzResponsesEntity getUserQuizzResponses(Long userQuizzResponsesId) ;

    List<Long> getResponsesFromAnUserQuizzResponses(Long userQuizzResponsesId) ;
    boolean addResponseForAnUserQuizzResponses(Long userQuizzResponsesId, Long responseId) ;
    boolean removeResponseFromAnUserQuizzResponses(Long userQuizzResponsesId, Long responseId) ;

    UserQuizzEntity getUserQuizzFromUserQuizzResponses(Long userQuizzResponsesId) ;

}
