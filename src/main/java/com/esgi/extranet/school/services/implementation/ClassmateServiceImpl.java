package com.esgi.extranet.school.services.implementation;

import com.esgi.extranet.administration.entities.TeacherEntity;
import com.esgi.extranet.school.entities.ClassmateEntity;
import com.esgi.extranet.school.entities.StudentEntity;
import com.esgi.extranet.school.repositories.ClassmateRepository;
import com.esgi.extranet.school.services.ClassmateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author timotheearnauld
 */
@Service
public class ClassmateServiceImpl implements ClassmateService{
    ClassmateRepository classmateRepository;

    @Autowired
    public ClassmateServiceImpl(ClassmateRepository classmateRepository) {
        this.classmateRepository = classmateRepository;
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
    public boolean getTeachersFromClassmate(Long idClassmate, Long idTeacher) {
        ClassmateEntity classmateEntity = classmateRepository.findById(idClassmate);
        List<TeacherEntity> teacherEntities = classmateEntity.getTeacherEntities();
        for(int i = 0; i < teacherEntities.size(); i++){
            if(teacherEntities.get(i).getId().equals(idTeacher)){
                teacherEntities.remove(i);
                break;
            }
        }
        classmateRepository.save(classmateEntity);
        return true;
    }
}
