package com.example.bootintro.web;

import com.example.bootintro.model.User;
import com.example.bootintro.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ControllerJpa {

    private UserRepository userRepository;

    public ControllerJpa(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/jpa/people")
    public String test4(Model m) {
        m.addAttribute("people", userRepository.findAll());
        m.addAttribute("newPerson", new User("","","",""));
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
        return "user-create";
    }

    @PostMapping("/jpa/register")
    public String processRegister(Model m, @ModelAttribute User p){
        userRepository.save(p);
        return "redirect:/jpa/";
    }

    @PostMapping("/jpa/people")
    public String test4post(Model m, @ModelAttribute User p) {
        userRepository.save(p);
        return "redirect:/jpa/people";
    }

    @PostMapping("/api/v1/people/add")
    public User addPersonFromRequest(User p) {
        return userRepository.save(p);
    }

    @GetMapping("/jpa/find/{id}")
    public Optional<User> findByID(@PathVariable int id) {
        return userRepository.findById(id);
    }

}
