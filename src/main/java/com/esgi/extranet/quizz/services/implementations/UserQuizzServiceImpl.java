package com.esgi.extranet.quizz.services.implementations ;

import com.esgi.extranet.quizz.entities.UserQuizzEntity ;
import com.esgi.extranet.quizz.entities.UserQuizzResponsesEntity ;
import com.esgi.extranet.quizz.repositories.UserQuizzRepository ;
import com.esgi.extranet.quizz.repositories.UserQuizzResponsesRepository ;
import com.esgi.extranet.quizz.services.interfaces.UserQuizzService ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.stereotype.Service ;

import javax.transaction.Transactional ;
import java.util.List ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@Service
public class UserQuizzServiceImpl implements UserQuizzService
{

    private UserQuizzRepository userQuizzRepository ;
    private UserQuizzResponsesRepository userQuizzResponsesRepository ;


    @Autowired
    public UserQuizzServiceImpl(UserQuizzRepository userQuizzRepository)
    {
        this.userQuizzRepository = userQuizzRepository ;
        this.userQuizzResponsesRepository = null ;
    }

    public UserQuizzServiceImpl(UserQuizzRepository userQuizzRepository, UserQuizzResponsesRepository userQuizzResponsesRepository)
    {
        this.userQuizzRepository = userQuizzRepository ;
        this.userQuizzResponsesRepository = userQuizzResponsesRepository ;
    }


    @Override
    public List<UserQuizzEntity> getAll()
    {
        return userQuizzRepository.findAll() ;
    }

    @Override
    @Transactional
    public UserQuizzEntity addUserQuizz(Long userId, Long surveyId, int count)
    {
        UserQuizzEntity userQuizzEntity = UserQuizzEntity.builder()
                .userId(userId)
                .surveyId(surveyId)
                .count(count)
                .build() ;

        userQuizzRepository.save(userQuizzEntity) ;

        return userQuizzEntity ;
    }

    @Override
    @Transactional
    public UserQuizzEntity updateUserQuizz(Long userQuizzId, Long userId, Long surveyId, int count)
    {
        UserQuizzEntity userQuizzEntity = userQuizzRepository.findById(userQuizzId) ;

        userQuizzEntity.setId(userQuizzId) ;
        userQuizzEntity.setUserId(userId) ;
        userQuizzEntity.setSurveyId(surveyId) ;
        userQuizzEntity.setCount(count) ;

        userQuizzRepository.save(userQuizzEntity) ;

        return userQuizzEntity ;
    }

    @Override
    @Transactional
    public boolean removeUserQuizz(Long userQuizzId)
    {
        userQuizzRepository.delete(userQuizzId) ;

        return (userQuizzRepository.findById(userQuizzId) == null) ;
    }

    @Override
    public UserQuizzEntity getUserQuizz(Long userQuizzId)
    {
        return userQuizzRepository.findById(userQuizzId) ;
    }

    @Override
    public UserQuizzEntity getUsersQuizzByUserIdAndSurveyId(Long userId, Long surveyId)
    {
        return userQuizzRepository.findByUserIdAndSurveyId(userId, surveyId) ;
    }


    @Override
    public List<UserQuizzResponsesEntity> getAllUserQuizzResponsesFromAnUserQuizz(Long userQuizzId)
    {
        return userQuizzResponsesRepository.findByUserQuizzId(userQuizzId) ;
    }

    @Override
    public boolean removeAllUserQuizzResponsesFromAnUserQuizz(Long userQuizzId)
    {
        List<UserQuizzResponsesEntity> userQuizzResponsesEntity = userQuizzResponsesRepository.findByUserQuizzId(userQuizzId) ;

        for(int i = 0 ; i < userQuizzResponsesEntity.size() ; i++)
        {
            userQuizzResponsesRepository.delete(userQuizzResponsesEntity.get(i).getId()) ;
        }

        return true ;
    }

    @Override
    public UserQuizzResponsesEntity getUserQuizzResponsesFromAnUserQuizz(Long userQuizzId, Long questionId)
    {
        return userQuizzResponsesRepository.findByUserQuizzIdAndQuestionId(userQuizzId, questionId) ;
    }

    @Override
    public List<Long> getResponsesFromAnUserQuizz(Long userQuizzId, Long questionId)
    {
        UserQuizzResponsesEntity userQuizzResponsesEntity = userQuizzResponsesRepository.findByUserQuizzIdAndQuestionId(userQuizzId, questionId) ;

        return userQuizzResponsesEntity.getResponses() ;
    }

    @Override
    @Transactional
    public boolean addResponseForAnUserQuizz(Long userQuizzId, Long questionId, Long responseId)
    {
        UserQuizzResponsesEntity userQuizzResponsesEntity = userQuizzResponsesRepository.findByUserQuizzIdAndQuestionId(userQuizzId, questionId) ;

        userQuizzResponsesEntity.getResponses().add(responseId) ;

        userQuizzResponsesRepository.save(userQuizzResponsesEntity) ;

        return true ;
    }

    @Override
    @Transactional
    public boolean removeResponseFromAnUserQuizz(Long userQuizzId, Long questionId, Long reponseId)
    {
        UserQuizzResponsesEntity userQuizzResponsesEntity = userQuizzResponsesRepository.findByUserQuizzIdAndQuestionId(userQuizzId, questionId) ;
        List<Long> userResponses = userQuizzResponsesEntity.getResponses() ;

        for(int i = 0 ; i < userResponses.size() ; i++)
        {
            if(userResponses.get(i).equals(reponseId))
            {
                userResponses.remove(i) ;
                userQuizzResponsesEntity.setResponses(userResponses) ;

                userQuizzResponsesRepository.save(userQuizzResponsesEntity) ;

                return true ;
            }
        }

        return false ;
    }

}
