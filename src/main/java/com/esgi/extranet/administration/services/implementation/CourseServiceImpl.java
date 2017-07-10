package com.esgi.extranet.administration.services.implementation;

import com.esgi.extranet.administration.entities.CourseEntity;
import com.esgi.extranet.administration.repositories.CourseRepository;
import com.esgi.extranet.administration.services.CourseService;
import com.esgi.extranet.login.UserEntity;
import com.esgi.extranet.login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author timotheearnauld
 */
@Service
public class CourseServiceImpl implements CourseService{
    private CourseRepository courseRepository;
    private UserRepository userRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<CourseEntity> getAll() {
        return courseRepository.findAll();
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

    @Override
    public CourseEntity getCourse(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public List<UserEntity> getTeachersForACourse(Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId);
        return courseEntity.getTeacherEntities();
    }

    @Override
    public boolean addTeacherForACourse(Long courseId, Long teacherId) {
        CourseEntity courseEntity = courseRepository.findById(courseId);
        UserEntity teacherEntity = userRepository.findById(teacherId);
        courseEntity.getTeacherEntities().add(teacherEntity);
        courseRepository.save(courseEntity);
        return false;
    }

    @Override
    public boolean removeTeacherFromACourse(Long courseId, Long teacherId) {
        CourseEntity courseEntity = courseRepository.findById(courseId);
        List<UserEntity>teacherEntities = courseEntity.getTeacherEntities();

        for(int i = 0; i < teacherEntities.size(); i++){
            if(teacherEntities.get(i).getId().equals(teacherId)){
                teacherEntities.remove(i);
                courseRepository.save(courseEntity);
                return true;
            }
        }
        return false;
    }
}
