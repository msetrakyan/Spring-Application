package com.smartcode.web.service.user.impl;

import com.smartcode.web.exception.ResourceNotFoundException;
import com.smartcode.web.exception.VerificationException;
import com.smartcode.web.exception.WrongPasswordException;
import com.smartcode.web.model.UserEntity;
import com.smartcode.web.repository.UserRepository;
import com.smartcode.web.service.mail.MailService;
import com.smartcode.web.service.user.UserService;
import com.smartcode.web.utils.RandomGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;


@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final MailService mailService;


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
        if(!user.getIsVerified()) {
            throw new VerificationException("Account not verified!");
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



    @Transactional(readOnly = true)
    public UserEntity findByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        return userEntity;
    }

    @Transactional(readOnly = true)
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void updateUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }





}