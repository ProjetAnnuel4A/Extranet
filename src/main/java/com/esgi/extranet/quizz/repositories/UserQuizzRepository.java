package com.esgi.extranet.quizz.repositories ;

import com.esgi.extranet.quizz.entities.UserQuizzEntity ;
import org.springframework.data.jpa.repository.JpaRepository ;
import org.springframework.stereotype.Repository ;

import java.util.List ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@Repository
public interface UserQuizzRepository extends JpaRepository<UserQuizzEntity, Long>
{

    UserQuizzEntity findById(Long id) ;
    List<UserQuizzEntity> findAllByUserId(Long userId) ;
    UserQuizzEntity findByUserIdAndSurveyId(Long userId, Long surveyId) ;

}
