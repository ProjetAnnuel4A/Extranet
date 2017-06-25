package com.esgi.extranet.school.repositories;

import com.esgi.extranet.school.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author timotheearnauld
 */
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long>{
    Optional<StudentEntity> findByFirstname(String firstname);
    Optional<StudentEntity> findByLastname(String lastname);
    StudentEntity findById(Long id);
    List<StudentEntity> findAllByIdClassmate(Long idClassmate);
}
