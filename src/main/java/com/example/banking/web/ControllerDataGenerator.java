package com.example.banking.web;

import com.example.banking.model.Account;
import com.example.banking.model.AccountActivity;
import com.example.banking.model.Role;
import com.example.banking.model.User;
import com.example.banking.repositories.AccountActivityRepository;
import com.example.banking.repositories.AccountRepository;
import com.example.banking.repositories.RoleRepository;
import com.example.banking.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@Controller
public class ControllerDataGenerator {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AccountRepository accountRepository;
    private AccountActivityRepository accountActivityRepository;

    public ControllerDataGenerator(UserRepository userRepository, RoleRepository roleRepository, AccountRepository accountRepository, AccountActivityRepository accountActivityRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
        this.accountActivityRepository = accountActivityRepository;
    }

    @GetMapping("/jpa/generateDummyData")
    public String generateDummyData(Model m) {

        Role role1 = new Role("ROLE_USER");
        Role role2 = new Role("ROLE_ADMIN");

        User user1 = new User("Petr","Vratny","123","petr@vratny.cz");
        User user2 = new User("Vaclav","Zahradnik","159","vaclav@zahradnik.cz");
        User user3 = new User("Karel","Hromek","741","karel@hromek.cz");
        User user4 = new User("Marek","Truhlar","987","marek@truhlar.cz");
        User user5 = new User("Jan","Novotny","369","jan@novotny.cz");

        Account account1 = new Account(10000,user1);
        Account account2 = new Account(7700,user1);
        Account account3 = new Account(28000,user2);
        Account account4 = new Account(3000,user3);
        Account account5 = new Account(8600,user4);
        Account account6 = new Account(60000,user5);
        Account account7 = new Account(5100,user4);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();

        AccountActivity accountActivity1 = new AccountActivity("Expense",100,account1, dtf.toString());
        AccountActivity accountActivity2 = new AccountActivity("Income",150,account2, dtf.format(now));
        AccountActivity accountActivity3 = new AccountActivity("Expense",500,account3, dtf.format(now));
        AccountActivity accountActivity4 = new AccountActivity("Income",690,account4, dtf.format(now));
        AccountActivity accountActivity5 = new AccountActivity("Income",470,account5, dtf.format(now));
        AccountActivity accountActivity6 = new AccountActivity("Income",50,account1, dtf.format(now));
        AccountActivity accountActivity7 = new AccountActivity("Expense",800,account7, dtf.format(now));
        AccountActivity accountActivity8 = new AccountActivity("Income",970,account3, dtf.format(now));
        AccountActivity accountActivity9 = new AccountActivity("Income",490,account5, dtf.format(now));
        AccountActivity accountActivity10 = new AccountActivity("Expense",550,account4, dtf.format(now));

        roleRepository.save(role1);
        roleRepository.save(role2);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);

        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.save(account3);
        accountRepository.save(account4);
        accountRepository.save(account5);
        accountRepository.save(account6);
        accountRepository.save(account7);

        accountActivityRepository.save(accountActivity1);
        accountActivityRepository.save(accountActivity2);
        accountActivityRepository.save(accountActivity3);
        accountActivityRepository.save(accountActivity4);
        accountActivityRepository.save(accountActivity5);
        accountActivityRepository.save(accountActivity6);
        accountActivityRepository.save(accountActivity7);
        accountActivityRepository.save(accountActivity8);
        accountActivityRepository.save(accountActivity9);
        accountActivityRepository.save(accountActivity10);

        return "redirect:/jpa/";
    }


}
