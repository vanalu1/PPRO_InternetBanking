package com.example.bootintro.web;

import com.example.bootintro.model.Account;
import com.example.bootintro.model.AccountActivity;
import com.example.bootintro.model.User;
import com.example.bootintro.repositories.AccountActivityRepository;
import com.example.bootintro.repositories.AccountRepository;
import com.example.bootintro.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerActivity {

    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private AccountActivityRepository accountActivityRepository;

    public ControllerActivity(UserRepository userRepository, AccountRepository accountRepository, AccountActivityRepository accountActivityRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.accountActivityRepository = accountActivityRepository;
    }

    @GetMapping("/jpa/account/{id}/new_activity")
    public String createActivity(@PathVariable("id") Integer id, Model m){

        Account account = accountRepository.findById(id).get();
        m.addAttribute("account", account);
        m.addAttribute("newAccountActivity", new AccountActivity());

        System.out.println(account.getId());

        return "activity-create";

    }

    @PostMapping("/jpa/account/{id}/new_activity")
    public String processActivity(Model m, @ModelAttribute AccountActivity p){
        accountActivityRepository.save(p);
        return "redirect:/jpa/account/detail/{id}";
    }

}
