package com.esgi.extranet.login.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.esgi.extranet.login.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * @author timotheearnauld
 */
@Service
public class UserServices {


    final UserRepository userRepository;
    final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServices(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, DataSource dataSource, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserAdapter::toDto)
                .collect(toList());
    }

    public String createToken(String pseudo, String password ){
        String token = "";
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            token = JWT.create()
                    .withClaim("admin", false)
                    .withClaim("username", pseudo)
                    .withClaim("password", password)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return token;
    }

    @Transactional
    public UserDto createUser(String pseudo, String email, String password, Role role) {
        if(userRepository.findByPseudo(pseudo) != null){
            return null;
        }else {
            String token = createToken(pseudo, password);
            UserEntity userEntity = UserEntity.builder()
                    .pseudo(pseudo)
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .role(role)
                    .token(token)
                    .build();
            userRepository.save(userEntity);
            System.out.println(userEntity.getPseudo());
            System.out.println(userRepository.findByToken(userEntity.getToken()));
            Long id = Long.valueOf(userRepository.findIdByPseudo(userEntity.getPseudo()));
            return UserAdapter.toDto(userEntity);
        }
    }

    @Transactional
    public UserDto saveUser(UserEntity userEntity) {
        userEntity.setToken(createToken(userEntity.getPseudo(), userEntity.getPassword()));
        userRepository.save(userEntity);
        return UserAdapter.toDto(userEntity);
    }

    @Transactional(readOnly = true)
    public UserEntity getUserByPseudo(String pseudo) {
        UserEntity user = userRepository.findByPseudo(pseudo);
        if(user != null) {
            return user;
        }else{
            return null;
        }
    }

    @Transactional(readOnly = true)
    public boolean verifyToken(String token) {
        try{
            DecodedJWT decodedJWT = JWT.decode(token);
            String pseudo = decodedJWT.getClaim("username").asString();
            String password = decodedJWT.getClaim("password").asString();
            if(!verifyUser(pseudo, password)){
                return false;
            }
        }catch(JWTDecodeException e){
            e.printStackTrace();
        }
        return true;
    }

    @Transactional(readOnly = true)
    public boolean verifyUser(String pseudo, String password){
        UserEntity user = userRepository.findByPseudo(pseudo);
        if(user == null) {
            return false;
        }else if(!passwordEncoder.matches(password, user.getPassword())){
            return false;
        }
        return true;
    }

    @Transactional
    public void removeUser(Long id){
        UserEntity u = userRepository.findById(id);
        if(u != null){
            userRepository.delete(u);
        }
    }


}
