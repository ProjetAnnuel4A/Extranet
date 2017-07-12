package com.esgi.extranet.school.services.implementation;

import com.esgi.extranet.login.MailClient;
import com.esgi.extranet.login.Role;
import com.esgi.extranet.login.Service.UserServices;
import com.esgi.extranet.login.UserEntity;
import com.esgi.extranet.login.UserRepository;
import com.esgi.extranet.school.services.StudentService;
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
public class StudentServiceImpl implements StudentService{
    private final UserRepository userRepository;
    private final UserServices userServices;
    private final MailClient mailClient;
    final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public StudentServiceImpl(UserServices userServices, UserRepository userRepository, MailClient mailClient, BCryptPasswordEncoder passwordEncoder) {
        this.userServices = userServices;
        this.userRepository = userRepository;
        this.mailClient = mailClient;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserEntity> getAllStudents() {
        return userRepository.findAllStudents();
    }

    @Override
    @Transactional
    public UserEntity addStudent(String firstname, String lastname, String email, String password, LocalDate birthday, String photo, String address) {
        UserEntity studentEntity = UserEntity.builder()
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.STUDENT)
                .pseudo(firstname+lastname)
                .birthday(birthday)
                .photo(photo)
                .address(address)
                .build();

        //userRepository.save(studentEntity);
        userServices.saveUser(studentEntity);
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
        message = message.replace("*role*", "Etudiant");
        message = message.replace("*pseudo*", firstname+lastname);
        message = message.replace("*password*", password);
        mailClient.prepareAndSend(email, object, message);
        return studentEntity;
    }

    @Override
    @Transactional
    public UserEntity updateStudent(String firstname, String lastname, String email, String password, LocalDate date, String photo, String address, Long id){
        UserEntity studentEntity = userRepository.findById(id);
        studentEntity.setFirstname(firstname);
        studentEntity.setLastname(lastname);
        studentEntity.setBirthday(date);
        studentEntity.setEmail(email);
        studentEntity.setPassword(passwordEncoder.encode(password));
        studentEntity.setAddress(address);
        studentEntity.setPhoto(photo);
        userServices.saveUser(studentEntity);
        //userRepository.save(studentEntity);
        return studentEntity;
    }

    @Override
    @Transactional
    public boolean removeStudent(Long id) {
        userRepository.delete(id);
        return (userRepository.findById(id) == null);
    }

    @Override
    @Transactional
    public UserEntity getStudent(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean addClassmateForStudent(Long studentId, Long classmateId) {
        UserEntity studentEntity = userRepository.getOne(studentId);
        studentEntity.setIdClassmate(classmateId);
        userRepository.save(studentEntity);

        return (userRepository.getOne(studentId).getIdClassmate().equals(classmateId));
    }

    @Override
    public boolean removeClassmateForStudent(Long studentId) {
        UserEntity studentEntity = userRepository.getOne(studentId);
        studentEntity.setIdClassmate(null);
        userRepository.save(studentEntity);

        return (userRepository.getOne(studentId).getIdClassmate() == null);
    }

    @Override
    public List<UserEntity> getStudentsForClassmate(Long idClassmate) {
        return userRepository.findAllByIdClassmate(idClassmate);
    }

    @Override
    public List<UserEntity> getStudentsWithoutClassmate() {
        return userRepository.findAllByIdClassmate(null);
    }
}
