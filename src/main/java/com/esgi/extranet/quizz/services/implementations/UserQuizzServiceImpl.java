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
    public UserQuizzEntity addUserQuizz(Long userId, Long surveyId, float score)
    {
        UserQuizzEntity userQuizzEntity = UserQuizzEntity.builder()
                .userId(userId)
                .surveyId(surveyId)
                .score(score)
                .build() ;

        userQuizzRepository.save(userQuizzEntity) ;

        return userQuizzEntity ;
    }

    @Override
    @Transactional
    public UserQuizzEntity updateUserQuizz(Long userQuizzId, Long userId, Long surveyId, float score)
    {
        UserQuizzEntity userQuizzEntity = userQuizzRepository.findById(userQuizzId) ;

        userQuizzEntity.setUserId(userId) ;
        userQuizzEntity.setSurveyId(surveyId) ;
        userQuizzEntity.setScore(score) ;

        userQuizzRepository.save(userQuizzEntity) ;

        return userQuizzEntity ;
    }

    @Override
    @Transactional
    public float updateUserQuizzScore(Long userQuizzId, float score)
    {
        UserQuizzEntity userQuizzEntity = userQuizzRepository.findById(userQuizzId) ;

        userQuizzEntity.setScore(score) ;

        userQuizzRepository.save(userQuizzEntity) ;

        return score ;
    }

    @Override
    @Transactional
    public boolean removeUserQuizz(Long userQuizzId)
    {
        userQuizzRepository.delete(userQuizzId) ;

        return (userQuizzRepository.findById(userQuizzId) == null) ;
    }

    @Override
    public List<UserQuizzEntity> getAllUsersQuizzBySurveyId(Long surveyId)
    {
        return userQuizzRepository.findAllBySurveyId(surveyId) ;
    }

    @Override
    public List<UserQuizzEntity> getAllUsersQuizzByUserId(Long userId)
    {
        return userQuizzRepository.findAllByUserId(userId) ;
    }

    @Override
    public UserQuizzEntity getUserQuizz(Long userQuizzId)
    {
        return userQuizzRepository.findById(userQuizzId) ;
    }

    @Override
    public List<UserQuizzEntity> getUserQuizzsByUserIdAndSurveyId(Long userId, Long surveyId)
    {
        return userQuizzRepository.findAllByUserIdAndSurveyId(userId, surveyId) ;
    }


    @Override
    public List<UserQuizzResponsesEntity> getAllUsersQuizzResponsesFromAnUserQuizz(Long userQuizzId)
    {
        return userQuizzResponsesRepository.findByUserQuizzId(userQuizzId) ;
    }

    @Override
    public boolean removeAllUsersQuizzResponsesFromAnUserQuizz(Long userQuizzId)
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
