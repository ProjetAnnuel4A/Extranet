package com.esgi.extranet.QCM.services.implementations ;

import com.esgi.extranet.QCM.entities.QuestionEntity ;
import com.esgi.extranet.QCM.entities.ResponseEntity ;
import com.esgi.extranet.QCM.repositories.QuestionRepository ;
import com.esgi.extranet.QCM.repositories.ResponseRepository ;
import com.esgi.extranet.QCM.services.interfaces.QuestionService ;
import org.springframework.beans.factory.annotation.Autowired ;

import javax.transaction.Transactional ;
import java.util.ArrayList ;
import java.util.List ;

/**
 * Created by Samuel Bijou on 27/06/2017.
 */
public class QuestionServiceImpl implements QuestionService
{

    private QuestionRepository questionRepository ;
    private ResponseRepository responseRepository ;


    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, ResponseRepository responseRepository)
    {
        this.questionRepository = questionRepository ;
        this.responseRepository = responseRepository ;
    }

    @Override
    public List<QuestionEntity> getAll()
    {
        return questionRepository.findAll() ;
    }

    @Override
    @Transactional
    public QuestionEntity addQuestion(Long id, String description, ArrayList<ResponseEntity> responses, int[] indexCorrectResponses, float points, boolean allOrNot, String imagePath)
    {
        QuestionEntity questionEntity = QuestionEntity.builder()
                .description(description)
                .responses(responses)
                .indexCorrectResponses(indexCorrectResponses)
                .points(points)
                .allOrNot(allOrNot)
                .imagePath(imagePath)
                .build() ;

        questionRepository.save(questionEntity) ;

        return questionEntity ;
    }

    @Override
    public QuestionEntity updateQuestion(Long id, String description, ArrayList<ResponseEntity> responses, int[] indexCorrectResponses, float points, boolean allOrNot, String imagePath)
    {
        QuestionEntity questionEntity = questionRepository.findById(id) ;

        questionEntity.setDescription(description) ;
        questionEntity.setResponses(responses) ;
        questionEntity.setIndexCorrectResponses(indexCorrectResponses) ;
        questionEntity.setPoints(points) ;
        questionEntity.setAllOrNot(allOrNot) ;
        questionEntity.setImagePath(imagePath) ;

        questionRepository.save(questionEntity) ;

        return questionEntity ;
    }

    @Override
    @Transactional
    public boolean removeQuestion(Long questionId)
    {
        questionRepository.delete(questionId) ;

        return (questionRepository.findById(questionId) == null) ;
    }

    @Override
    public QuestionEntity getQuestion(Long questionId)
    {
        return questionRepository.findById(questionId) ;
    }


    @Override
    public ArrayList<ResponseEntity> getResponsesFromAQuestion(Long questionId)
    {
        QuestionEntity questionEntity = questionRepository.findById(questionId) ;

        return questionEntity.getResponses() ;
    }

    @Override
    public boolean addResponseForAQuestion(Long questionId, Long responseId)
    {

        QuestionEntity questionEntity = questionRepository.findById(questionId) ;
        ResponseEntity responseEntity = responseRepository.findById(responseId) ;

        questionEntity.getResponses().add(responseEntity) ;

        questionRepository.save(questionEntity) ;

        return false ;
    }

    @Override
    public boolean removeResponseFromAQuestion(Long questionId, Long responseId)
    {
        QuestionEntity questionEntity = questionRepository.findById(questionId) ;
        List<ResponseEntity> responseEntities = questionEntity.getResponses() ;

        for(int i = 0 ; i < responseEntities.size() ; i++)
        {
            if(responseEntities.get(i).getId().equals(responseId))
            {
                responseEntities.remove(i) ;
                questionRepository.save(questionEntity) ;

                return true ;
            }
        }

        return false ;
    }

}
