package com.smartcode.web.service.comment.impl;

import com.smartcode.web.model.Comment;
import com.smartcode.web.model.User;
import com.smartcode.web.repository.CommentRepository;
import com.smartcode.web.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Transactional
    public Comment get(User user, String title) {
        return null;
    }

    @Override
    public Comment create(User user, String title, String description) {
        return null;
    }

    @Override
    public boolean delete(User user, String title) {
        return false;
    }

    @Override
    public List<Comment> getAll(User user) {
        return null;
    }
}