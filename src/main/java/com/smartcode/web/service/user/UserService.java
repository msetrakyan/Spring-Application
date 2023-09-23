package com.smartcode.web.service.user;

import com.smartcode.web.model.UserEntity;

public interface UserService {

    void register(UserEntity user);

    void login(String username, String password);

    boolean changePassword(UserEntity user, String oldPass, String newPass);

    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

    void updateUser(UserEntity userEntity);



}