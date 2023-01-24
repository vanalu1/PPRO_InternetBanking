package com.example.banking;

import com.example.banking.model.Account;
import com.example.banking.model.User;
import com.example.banking.repositories.AccountRepository;
import com.example.banking.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    private AccountRepository accountRepository;
    private UserRepository userRepository;

    @Test
    public void TestAccountRepository(){
        User userTemp = new User("test","test","test","test");
        testEntityManager.persistAndFlush(userTemp);
        Account account = new Account(10,userTemp);
        testEntityManager.persistAndFlush(account);

        Account foundAcc = accountRepository.findById(account.getId()).orElse(null);
        assertThat(foundAcc.getBalance()).isEqualTo(account.getBalance());
    }
}
