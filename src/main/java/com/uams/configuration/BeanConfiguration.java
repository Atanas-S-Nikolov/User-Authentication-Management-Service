package com.uams.configuration;

import com.uams.repository.SearchHistoryRepository;
import com.uams.repository.UserRepository;
import com.uams.service.IUserService;
import com.uams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    @Bean("password-encoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean("user-service")
    public IUserService userService() {
        return new UserService(userRepository, searchHistoryRepository, passwordEncoder());
    }
}
