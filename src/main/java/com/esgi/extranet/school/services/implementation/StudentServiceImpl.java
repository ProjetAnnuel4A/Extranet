package com.esgi.extranet.school.services.implementation;

import com.esgi.extranet.school.entities.StudentEntity;
import com.esgi.extranet.school.repositories.StudentRepository;
import com.esgi.extranet.school.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
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
    public StudentEntity addStudent(String firstname, String lastname, String email, LocalDate birthday, String photo, String address) {
        StudentEntity studentEntity = StudentEntity.builder()
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .birthday(birthday)
                .photo(photo)
                .address(address)
                .build();

        studentRepository.save(studentEntity);
        return studentEntity;
    }

    @Override
    @Transactional
    public StudentEntity updateStudent(String firstname, String lastname, String email, LocalDate date, String photo, String address, Long id) {
        StudentEntity studentEntity = studentRepository.findById(id);
        studentEntity.setFirstname(firstname);
        studentEntity.setLastname(lastname);
        studentEntity.setBirthday(date);
        studentEntity.setEmail(email);
        studentEntity.setAddress(address);
        studentEntity.setPhoto(photo);
        studentRepository.save(studentEntity);
        return studentEntity;
    }

    @Override
    @Transactional
    public boolean removeStudent(Long id) {
        studentRepository.delete(id);
        return (studentRepository.findById(id) == null);
    }

    @Override
    @Transactional
    public StudentEntity getStudent(Long id) {
        return studentRepository.findById(id);
    }
}
