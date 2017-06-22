package com.esgi.extranet.administration.repositories;

import com.esgi.extranet.administration.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author timotheearnauld
 */
@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long>{
    Optional<CourseEntity> findByCoursename(String coursename);
    Optional<CourseEntity> findById(Long id);
}
