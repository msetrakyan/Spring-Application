package com.smartcode.web.controller;

import com.smartcode.web.model.UserEntity;
import com.smartcode.web.service.comment.CommentService;
import com.smartcode.web.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/comments")
public class CommentController {

    private final UserService userService;
    private final CommentService commentService;

    @Autowired
    public CommentController(UserService userService, CommentService commentService) {
        this.userService = userService;
        this.commentService = commentService;
    }



    @RequestMapping(path = "/commentDelete", method = RequestMethod.POST)
    public ModelAndView commentDelete(@RequestParam String title,
                                      HttpSession session) {

        String username = (String)session.getAttribute("username");

        UserEntity userEntity = userService.findByUsername(username);

        commentService.delete(userEntity, title);

        return new ModelAndView("comment");
    }


    @RequestMapping(path = "/commentCreate", method = RequestMethod.POST)
    public ModelAndView commentCreate(@RequestParam String title,
                                      @RequestParam String description,
                                      HttpSession session) {

        String username = (String)session.getAttribute("username");

        UserEntity userEntity = userService.findByUsername(username);

        commentService.create(userEntity, title, description);

        return new ModelAndView("comment");
    }



}
