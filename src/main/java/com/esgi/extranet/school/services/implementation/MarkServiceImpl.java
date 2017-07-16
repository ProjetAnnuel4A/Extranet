package com.esgi.extranet.school.services.implementation;

import com.esgi.extranet.school.entities.MarkEntity;
import com.esgi.extranet.school.repositories.MarkRepository;
import com.esgi.extranet.school.services.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

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
    public List<MarkEntity> getAll() {
        return markRepository.findAll();
    }

    @Override
    @Transactional
    public MarkEntity addMark(Long idStudent, Long idCourse, Float mark) {
        MarkEntity markEntity = MarkEntity.builder()
                .idStudent(idStudent)
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

    @Override
    public List<MarkEntity> getMarkForStudent(int idStudent) {
        return markRepository.getMarkForStudent(idStudent);
    }
}
