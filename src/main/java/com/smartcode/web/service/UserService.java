package com.smartcode.web.service;

import com.smartcode.web.exception.ResourceNotFoundException;
import com.smartcode.web.exception.WrongPasswordException;
import com.smartcode.web.model.User;
import com.smartcode.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user) {
        userRepository.save(user);
    }

    public void login(String username, String password) {

        User user = userRepository.findByUsername(username);

        if(user == null) {
            throw new ResourceNotFoundException("Username doesn't exist");
        }
        if(!user.getPassword().equals(password)) {
            throw new WrongPasswordException("You've entered the wrong password");
        }

    }

   public boolean changePassword(User user, String oldPass, String newPass) {

        if(!oldPass.equals(user.getPassword())) {
            return false;
        }

        user.setPassword(newPass);

        return true;
    }















}
