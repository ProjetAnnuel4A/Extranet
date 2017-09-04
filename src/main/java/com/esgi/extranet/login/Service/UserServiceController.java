package com.esgi.extranet.login.Service;

import com.esgi.extranet.MappingController;
import com.esgi.extranet.login.MailClient;
import com.esgi.extranet.login.UserEntity;
import com.esgi.extranet.login.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author timotheearnauld
 */
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserServiceController {
    private final MailClient mailClient;
    private final UserServices userServices;
    private final SecurityService securityService;
    private final UserValidator userValidator;

    @Autowired
    public UserServiceController(MailClient mailClient, UserServices userServices, SecurityService securityService, UserValidator userValidator) {
        this.mailClient = mailClient;
        this.userServices = userServices;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping("/getUsers")
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
        UserEntity user = userServices.verifyUser(pseudo, password);
        if(user != null){
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

    @PostMapping("/retrievePassword")
    public void retrievePassword(@RequestParam("email") String email){
        UserEntity user = userServices.retrievePassword(email);
        String object = "Récupération de mot de passe - Brotherhood";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("email/retrievepassword.html").getFile());
        String message = null;
        try {
            message = String.join("", Files.readAllLines(Paths.get(file.getPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        message = message.replace("*firstname*", user.getFirstname());
        message = message.replace("*token*", user.getToken());
        mailClient.prepareAndSend(email, object, message);
    }

    @GetMapping("/changePassword")
    public void changePassword(@RequestParam("token") String token){
        UserEntity user = userServices.getUserByToken(token);
        String newPassword = userServices.changePassword(token);
        String object = "Nouveau mot de passe - Brotherhood";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("email/newpassword.html").getFile());
        String message = null;
        try {
            message = String.join("", Files.readAllLines(Paths.get(file.getPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        message = message.replace("*firstname*", user.getFirstname());
        message = message.replace("*pseudo*", user.getPseudo());
        message = message.replace("*password*", newPassword);
        mailClient.prepareAndSend(user.getEmail(), object, message);
        MappingController.newPassword();
    }

    @PostMapping("/verifyToken")
    public String getRoleForToken(@RequestParam("token") String token){
        return userServices.getRoleForToken(token);
    }

    @PostMapping("/getInformations")
    public UserEntity getInformations(@RequestParam("token") String token){
        return userServices.getInformations(token);
    }

    @PostMapping("/changePassword")
    public boolean changePassword(@RequestParam("id") Long id, @RequestParam("password") String password){
        return userServices.changeUserPassword(id, password);
    }
}
