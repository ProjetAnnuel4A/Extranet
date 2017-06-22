package com.esgi.extranet.school.services;

import com.esgi.extranet.school.entities.ClassmateEntity;

import java.util.List;

/**
 * @author timotheearnauld
 */
public interface ClassmateService {
    List<ClassmateEntity> getAll();
    ClassmateEntity addClassmate(String classmateName);
    boolean removeClassmate(Long id);
}
