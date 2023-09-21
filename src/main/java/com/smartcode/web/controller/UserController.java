package com.smartcode.web.controller;


import com.smartcode.web.model.CommentEntity;
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
@RequestMapping(path = "/users")
public class UserController {


    private final UserService userService;
    private final CommentService commentService;

    @Autowired
    public UserController(UserService userService, CommentService commentService) {
        this.userService = userService;
        this.commentService = commentService;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam String username,
                              @RequestParam String password,
                              HttpSession session) {

        UserEntity userEntity = userService.findByUsername(username);

        if(userEntity == null) {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("message", "Username not found");
            return modelAndView;
        }

        if(!userEntity.getPassword().equals(password)) {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("message", "Wrong password");
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("username", username);
        session.setAttribute("username", username);

        System.out.println(session.getAttribute("username"));

        return modelAndView;
    }



    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ModelAndView register(@RequestParam String name,
                                 @RequestParam String lastName,
                                 @RequestParam Integer age,
                                 @RequestParam String username,
                                 @RequestParam String password) {

        if(userService.findByUsername(username) != null) {
            ModelAndView modelAndView = new ModelAndView("register");
            modelAndView.addObject("message", "Username is taken");
            return modelAndView;
        }

        UserEntity userEntity = new UserEntity(name, lastName, age, username, password);

        userService.register(userEntity);

        return new ModelAndView("login");
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }
























}