package com.example.bootintro.web;

import com.example.bootintro.model.Account;
import com.example.bootintro.model.AccountActivity;
import com.example.bootintro.model.User;
import com.example.bootintro.repositories.AccountActivityRepository;
import com.example.bootintro.repositories.AccountRepository;
import com.example.bootintro.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerAccount {
    
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private AccountActivityRepository accountActivityRepository;

    public ControllerAccount(UserRepository userRepository, AccountRepository accountRepository, AccountActivityRepository accountActivityRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.accountActivityRepository = accountActivityRepository;
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

        List<AccountActivity> allAccountActivity = accountActivityRepository.findAll();
        List<AccountActivity> accountActivities = new ArrayList<>();
        for (AccountActivity accActivity: allAccountActivity) {
            if (accActivity.getAccount().getId() == account.getId()){
                accountActivities.add(accActivity);
            }
        }

        m.addAttribute("accountActivitiesFound",accountActivities);
        
        return "account-detail";
    }

    @GetMapping("/jpa/account/delete/{id}")
    public String deleteAccount(@PathVariable("id") Integer id, Model m){
        accountRepository.deleteById(id);

        return "redirect:/jpa/accounts";

    }
}
