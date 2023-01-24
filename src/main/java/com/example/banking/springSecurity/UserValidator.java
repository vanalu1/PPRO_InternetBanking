/*package com.example.bootintro.web;

import com.example.bootintro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username","NotEmpty");
        if (user.getLogin().length()<2 || user.getLogin().length()>100){
            errors.rejectValue("username","Size.userForm.username");
        }
        if (userService.findByUsername(user.getLogin()) != null){
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","NotEmpty");
        if (user.getPassword().length()<2 || user.getPassword().length()>300){
            errors.rejectValue("password", "Size.userForm.password");
        }
    }
}*/
