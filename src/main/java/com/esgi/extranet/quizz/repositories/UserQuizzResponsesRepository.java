package com.esgi.extranet.quizz.repositories ;

import com.esgi.extranet.quizz.entities.UserQuizzEntity ;
import com.esgi.extranet.quizz.entities.UserQuizzResponsesEntity ;
import org.springframework.data.jpa.repository.JpaRepository ;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query(nativeQuery = true, value = "select * from user_quizz_responses uqr where uqr.user_quizz_id = :userQuizzId and uqr.question_id = :questionId")
    UserQuizzResponsesEntity findByUserQuizzIdAndQuestionId(@Param("userQuizzId") Long userQuizzId,
                                                            @Param("questionId") Long questionId) ;

}
