package com.smartcode.web.repository;

import com.smartcode.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {


     User findByUsername(String username);



}