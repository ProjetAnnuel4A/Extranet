package com.esgi.extranet.quizz.repositories ;

import com.esgi.extranet.quizz.entities.UserResponsesEntity ;
import org.springframework.data.jpa.repository.JpaRepository ;
import org.springframework.stereotype.Repository ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@Repository
public interface UserResponsesRepository extends JpaRepository<UserResponsesEntity, Long>
{

    UserResponsesEntity findById(Long id) ;

}
