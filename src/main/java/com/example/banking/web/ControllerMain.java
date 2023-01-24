package com.example.banking.web;

import com.example.banking.model.User;
import com.example.banking.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControllerMain {

    private UserRepository userRepository;

    public ControllerMain(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/jpa/users")
    public String test4(Model m) {
        m.addAttribute("people", userRepository.findAll());
        return "person-detail";
    }

    @GetMapping("jpa/")
    public String getMainPage(){
        return "main-page";
    }

    @GetMapping("jpa/sign_in")
    public String signIn()
    {
        return "user-login";
    }
    @GetMapping("jpa/register")
    public String register(Model m)
    {
        m.addAttribute("newPerson", new User("", "","",""));
        return "user-register";
    }

    @PostMapping("/jpa/register")
    public String processRegister(Model m, @ModelAttribute User p){
        userRepository.save(p);
        return "redirect:/jpa/";
    }




//    @PostMapping("/jpa/users")
//    public String usersTest(Model m, @ModelAttribute User p) {
//        userRepository.save(p);
//        return "redirect:/jpa/people";
//    }

}
