package com.smartcode.web.service.user.impl;

import com.smartcode.web.exception.ResourceNotFoundException;
import com.smartcode.web.exception.WrongPasswordException;
import com.smartcode.web.model.User;
import com.smartcode.web.repository.UserRepository;
import com.smartcode.web.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void register(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void login(String username, String password) {

        User user = userRepository.findByUsername(username);

        if(user == null) {
            throw new ResourceNotFoundException("Username doesn't exist");
        }
        if(!user.getPassword().equals(password)) {
            throw new WrongPasswordException("You've entered the wrong password");
        }

    }

    @Transactional
   public boolean changePassword(User user, String oldPass, String newPass) {

        if(!oldPass.equals(user.getPassword())) {
            return false;
        }

        user.setPassword(newPass);

        return true;
    }















}
