package com.example.bootintro.web;

import com.example.bootintro.model.Person;
import com.example.bootintro.repositories.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ControllerJpa {

    private PersonRepository personRepository;

    public ControllerJpa(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @GetMapping("/jpa/people")
    public String test4(Model m) {
        m.addAttribute("people",personRepository.findAll());
        m.addAttribute("newPerson", new Person("", ""));
        return "person-detail";
    }

    @GetMapping("jpa/")
    public String getMainPage(){
        return "main-page";
    }

    @GetMapping("jpa/sign_in")
    public String signIn()
    {
        return "account-login";
    }
    @GetMapping("jpa/register")
    public String register()
    {
        return "account-create";
    }

    @PostMapping("/jpa/people")
    public String test4post(Model m, @ModelAttribute Person p) {
        personRepository.save(p);
        return "redirect:/jpa/people";
    }

    @PostMapping("/api/v1/people/add")
    public Person addPersonFromRequest(Person p) {
        return personRepository.save(p);
    }

    @GetMapping("/jpa/find/{id}")
    public Optional<Person> findByID(@PathVariable int id) {
        return personRepository.findById(id);
    }

}
