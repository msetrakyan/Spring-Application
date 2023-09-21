package com.smartcode.web.service.comment.impl;

import com.smartcode.web.model.CommentEntity;
import com.smartcode.web.model.UserEntity;
import com.smartcode.web.repository.CommentRepository;
import com.smartcode.web.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.stream.events.Comment;
import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Transactional
    public CommentEntity get(UserEntity user, String title) {
        CommentEntity commentEntity = commentRepository.findByTitleAndUser(title, user);
        return commentEntity;
    }

    @Override
    public CommentEntity create(UserEntity user, String title, String description) {
        CommentEntity commentEntity = new CommentEntity(null, title, description, user);
        commentRepository.save(commentEntity);
        return commentEntity;
    }

    @Override
    public boolean delete(UserEntity user, String title) {
        CommentEntity commentEntity = commentRepository.findByTitleAndUser(title, user);
        commentRepository.delete(commentEntity);
        return true;
    }

    @Override
    public List<CommentEntity> getAll(UserEntity user) {
        List<CommentEntity> list = commentRepository.findAllByUser(user);
        return list;
    }
}