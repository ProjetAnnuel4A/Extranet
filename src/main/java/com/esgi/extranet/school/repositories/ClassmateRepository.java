package com.esgi.extranet.school.repositories;

import com.esgi.extranet.school.entities.ClassmateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author timotheearnauld
 */
@Repository
public interface ClassmateRepository extends JpaRepository<ClassmateEntity, Long>{
    ClassmateEntity findByClassmateName(String classmateName);
    ClassmateEntity findById(Long id);
}
