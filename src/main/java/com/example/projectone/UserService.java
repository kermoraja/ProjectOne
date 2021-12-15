package com.example.projectone;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(String userName, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        userRepository.createUser(userName, encodedPassword);
    }
}
