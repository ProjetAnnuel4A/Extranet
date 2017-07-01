package com.esgi.extranet.quizz.repositories ;

import com.esgi.extranet.quizz.entities.ResponseEntity ;
import org.springframework.data.jpa.repository.JpaRepository ;
import org.springframework.stereotype.Repository ;

import java.util.ArrayList ;

/**
 * Created by Samuel Bijou on 27/06/2017.
 */
@Repository
public interface ResponseRepository extends JpaRepository<ResponseEntity, Long>
{

    ResponseEntity findById(Long id) ;

}
