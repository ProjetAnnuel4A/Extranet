package com.esgi.extranet.login.Service;

import com.esgi.extranet.login.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author timotheearnauld
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    private UserServices userServices;

    @Override
    public boolean supports(Class<?> aClass){
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pseudo", "NotEmpty");
        if(user.getPseudo().length()<6 || user.getPseudo().length()>32){
            errors.rejectValue("pseudo", "Size.userForm.pseudo");
        }
        if(userServices.getUserByPseudo(user.getPseudo()) != null) {
            errors.rejectValue("pseudo", "Duplicate.userForm.pseudo");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if(user.getPassword().length()<8 || user.getPassword().length()>32){
            errors.rejectValue("password", "Size.userForm.password");
        }
    }


}
