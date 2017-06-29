package com.esgi.extranet.quizz.services.interfaces ;

import com.esgi.extranet.quizz.entities.UserResponsesEntity ;

import java.util.List ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
public interface UserResponsesService
{

    List<UserResponsesEntity> getAll() ;

    UserResponsesEntity addUserResponses(Long userId, Long surveyId, Long questionId) ;
    UserResponsesEntity updateUserResponses(Long userResponsesId, Long userId, Long surveyId, Long questionId) ;
    boolean removeUserResponses(Long userResponsesId) ;
    UserResponsesEntity getUserResponses(Long userResponsesId) ;

    List<Long> getResponsesFromAUserResponses(Long userResponsesId) ;
    boolean addResponseForAUserResponses(Long userResponsesId, Long responsesId) ;
    boolean removeResponseFromAUserResponses(Long userResponsesId, Long responsesId) ;

}
