package com.esgi.extranet.login.Service;

import com.esgi.extranet.login.Role;
import com.esgi.extranet.login.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author timotheearnauld
 */
@CrossOrigin
@Controller
public class LoginController {
    private final UserServices userServices;
    private final SecurityService securityService;
    private final UserValidator userValidator;

    @Autowired
    public LoginController(UserServices userServices, SecurityService securityService, UserValidator userValidator) {
        this.userServices = userServices;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping(value = "/home")
    public String home(Model model) {
        return "login/homePage";
    }

    @GetMapping(value = "/welcome")
    public String welcome(Model model){ return "login/welcomePage";}

    @GetMapping(value = "/login", produces = "text/html")
    public String getLoginPage(Model model){
        return "login/loginPage";
    }
    @GetMapping(value = "/error")
    public String error(Model model){ return "errorPage";}

    @RequestMapping(value = "/login", method = POST)
    public String login(Model model, @ModelAttribute("pseudo")String pseudo,
                        @ModelAttribute("password")String password) {
        if(userServices.verifyUser(pseudo, password) == true){
            return "redirect:login/welcome";
        }else{
            return "redirect:login/error";
        }
    }
}
