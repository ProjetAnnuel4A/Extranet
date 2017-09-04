package com.esgi.extranet.login.Service;

import org.springframework.stereotype.Service;

/**
 * @author timotheearnauld
 */
@Service
public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String pseudo, String password);
}
