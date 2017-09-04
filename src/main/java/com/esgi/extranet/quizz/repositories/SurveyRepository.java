package com.esgi.extranet.quizz.repositories ;

import com.esgi.extranet.quizz.entities.SurveyEntity ;
import org.springframework.data.jpa.repository.JpaRepository ;
import org.springframework.stereotype.Repository ;

import java.util.Optional ;

/**
 * Created by Samuel Bijou on 27/06/2017.
 */
@Repository
public interface SurveyRepository extends JpaRepository<SurveyEntity, Long>
{

    SurveyEntity findById(Long id) ;
    Optional<SurveyEntity> findByName(String name) ;

}
