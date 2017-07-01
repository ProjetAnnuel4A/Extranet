package com.esgi.extranet.quizz.services.implementations ;

import com.esgi.extranet.quizz.entities.UserQuizzEntity ;
import com.esgi.extranet.quizz.repositories.UserQuizzRepository ;
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


    @Autowired
    public UserQuizzServiceImpl(UserQuizzRepository userQuizzRepository)
    {
        this.userQuizzRepository = userQuizzRepository ;
    }

    @Override
    public List<UserQuizzEntity> getAll()
    {
        return userQuizzRepository.findAll() ;
    }

    @Override
    @Transactional
    public UserQuizzEntity addUserQuizz(Long userId, Long surveyId, Long questionId, int count)
    {
        UserQuizzEntity userQuizzEntity = UserQuizzEntity.builder()
                .userId(userId)
                .surveyId(surveyId)
                .questionId(questionId)
                .count(count)
                .build() ;

        userQuizzRepository.save(userQuizzEntity) ;

        return userQuizzEntity ;
    }

    @Override
    @Transactional
    public UserQuizzEntity updateUserQuizz(Long userQuizzId, Long userId, Long surveyId, Long questionId, int count)
    {
        UserQuizzEntity userQuizzEntity = userQuizzRepository.findById(userQuizzId) ;

        userQuizzEntity.setId(userQuizzId) ;
        userQuizzEntity.setUserId(userId) ;
        userQuizzEntity.setSurveyId(surveyId) ;
        userQuizzEntity.setQuestionId(questionId) ;
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
    public List<Long> getResponsesFromAnUserQuizz(Long userQuizzId)
    {
        UserQuizzEntity userQuizzEntity = userQuizzRepository.findById(userQuizzId) ;

        return userQuizzEntity.getResponses() ;
    }

    @Override
    @Transactional
    public boolean addResponseForAnUserQuizz(Long userQuizzId, Long responseId)
    {
        UserQuizzEntity userQuizzEntity = userQuizzRepository.findById(userQuizzId) ;

        userQuizzEntity.getResponses().add(responseId) ;

        userQuizzRepository.save(userQuizzEntity) ;

        return true ;
    }

    @Override
    @Transactional
    public boolean removeResponseFromAnUserQuizz(Long userQuizzId, Long reponseId)
    {
        UserQuizzEntity userQuizzEntity = userQuizzRepository.findById(userQuizzId) ;
        List<Long> userResponses = userQuizzEntity.getResponses() ;

        for(int i = 0 ; i < userResponses.size() ; i++)
        {
            if(userResponses.get(i).equals(reponseId))
            {
                userResponses.remove(i) ;
                userQuizzRepository.save(userQuizzEntity) ;

                return true ;
            }
        }

        return false ;
    }

}
