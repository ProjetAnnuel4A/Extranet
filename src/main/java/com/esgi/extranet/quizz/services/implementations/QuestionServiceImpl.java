package com.esgi.extranet.quizz.services.implementations ;

import com.esgi.extranet.quizz.entities.QuestionEntity ;
import com.esgi.extranet.quizz.entities.ResponseEntity ;
import com.esgi.extranet.quizz.repositories.QuestionRepository ;
import com.esgi.extranet.quizz.repositories.ResponseRepository ;
import com.esgi.extranet.quizz.services.interfaces.QuestionService ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.stereotype.Service ;

import javax.transaction.Transactional ;
import java.util.List ;

/**
 * Created by Samuel Bijou on 27/06/2017.
 */
@Service
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
    public QuestionEntity addQuestion(String description, float points, boolean allOrNot, String imagePath)
    {
        QuestionEntity questionEntity = QuestionEntity.builder()
                .description(description)
                .points(points)
                .allOrNot(allOrNot)
                .imagePath(imagePath)
                .build() ;

        questionRepository.save(questionEntity) ;

        return questionEntity ;
    }

    @Override
    @Transactional
    public QuestionEntity updateQuestion(Long questionId, String description, float points, boolean allOrNot, String imagePath)
    {
        QuestionEntity questionEntity = questionRepository.findById(questionId) ;

        questionEntity.setDescription(description) ;
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
    public List<ResponseEntity> getResponsesFromAQuestion(Long questionId)
    {
        QuestionEntity questionEntity = questionRepository.findById(questionId) ;

        return questionEntity.getResponses() ;
    }

    @Override
    @Transactional
    public boolean addResponseForAQuestion(Long questionId, Long responseId)
    {

        QuestionEntity questionEntity = questionRepository.findById(questionId) ;
        ResponseEntity responseEntity = responseRepository.findById(responseId) ;

        questionEntity.getResponses().add(responseEntity) ;

        questionRepository.save(questionEntity) ;

        return true ;
    }

    @Override
    @Transactional
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

    @Override
    public List<Long> getCorrectResponsesFromAQuestion(Long questionId)
    {
        QuestionEntity questionEntity = questionRepository.findById(questionId) ;

        return questionEntity.getCorrectResponses() ;
    }

    @Override
    @Transactional
    public boolean addCorrectResponseForAQuestion(Long questionId, Long responseId)
    {

        QuestionEntity questionEntity = questionRepository.findById(questionId) ;

        questionEntity.getCorrectResponses().add(responseId) ;

        questionRepository.save(questionEntity) ;

        return true ;
    }

    @Override
    @Transactional
    public boolean removeCorrectResponseFromAQuestion(Long questionId, Long responseId)
    {
        QuestionEntity questionEntity = questionRepository.findById(questionId) ;
        List<Long> correctResponses = questionEntity.getCorrectResponses() ;

        for(int i = 0 ; i < correctResponses.size() ; i++)
        {
            if(correctResponses.get(i).equals(responseId))
            {
                correctResponses.remove(i) ;
                questionRepository.save(questionEntity) ;

                return true ;
            }
        }

        return false ;
    }

}
