package com.esgi.extranet.school.services.implementation;

import com.esgi.extranet.school.entities.StudentEntity;
import com.esgi.extranet.school.repositories.StudentRepository;
import com.esgi.extranet.school.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author timotheearnauld
 */
@Service
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentEntity> getAll() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional
    public StudentEntity addStudent(String firstname, String lastname) {
        StudentEntity studentEntity = StudentEntity.builder()
                .firstname(firstname)
                .lastname(lastname)
                .build();

        studentRepository.save(studentEntity);
        return studentEntity;
    }

    @Override
    @Transactional
    public boolean removeStudent(Long id) {
        studentRepository.delete(id);
        return (studentRepository.findById(id) == null);
    }
}
