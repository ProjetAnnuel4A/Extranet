package com.esgi.extranet.administration.services.implementation;


import com.esgi.extranet.administration.services.TeacherService;
import com.esgi.extranet.login.UserEntity;
import com.esgi.extranet.login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author timotheearnauld
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    private UserRepository userRepository;

    @Autowired
    public TeacherServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public UserEntity addTeacher(String firstname, String lastname, String email, LocalDate birthday, String photo, String address) {
        UserEntity teacherEntity = UserEntity.builder()
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .birthday(birthday)
                .photo(photo)
                .address(address)
                .build();
        userRepository.save(teacherEntity);
        return teacherEntity;
    }

    @Override
    @Transactional
    public boolean removeTeacher(Long id) {
        userRepository.delete(id);
        return (userRepository.findById(id) == null);
    }

    @Override
    public UserEntity getTeacher(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity updateTeacher(String firstname, String lastname, String email, LocalDate date, String photo, String address, Long id) {
        UserEntity teacherEntity = userRepository.findById(id);
        teacherEntity.setFirstname(firstname);
        teacherEntity.setLastname(lastname);
        teacherEntity.setBirthday(date);
        teacherEntity.setEmail(email);
        teacherEntity.setAddress(address);
        teacherEntity.setPhoto(photo);
        userRepository.save(teacherEntity);
        return teacherEntity;
    }
}
