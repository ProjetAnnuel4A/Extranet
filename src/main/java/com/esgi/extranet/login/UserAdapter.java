package com.esgi.extranet.login;

/**
 * @author timotheearnauld
 */
public class UserAdapter {

    public static UserDto toDto(User user){
        return UserDto.builder()
                .email(user.getEmail())
                .pseudo(user.getPseudo())
                .password(user.getPassword())
                .token(user.getToken())
                .build();
    }

    public static User toUser(UserDto dto){
        return User.builder()
                .pseudo(dto.getPseudo())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .token(dto.getToken())
                .build();
    }
}
