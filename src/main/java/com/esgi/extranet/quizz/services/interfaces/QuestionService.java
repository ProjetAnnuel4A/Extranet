package com.esgi.extranet.quizz.services.interfaces ;

import com.esgi.extranet.quizz.entities.QuestionEntity ;
import com.esgi.extranet.quizz.entities.ResponseEntity ;

import java.util.List ;
import java.util.ArrayList ;

/**
 * Created by Samuel Bijou on 27/06/2017.
 */
public interface QuestionService
{

    List<QuestionEntity> getAll() ;

    QuestionEntity addQuestion(Long id, String description, float points, boolean allOrNot, String imagePath) ;
    QuestionEntity updateQuestion(Long id, String description, float points, boolean allOrNot, String imagePath) ;
    boolean removeQuestion(Long id) ;
    QuestionEntity getQuestion(Long id) ;

    ArrayList<ResponseEntity> getResponsesFromAQuestion(Long questionId) ;
    boolean addResponseForAQuestion(Long questionId, Long responseId) ;
    boolean removeResponseFromAQuestion(Long questionId, Long responseId) ;

    ArrayList<Long> getCorrectResponsesFromAQuestion(Long questionId) ;
    boolean addCorrectResponseForAQuestion(Long questionId, Long responseId) ;
    boolean removeCorrectResponseFromAQuestion(Long questionId, Long responseId) ;

}
