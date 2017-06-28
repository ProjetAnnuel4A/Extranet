package com.esgi.extranet.quizz.services.interfaces ;

import com.esgi.extranet.quizz.entities.ResponseEntity ;

import java.util.List ;

/**
 * Created by Samuel Bijou on 27/06/2017.
 */
public interface ResponseService
{

    List<ResponseEntity> getAll() ;

    ResponseEntity addResponse(String description, String imagePath) ;
    ResponseEntity updateResponse(Long responseId, String description, String imagePath) ;
    boolean removeResponse(Long responseId) ;
    ResponseEntity getResponse(Long responseId) ;

}
