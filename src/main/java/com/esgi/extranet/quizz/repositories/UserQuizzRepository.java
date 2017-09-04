package com.esgi.extranet.quizz.repositories ;

import com.esgi.extranet.quizz.entities.UserQuizzEntity ;
import org.springframework.data.jpa.repository.JpaRepository ;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository ;

import java.util.List ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@Repository
public interface UserQuizzRepository extends JpaRepository<UserQuizzEntity, Long>
{

    UserQuizzEntity findById(Long id) ;
    @Query(nativeQuery = true, value = "select * from user_quizz uq where uq.survey_id = :surveyId")
    List<UserQuizzEntity> findAllBySurveyId(@Param("surveyId") Long surveyId) ;
    @Query(nativeQuery = true, value = "select * from user_quizz uq where uq.user_id = :userId")
    List<UserQuizzEntity> findAllByUserId(@Param("userId") Long userId) ;
    @Query(nativeQuery = true, value = "select * from user_quizz uq where uq.user_id = :userId and uq.survey_id = :surveyId")
    List<UserQuizzEntity> findAllByUserIdAndSurveyId(@Param("userId") Long userId,
                                                     @Param("surveyId") Long surveyId) ;

}
