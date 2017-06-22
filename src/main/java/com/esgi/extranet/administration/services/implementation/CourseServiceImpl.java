package com.esgi.extranet.administration.services.implementation;

import com.esgi.extranet.administration.entities.CourseEntity;
import com.esgi.extranet.administration.repositories.CourseRepository;
import com.esgi.extranet.administration.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author timotheearnauld
 */
@Service
public class CourseServiceImpl implements CourseService{
    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public CourseEntity addCourse(String coursename) {
        CourseEntity courseEntity = CourseEntity.builder()
                .coursename(coursename)
                .build();
        courseRepository.save(courseEntity);
        return courseEntity;
    }

    @Override
    @Transactional
    public boolean removeCourse(Long id) {
        courseRepository.delete(id);
        return (courseRepository.findById(id) == null);
    }
}
