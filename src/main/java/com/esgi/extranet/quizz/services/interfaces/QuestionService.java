package com.esgi.extranet.quizz.services.interfaces ;

import com.esgi.extranet.quizz.entities.QuestionEntity ;
import com.esgi.extranet.quizz.entities.ResponseEntity ;

import java.util.List ;

/**
 * Created by Samuel Bijou on 27/06/2017.
 */
public interface QuestionService
{

    List<QuestionEntity> getAll() ;

    QuestionEntity addQuestion(String description, float points, boolean allOrNot, Long imageId) ;
    QuestionEntity updateQuestion(Long questionId, String description, float points, boolean allOrNot, Long imageId) ;
    boolean removeQuestion(Long questionId) ;
    QuestionEntity getQuestion(Long questionId) ;

    List<ResponseEntity> getResponsesFromAQuestion(Long questionId) ;
    boolean addResponseForAQuestion(Long questionId, Long responseId) ;
    boolean removeResponseFromAQuestion(Long questionId, Long responseId) ;

    List<Long> getCorrectResponsesFromAQuestion(Long questionId) ;
    boolean addCorrectResponseForAQuestion(Long questionId, Long correctResponseId) ;
    boolean removeCorrectResponseFromAQuestion(Long questionId, Long correctResponseId) ;

}
