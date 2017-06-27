package com.esgi.extranet.planning.repositories;

import com.esgi.extranet.planning.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author timotheearnauld
 */
@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long>{
    ScheduleEntity findById(Long id);
}
