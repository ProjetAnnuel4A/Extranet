package com.esgi.extranet.QCM.repositories ;

import com.esgi.extranet.QCM.entities.QuestionEntity ;
import org.springframework.data.jpa.repository.JpaRepository ;
import org.springframework.stereotype.Repository ;

import java.util.ArrayList ;

/**
 * Created by Samuel Bijou on 27/06/2017.
 */
@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long>
{

    QuestionEntity findById(Long id) ;
    ArrayList<QuestionEntity> findAllByIdSurvey(Long idSurvey) ;

}
