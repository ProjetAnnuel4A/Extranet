package com.esgi.extranet.administration.repositories;

import com.esgi.extranet.administration.entities.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author timotheearnauld
 */
@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long>{
    Optional<TeacherEntity> findByFirstname(String firstname);
    Optional<TeacherEntity> findByLastname(String lastname);
    TeacherEntity findById(Long id);
}
