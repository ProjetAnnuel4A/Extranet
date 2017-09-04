package com.esgi.extranet.login;

/**
 * @author timotheearnauld
 */
public class UserAdapter {

    public static UserDto toDto(UserEntity userEntity){
        return UserDto.builder()
                .email(userEntity.getEmail())
                .pseudo(userEntity.getPseudo())
                .password(userEntity.getPassword())
                .token(userEntity.getToken())
                .build();
    }

    public static UserEntity toUser(UserDto dto){
        return UserEntity.builder()
                .pseudo(dto.getPseudo())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .token(dto.getToken())
                .build();
    }
}
