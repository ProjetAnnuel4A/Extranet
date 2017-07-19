package com.esgi.extranet.quizz.services.implementations ;

import com.esgi.extranet.quizz.entities.UserQuizzEntity ;
import com.esgi.extranet.quizz.entities.UserQuizzResponsesEntity ;
import com.esgi.extranet.quizz.repositories.UserQuizzRepository ;
import com.esgi.extranet.quizz.repositories.UserQuizzResponsesRepository ;
import com.esgi.extranet.quizz.services.interfaces.UserQuizzResponsesService ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.stereotype.Service ;

import javax.transaction.Transactional ;
import java.util.List ;

/**
 * Created by Samuel Bijou on 16/07/2017.
 */
@Service
public class UserQuizzResponsesServiceImpl implements UserQuizzResponsesService
{

    private UserQuizzRepository userQuizzRepository ;
    private UserQuizzResponsesRepository userQuizzResponsesRepository ;


    @Autowired
    public UserQuizzResponsesServiceImpl(UserQuizzResponsesRepository userQuizzResponsesRepository)
    {
        this.userQuizzRepository = null ;
        this.userQuizzResponsesRepository = userQuizzResponsesRepository ;
    }

    public UserQuizzResponsesServiceImpl(UserQuizzRepository userQuizzRepository, UserQuizzResponsesRepository userQuizzResponsesRepository)
    {
        this.userQuizzRepository = userQuizzRepository ;
        this.userQuizzResponsesRepository = userQuizzResponsesRepository ;
    }


    @Override
    public List<UserQuizzResponsesEntity> getAll()
    {
        return userQuizzResponsesRepository.findAll() ;
    }

    @Override
    @Transactional
    public UserQuizzResponsesEntity addUserQuizzResponses(Long userQuizzId, Long questionId)
    {
        UserQuizzResponsesEntity userQuizzResponsesEntity = UserQuizzResponsesEntity.builder()
                .userQuizzId(userQuizzId)
                .questionId(questionId)
                .build() ;

        userQuizzResponsesRepository.save(userQuizzResponsesEntity) ;

        return userQuizzResponsesEntity ;
    }

    @Override
    @Transactional
    public UserQuizzResponsesEntity updateUserQuizzResponses(Long userQuizzResponsesId, Long userQuizzId, Long questionId)
    {
        UserQuizzResponsesEntity userQuizzResponsesEntity = userQuizzResponsesRepository.findById(userQuizzResponsesId) ;

        userQuizzResponsesEntity.setUserQuizzId(userQuizzId) ;
        userQuizzResponsesEntity.setQuestionId(questionId) ;

        userQuizzResponsesRepository.save(userQuizzResponsesEntity) ;

        return userQuizzResponsesEntity ;
    }

    @Override
    @Transactional
    public boolean removeUserQuizzResponses(Long userQuizzResponsesId)
    {
        userQuizzResponsesRepository.delete(userQuizzResponsesId) ;

        return (userQuizzResponsesRepository.findById(userQuizzResponsesId) == null) ;
    }

    @Override
    public UserQuizzResponsesEntity getUserQuizzResponses(Long userQuizzResponsesId)
    {
        return userQuizzResponsesRepository.findById(userQuizzResponsesId) ;
    }


    @Override
    public List<Long> getResponsesFromAnUserQuizzResponses(Long userQuizzResponsesId)
    {
        UserQuizzResponsesEntity userQuizzResponsesEntity = userQuizzResponsesRepository.findById(userQuizzResponsesId) ;

        return userQuizzResponsesEntity.getResponses() ;
    }

    @Override
    @Transactional
    public boolean addResponseForAnUserQuizzResponses(Long userQuizzResponsesId, Long responseId)
    {
        UserQuizzResponsesEntity userQuizzResponsesEntity = userQuizzResponsesRepository.findById(userQuizzResponsesId) ;

        userQuizzResponsesEntity.getResponses().add(responseId) ;

        userQuizzResponsesRepository.save(userQuizzResponsesEntity) ;

        return true ;
    }

    @Override
    @Transactional
    public boolean removeResponseFromAnUserQuizzResponses(Long userQuizzResponsesId, Long responseId)
    {
        UserQuizzResponsesEntity userQuizzResponsesEntity = userQuizzResponsesRepository.findById(userQuizzResponsesId) ;
        List<Long> userResponses = userQuizzResponsesEntity.getResponses() ;

        for(int i = 0 ; i < userResponses.size() ; i++)
        {
            if(userResponses.get(i).equals(responseId))
            {
                userResponses.remove(i) ;
                userQuizzResponsesEntity.setResponses(userResponses) ;

                userQuizzResponsesRepository.save(userQuizzResponsesEntity) ;

                return true ;
            }
        }

        return false ;
    }


    @Override
    public UserQuizzEntity getUserQuizzFromUserQuizzResponses(Long userQuizzResponsesId)
    {
        UserQuizzResponsesEntity userQuizzResponsesEntity = userQuizzResponsesRepository.findById(userQuizzResponsesId) ;

        return userQuizzRepository.findById(userQuizzResponsesEntity.getUserQuizzId()) ;
    }

}
