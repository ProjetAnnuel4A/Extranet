package com.esgi.extranet.QCM.services.interfaces ;

import com.esgi.extranet.QCM.entities.QuestionEntity ;
import com.esgi.extranet.QCM.entities.ResponseEntity ;

import java.util.List ;
import java.util.ArrayList ;

/**
 * Created by Samuel Bijou on 27/06/2017.
 */
public interface QuestionService
{

    List<QuestionEntity> getAll() ;

    QuestionEntity addQuestion(Long id, String description, ArrayList<ResponseEntity> responses, int[] indexCorrectResponses, float points, boolean allOrNot, String imagePath) ;
    QuestionEntity updateQuestion(Long id, String description, ArrayList<ResponseEntity> responses, int[] indexCorrectResponses, float points, boolean allOrNot, String imagePath) ;
    boolean removeQuestion(Long id) ;
    QuestionEntity getQuestion(Long id) ;

    ArrayList<ResponseEntity> getResponsesFromAQuestion(Long questionId) ;
    boolean addResponseForAQuestion(Long questionId, Long responseId) ;
    boolean removeResponseFromAQuestion(Long questionId, Long responseId) ;

}
