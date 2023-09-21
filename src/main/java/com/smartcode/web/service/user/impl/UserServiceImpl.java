package com.smartcode.web.service.user.impl;

import com.smartcode.web.exception.ResourceNotFoundException;
import com.smartcode.web.exception.WrongPasswordException;
import com.smartcode.web.model.UserEntity;
import com.smartcode.web.repository.UserRepository;
import com.smartcode.web.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void register(UserEntity user) {
        userRepository.save(user);
    }

    @Transactional
    public void login(String username, String password) {

        UserEntity user = userRepository.findByUsername(username);

        if(user == null) {
            throw new ResourceNotFoundException("Username doesn't exist");
        }
        if(!user.getPassword().equals(password)) {
            throw new WrongPasswordException("You've entered the wrong password");
        }

    }

    @Transactional
   public boolean changePassword(UserEntity user, String oldPass, String newPass) {

        if(!oldPass.equals(user.getPassword())) {
            return false;
        }

        user.setPassword(newPass);

        return true;
    }



    public UserEntity findByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        return userEntity;
    }



}
