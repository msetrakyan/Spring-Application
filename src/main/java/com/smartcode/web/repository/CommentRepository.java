package com.smartcode.web.repository;

import com.smartcode.web.model.CommentEntity;
import com.smartcode.web.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {


    CommentEntity findByTitleAndUser (String title, UserEntity userEntity);

    List<CommentEntity> findAllByUser (UserEntity userEntity);



}