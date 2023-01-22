package com.example.bootintro.web;

import com.example.bootintro.repositories.AccountRepository;
import com.example.bootintro.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class ControllerAccount {

    private UserRepository userRepository;
    private AccountRepository accountRepository;

    public ControllerAccount(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping("jpa/accounts")
    public String accountList(Model m){
        m.addAttribute("accounts",accountRepository.findAll());
        return "view-accounts";
    }
}
