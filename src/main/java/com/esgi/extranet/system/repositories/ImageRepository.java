package com.esgi.extranet.system.repositories ;

import com.esgi.extranet.system.entities.ImageEntity ;
import org.springframework.data.jpa.repository.JpaRepository ;
import org.springframework.stereotype.Repository ;

import java.util.ArrayList ;

/**
 * Created by Samuel Bijou on 28/06/2017.
 */
@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long>
{

    ImageEntity findById(Long id) ;

}
