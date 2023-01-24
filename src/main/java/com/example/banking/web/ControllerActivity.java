package com.example.banking.web;

import com.example.banking.model.Account;
import com.example.banking.model.AccountActivity;
import com.example.banking.repositories.AccountActivityRepository;
import com.example.banking.repositories.AccountRepository;
import com.example.banking.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

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

        List<Account> allAcounts = accountRepository.findAll();
        List<Account> otherAccounts = new ArrayList<>();
        for (Account acc: allAcounts) {
            if (acc.getId() != account.getId()){
                otherAccounts.add(acc);
            }
        }
        m.addAttribute("allOtherAccounts", otherAccounts);

        int transferId = 0;
        m.addAttribute("transferId", transferId);


        return "activity-create";

    }

    @PostMapping("/jpa/account/{id}/new_activity")
    public String processActivity(Model m, @ModelAttribute AccountActivity p,@PathVariable("id") Integer id, @RequestParam(required = false, value = "transferId") Integer transferId){

        Account account = accountRepository.findById(id).get();
        int temp = 0;
        int temp2 = 0;
        if (p.getActivityType().toString().equals("Expense")){
            temp = account.getBalance();
            temp -= p.getValue();
            accountRepository.findById(id).get().setBalance(temp);
        }
        if (p.getActivityType().toString().equals("Income")){
            temp = account.getBalance();
            temp += p.getValue();
            accountRepository.findById(id).get().setBalance(temp);
        }
        if (p.getActivityType().toString().equals("Outgoing Transfer")){
            temp = account.getBalance();
            temp -= p.getValue();
            accountRepository.findById(id).get().setBalance(temp);
            temp2 = accountRepository.findById(transferId).get().getBalance();
            temp2 += p.getValue();
            accountRepository.findById(transferId).get().setBalance(temp2);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            AccountActivity opositeTransferActivity = new AccountActivity("Incoming Transfer",p.getValue(),accountRepository.findById(transferId).get(), dtf.format(now));
            accountActivityRepository.save(opositeTransferActivity);
        }

        System.out.println(transferId);
        accountActivityRepository.save(p);
        return "redirect:/jpa/account/detail/{id}";
    }

}
