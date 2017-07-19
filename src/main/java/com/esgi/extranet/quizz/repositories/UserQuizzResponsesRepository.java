package com.esgi.extranet.quizz.repositories ;

import com.esgi.extranet.quizz.entities.UserQuizzEntity ;
import com.esgi.extranet.quizz.entities.UserQuizzResponsesEntity ;
import org.springframework.data.jpa.repository.JpaRepository ;
import org.springframework.stereotype.Repository ;

import java.util.List ;

/**
 * Created by Samuel Bijou on 16/07/2017.
 */
@Repository
public interface UserQuizzResponsesRepository extends JpaRepository<UserQuizzResponsesEntity, Long>
{

    UserQuizzResponsesEntity findById(Long id) ;
    List<UserQuizzResponsesEntity> findByUserQuizzId(Long userQuizzId) ;
    UserQuizzResponsesEntity findByUserQuizzIdAndQuestionId(Long userQuizzId, Long questionId) ;

}
