package com.example.bootintro.web;

import com.example.bootintro.model.Account;
import com.example.bootintro.model.User;
import com.example.bootintro.repositories.AccountRepository;
import com.example.bootintro.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerAccount {

    private UserRepository userRepository;
    private AccountRepository accountRepository;

    public ControllerAccount(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/jpa/accounts")
    public String accountList(Model m){
        m.addAttribute("accounts",accountRepository.findAll());
        return "view-accounts";
    }
    @GetMapping("/jpa/accounts/create")
    public String accountCreate(Model m){
        m.addAttribute("users", userRepository.findAll());
        m.addAttribute("newAccount",new Account());

        return "account-create";
    }

    @PostMapping("/jpa/accounts/create")
    public String processRegister(Model m, @ModelAttribute Account p){
        System.out.println(p.getBalance());
        //System.out.println(p.getUser().getId());
        User tempUser = userRepository.findByLogin(p.getUser().getLogin());

        System.out.println(tempUser.getId());

        accountRepository.save(p);
        return "redirect:/jpa/accounts";
    }

    @GetMapping("/jpa/account/detail/{id}")
    public String showAccountDetail(@PathVariable("id") Integer id, Model m){
        Account account = accountRepository.findById(id).get();
        m.addAttribute("account", account);
        return "account-detail";
    }
}
