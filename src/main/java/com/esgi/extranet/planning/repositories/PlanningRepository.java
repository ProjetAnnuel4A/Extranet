package com.esgi.extranet.planning.repositories;

import com.esgi.extranet.planning.entities.PlanningEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author timotheearnauld
 */
@Repository
public interface PlanningRepository extends JpaRepository<PlanningEntity, Long>{
    PlanningEntity findById(Long id);
    PlanningEntity findByIdClassmate(Long idClassmate);
}
