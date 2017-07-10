package com.esgi.extranet.login.Service;

import com.esgi.extranet.login.UserEntity;
import com.esgi.extranet.login.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author timotheearnauld
 */
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserServiceController {

    private final UserServices userServices;
    private final SecurityService securityService;
    private final UserValidator userValidator;

    @Autowired
    public UserServiceController(UserServices userServices, SecurityService securityService, UserValidator userValidator) {
        this.userServices = userServices;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping
    public List<UserDto> getUsers(){
        return userServices.getAllUsers();
    }

    @PostMapping
    public UserDto insertUser(@RequestBody UserEntity userEntity){
        return userServices.createUser(userEntity.getPseudo(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getRole());
    }
    @GetMapping("/{pseudo}")
    public UserEntity getUserByPseudo(@PathVariable String pseudo){
        UserEntity userEntity = userServices.getUserByPseudo(pseudo);
        return UserEntity.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .build();
    }

    @PostMapping("/token")
    public String verifyToken(@RequestBody UserEntity userEntity){
        return String.valueOf(userServices.verifyToken(userEntity.getToken()));
    }
    @GetMapping("/verify")
    public UserEntity verifyUser(@RequestParam("pseudo") String pseudo,
                                 @RequestParam("password") String password){
        UserEntity error = UserEntity.builder()
                .pseudo("erreur")
                .build();
        if(userServices.verifyUser(pseudo, password) == true){
            UserEntity userEntity = userServices.getUserByPseudo(pseudo);
            if(userServices.verifyToken(userEntity.getToken()) == false){
                return error;
            }
            return UserEntity.builder()
                    .id(userEntity.getId())
                    .token(userEntity.getToken())
                    .build();
        }else{
            return error;
        }
    }

    @GetMapping("/delete")
    public void removeUser(@RequestParam("id") Long id){
        userServices.removeUser(id);
    }
}
