package com.esgi.extranet.school.services;

import com.esgi.extranet.school.entities.ClassmateEntity;

/**
 * @author timotheearnauld
 */
public interface ClassmateService {
    ClassmateEntity addClassmate(String classmateName);
    boolean removeClassmate(Long id);
}
