package com.esgi.extranet.school.services.implementation;

import com.esgi.extranet.school.entities.MarkEntity;
import com.esgi.extranet.school.repositories.MarkRepository;
import com.esgi.extranet.school.services.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author timotheearnauld
 */
@Service
public class MarkServiceImpl implements MarkService{
    private final MarkRepository markRepository;

    @Autowired
    public MarkServiceImpl(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    @Override
    @Transactional
    public MarkEntity addMark(Long idStudent, Long idTeacher, Long idCourse, Long mark) {
        MarkEntity markEntity = MarkEntity.builder()
                .idStudent(idStudent)
                .idTeacher(idTeacher)
                .idCourse(idCourse)
                .mark(mark)
                .build();
        markRepository.save(markEntity);
        return markEntity;
    }

    @Override
    @Transactional
    public boolean removeMark(Long id) {
        markRepository.delete(id);
        return (markRepository.findById(id) == null);
    }
}
