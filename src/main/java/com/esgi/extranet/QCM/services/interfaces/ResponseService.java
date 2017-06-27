package com.esgi.extranet.QCM.services.interfaces ;

import com.esgi.extranet.QCM.entities.ResponseEntity ;

import java.util.List ;

/**
 * Created by Samuel Bijou on 27/06/2017.
 */
public interface ResponseService
{

    List<ResponseEntity> getAll() ;

    ResponseEntity addResponse(Long id, String description, String imagePath) ;
    ResponseEntity updateResponse(Long id, String description, String imagePath) ;
    boolean removeResponse(Long responseId) ;
    ResponseEntity getResponse(Long responseId) ;

}
