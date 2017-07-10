package com.esgi.extranet.school.services.implementation;

import com.esgi.extranet.administration.entities.CourseEntity;
import com.esgi.extranet.administration.repositories.CourseRepository;
import com.esgi.extranet.login.UserEntity;
import com.esgi.extranet.login.UserRepository;
import com.esgi.extranet.planning.services.PlanningService;
import com.esgi.extranet.school.entities.ClassmateEntity;
import com.esgi.extranet.school.repositories.ClassmateRepository;
import com.esgi.extranet.school.services.ClassmateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author timotheearnauld
 */
@Service
public class ClassmateServiceImpl implements ClassmateService{
    ClassmateRepository classmateRepository;
    UserRepository userRepository;
    CourseRepository courseRepository;
    PlanningService planningService;

    @Autowired
    public ClassmateServiceImpl(PlanningService planningService, ClassmateRepository classmateRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.planningService = planningService;
        this.classmateRepository = classmateRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<ClassmateEntity> getAll() {
        return classmateRepository.findAll();
    }

    @Override
    public ClassmateEntity addClassmate(String classmateName) {
        ClassmateEntity classmateEntity = ClassmateEntity.builder()
                .classmateName(classmateName)
                .build();
        classmateRepository.save(classmateEntity);
        planningService.createPlanning(classmateName, classmateEntity.getId());
        return classmateEntity;
    }

    @Override
    public boolean removeClassmate(Long id) {
        classmateRepository.delete(id);
        return (classmateRepository.findById(id) == null);
    }

    @Override
    public ClassmateEntity getClassmate(Long id) {
        return classmateRepository.findById(id);
    }

    @Override
    public boolean removeTeachersFromClassmate(Long idClassmate, Long idTeacher) {
        ClassmateEntity classmateEntity = classmateRepository.findById(idClassmate);
        List<UserEntity> teacherEntities = classmateEntity.getTeacherEntities();
        for(int i = 0; i < teacherEntities.size(); i++){
            if(teacherEntities.get(i).getId().equals(idTeacher)){
                teacherEntities.remove(i);
                break;
            }
        }
        classmateRepository.save(classmateEntity);
        return true;
    }

    @Override
    public boolean addTeacherForClassmate(Long idClassmate, Long idTeacher) {
        ClassmateEntity classmateEntity = classmateRepository.findById(idClassmate);
        classmateEntity.getTeacherEntities().add(userRepository.findById(idTeacher));
        classmateRepository.save(classmateEntity);
        return false;
    }

    @Override
    public List<UserEntity> getTeachersFromClassmate(Long idClassmate) {
        return classmateRepository.findById(idClassmate).getTeacherEntities();
    }

    @Override
    public List<ClassmateEntity> getClassmatesForTeacher(Long idTeacher) {
        List<ClassmateEntity> classmateEntities = classmateRepository.findAll();
        List<ClassmateEntity> result = new ArrayList<>();

        for(ClassmateEntity classmateEntity : classmateEntities){
            for(UserEntity teacherEntity : classmateEntity.getTeacherEntities()){
                if(teacherEntity.getId().equals(idTeacher)) {
                    result.add(classmateEntity);
                }
            }
        }
        return result;
    }

    @Override
    public List<CourseEntity> getCoursesForClassmate(Long idClassmate) {
        ClassmateEntity classmateEntity = classmateRepository.findById(idClassmate);
        return classmateEntity.getCourseEntities();
    }

    @Override
    public boolean addCoursesForClassmate(Long idClassmate, Long idCourse) {
        ClassmateEntity classmateEntity = classmateRepository.findById(idClassmate);
        CourseEntity courseEntity = courseRepository.findById(idCourse);

        classmateEntity.getCourseEntities().add(courseEntity);
        classmateRepository.save(classmateEntity);
        return true;
    }

    @Override
    public boolean removeCoursesForClassmate(Long idClassmate, Long idCourse) {
        ClassmateEntity classmateEntity = classmateRepository.findById(idClassmate);
        List<CourseEntity> courseEntities = classmateEntity.getCourseEntities();

        for(int i = 0; i < courseEntities.size(); i++){
            if(courseEntities.get(i).getId().equals(idCourse)){
                courseEntities.remove(i);
                break;
            }
        }
        classmateRepository.save(classmateEntity);
        return false;
    }
}
