package com.esgi.extranet.administration.services.implementation;


import com.esgi.extranet.administration.entities.TeacherEntity;
import com.esgi.extranet.administration.repositories.TeacherRepository;
import com.esgi.extranet.administration.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    public List<TeacherEntity> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    @Transactional
    public TeacherEntity addTeacher(String firstname, String lastname, String email, LocalDate birthday, String photo, String address) {
        TeacherEntity teacherEntity = TeacherEntity.builder()
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .birthday(birthday)
                .photo(photo)
                .address(address)
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
