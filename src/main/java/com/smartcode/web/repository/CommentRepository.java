package com.smartcode.web.repository;

import com.smartcode.web.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;


@Component
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
