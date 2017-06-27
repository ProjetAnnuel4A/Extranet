package com.esgi.extranet.quizz.services.implementations ;

import com.esgi.extranet.quizz.entities.ResponseEntity ;
import com.esgi.extranet.quizz.repositories.ResponseRepository ;
import com.esgi.extranet.quizz.services.interfaces.ResponseService ;
import org.springframework.beans.factory.annotation.Autowired ;

import javax.transaction.Transactional ;
import java.util.List ;

/**
 * Created by Samuel Bijou on 27/06/2017.
 */
public class ResponseServiceImpl implements ResponseService
{

    private ResponseRepository responseRepository ;


    @Autowired
    public ResponseServiceImpl(ResponseRepository responseRepository)
    {
        this.responseRepository = responseRepository ;
    }

    @Override
    public List<ResponseEntity> getAll()
    {
        return responseRepository.findAll() ;
    }

    @Override
    @Transactional
    public ResponseEntity addResponse(Long id, String description, String imagePath)
    {
        ResponseEntity responseEntity = ResponseEntity.builder()
                .description(description)
                .imagePath(imagePath)
                .build() ;

        responseRepository.save(responseEntity) ;

        return responseEntity ;
    }

    @Override
    public ResponseEntity updateResponse(Long id, String description, String imagePath)
    {
        ResponseEntity responseEntity = responseRepository.findById(id) ;

        responseEntity.setDescription(description) ;
        responseEntity.setImagePath(imagePath) ;

        responseRepository.save(responseEntity) ;

        return responseEntity ;
    }

    @Override
    @Transactional
    public boolean removeResponse(Long responseId)
    {
        responseRepository.delete(responseId) ;

        return (responseRepository.findById(responseId) == null) ;
    }

    @Override
    public ResponseEntity getResponse(Long responseId)
    {
        return responseRepository.findById(responseId) ;
    }

}
