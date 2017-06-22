package com.esgi.extranet.administration.services.implementation;


import com.esgi.extranet.administration.entities.TeacherEntity;
import com.esgi.extranet.administration.repositories.TeacherRepository;
import com.esgi.extranet.administration.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author timotheearnauld
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    @Override
    @Transactional
    public TeacherEntity addTeacher(String firstname, String lastname) {
        TeacherEntity teacherEntity = TeacherEntity.builder()
                .firstname(firstname)
                .lastname(lastname)
                .build();
        teacherRepository.save(teacherEntity);
        return teacherEntity;
    }

    @Override
    @Transactional
    public boolean removeTeacher(Long id) {
        teacherRepository.delete(id);
        return (teacherRepository.findById(id) == null);
    }
}
