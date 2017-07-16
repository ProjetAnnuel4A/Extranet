package com.esgi.extranet.school.repositories;

import com.esgi.extranet.school.entities.MarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author timotheearnauld
 */
@Repository
public interface MarkRepository extends JpaRepository<MarkEntity, Long>{
    Optional<MarkEntity> findById(Long id);

    @Query(nativeQuery = true,
            value = "select * from mark m where m.id_student = :idStudent")
    List<MarkEntity> getMarkForStudent(@Param("idStudent") int idStudent);
}
