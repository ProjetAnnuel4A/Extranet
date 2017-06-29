package com.esgi.extranet.quizz.services.implementations ;

import com.esgi.extranet.quizz.entities.UserResponsesEntity ;
import com.esgi.extranet.quizz.repositories.UserResponsesRepository ;
import com.esgi.extranet.quizz.services.interfaces.UserResponsesService ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.stereotype.Service ;

import javax.transaction.Transactional ;
import java.util.List ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@Service
public class UserResponsesServiceImpl implements UserResponsesService
{

    private UserResponsesRepository userResponsesRepository ;


    @Autowired
    public UserResponsesServiceImpl(UserResponsesRepository userResponsesRepository)
    {
        this.userResponsesRepository = userResponsesRepository ;
    }

    @Override
    public List<UserResponsesEntity> getAll()
    {
        return userResponsesRepository.findAll() ;
    }

    @Override
    @Transactional
    public UserResponsesEntity addUserResponses(Long userId, Long surveyId, Long questionId)
    {
        UserResponsesEntity userResponsesEntity = UserResponsesEntity.builder()
                .userId(userId)
                .surveyId(surveyId)
                .questionId(questionId)
                .build() ;

        userResponsesRepository.save(userResponsesEntity) ;

        return userResponsesEntity ;
    }

    @Override
    @Transactional
    public UserResponsesEntity updateUserResponses(Long userResponsesId, Long userId, Long surveyId, Long questionId)
    {
        UserResponsesEntity userResponsesEntity = userResponsesRepository.findById(userResponsesId) ;

        userResponsesEntity.setId(userResponsesId) ;
        userResponsesEntity.setUserId(userId) ;
        userResponsesEntity.setSurveyId(surveyId) ;
        userResponsesEntity.setQuestionId(questionId) ;

        userResponsesRepository.save(userResponsesEntity) ;

        return userResponsesEntity ;
    }

    @Override
    @Transactional
    public boolean removeUserResponses(Long userResponsesId)
    {
        userResponsesRepository.delete(userResponsesId) ;

        return (userResponsesRepository.findById(userResponsesId) == null) ;
    }

    @Override
    public UserResponsesEntity getUserResponses(Long userResponsesId)
    {
        return userResponsesRepository.findById(userResponsesId) ;
    }


    @Override
    public List<Long> getResponsesFromAUserResponses(Long userResponsesId)
    {
        UserResponsesEntity userResponsesEntity = userResponsesRepository.findById(userResponsesId) ;

        return userResponsesEntity.getResponses() ;
    }

    @Override
    @Transactional
    public boolean addResponseForAUserResponses(Long userResponsesId, Long responseId)
    {
        UserResponsesEntity userResponsesEntity = userResponsesRepository.findById(userResponsesId) ;

        userResponsesEntity.getResponses().add(responseId) ;

        userResponsesRepository.save(userResponsesEntity) ;

        return true ;
    }

    @Override
    @Transactional
    public boolean removeResponseFromAUserResponses(Long userResponsesId, Long reponseId)
    {
        UserResponsesEntity userResponsesEntity = userResponsesRepository.findById(userResponsesId) ;
        List<Long> userResponses = userResponsesEntity.getResponses() ;

        for(int i = 0 ; i < userResponses.size() ; i++)
        {
            if(userResponses.get(i).equals(reponseId))
            {
                userResponses.remove(i) ;
                userResponsesRepository.save(userResponsesEntity) ;

                return true ;
            }
        }

        return false ;
    }

}
