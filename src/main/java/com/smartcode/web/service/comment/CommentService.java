package com.smartcode.web.service.comment;

import com.smartcode.web.model.CommentEntity;
import com.smartcode.web.model.UserEntity;

import java.util.List;

public interface CommentService {

    CommentEntity get(UserEntity user, String title);

    CommentEntity create(UserEntity user, String title, String description);

    boolean delete(UserEntity user, String title);

    List<CommentEntity> getAll(UserEntity user);

}
