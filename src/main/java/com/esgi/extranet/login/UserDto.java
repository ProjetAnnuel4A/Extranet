package com.esgi.extranet.login;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author timotheearnauld
 */
@Builder
@Getter
@Setter
public class UserDto {

    private String pseudo;

    private String email;

    private String password;

    private String token;
}
