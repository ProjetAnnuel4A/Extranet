package com.esgi.extranet.administration.services.implementation;


import com.esgi.extranet.administration.services.TeacherService;
import com.esgi.extranet.login.MailClient;
import com.esgi.extranet.login.Role;
import com.esgi.extranet.login.Service.UserServices;
import com.esgi.extranet.login.UserEntity;
import com.esgi.extranet.login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

/**
 * @author timotheearnauld
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    private UserRepository userRepository;
    private final MailClient mailClient;
    private UserServices userServices;
    final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public TeacherServiceImpl(UserServices userServices, UserRepository userRepository, MailClient mailClient, BCryptPasswordEncoder passwordEncoder) {
        this.userServices = userServices;
        this.userRepository = userRepository;
        this.mailClient = mailClient;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public UserEntity addTeacher(String firstname, String lastname, String email, String password, LocalDate birthday, String photo, String address) {
        UserEntity teacherEntity = UserEntity.builder()
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .password(passwordEncoder.encode(password))
                .pseudo(firstname+lastname)
                .role(Role.TEACHER)
                .birthday(birthday)
                .photo(photo)
                .address(address)
                .build();
        //userRepository.save(teacherEntity);
        userServices.saveUser(teacherEntity);
        String object = "Création de compte étudiant - Brotherhood";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("email/email.html").getFile());
        String message = null;
        try {
            message = String.join("", Files.readAllLines(Paths.get(file.getPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        message = message.replace("*firstname*", firstname);
        message = message.replace("*role*", "Enseignant");
        message = message.replace("*pseudo*", firstname+lastname);
        message = message.replace("*password*", password);
        mailClient.prepareAndSend(email, object, message);
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
    public UserEntity updateTeacher(String firstname, String lastname, String email, String password, LocalDate date, String photo, String address, Long id) {
        UserEntity teacherEntity = userRepository.findById(id);
        teacherEntity.setFirstname(firstname);
        teacherEntity.setLastname(lastname);
        teacherEntity.setBirthday(date);
        teacherEntity.setEmail(email);
        teacherEntity.setPassword(passwordEncoder.encode(password));
        teacherEntity.setAddress(address);
        teacherEntity.setPhoto(photo);
        //userRepository.save(teacherEntity);
        userServices.saveUser(teacherEntity);
        return teacherEntity;
    }
}
