package com.tanveer.journalApp.service;


import com.tanveer.journalApp.entity.User;
import com.tanveer.journalApp.repository.UserRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsSource.class)
    public void testSaveUser(User user) {
        assertTrue(userService.saveEntry(user));
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "1,2,3",
            "2,8,10"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a + b);
    }

}
