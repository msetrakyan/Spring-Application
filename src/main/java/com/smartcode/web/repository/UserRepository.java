package com.smartcode.web.repository;

import com.smartcode.web.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Integer> {


     UserEntity findByUsername(String username);



}