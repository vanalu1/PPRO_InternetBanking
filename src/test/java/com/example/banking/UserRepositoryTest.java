package com.example.banking;

import com.example.banking.model.User;
import com.example.banking.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void test(){
        User user = new User("test","user","1254","test@user.com");
        testEntityManager.persistAndFlush(user);

        User loadUser = userRepository.findById(user.getId()).orElse(null);
        assertThat(loadUser.getLogin()).isEqualTo(user.getLogin());


    }
}
