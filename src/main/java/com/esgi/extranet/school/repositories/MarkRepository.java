package com.esgi.extranet.school.repositories;

import com.esgi.extranet.school.entities.MarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author timotheearnauld
 */
@Repository
public interface MarkRepository extends JpaRepository<MarkEntity, Long>{
    Optional<MarkEntity> findById(Long id);
}
