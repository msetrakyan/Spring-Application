package com.smartcode.web.controller;


import com.smartcode.web.exception.ResourceNotFoundException;
import com.smartcode.web.exception.VerificationException;
import com.smartcode.web.exception.WrongPasswordException;
import com.smartcode.web.model.UserEntity;
import com.smartcode.web.service.comment.CommentService;
import com.smartcode.web.service.mail.MailService;
import com.smartcode.web.service.user.UserService;
import com.smartcode.web.utils.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(path = "/users")
public class UserController {


    private final UserService userService;
    private final CommentService commentService;
    private final MailService mailService;

    @Autowired
    public UserController(UserService userService, CommentService commentService, MailService mailService) {
        this.userService = userService;
        this.commentService = commentService;
        this.mailService = mailService;
    }

    @GetMapping(path = "/")
    public ModelAndView start(@CookieValue(name = "rememberMe", required = false) Cookie cookie,
                              HttpSession session,
                              HttpServletResponse response) {
        if(cookie != null) {
            String cookieValue = cookie.getValue();
            String username = cookieValue.split(":")[0];
            String password = cookieValue.split(":")[1];

            return login(username, password, "on", response, session);
        } else {
            return new ModelAndView("index");
        }

    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam(required = false) String rememberMe,
                              HttpServletResponse response,
                              HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();

        try {
            userService.login(username, password);

            session.setAttribute("username", username);

            modelAndView.setViewName("home");

            Cookie cookie = new Cookie("rememberMe", username + ":" + password);

            cookie.setMaxAge(360000);

            response.addCookie(cookie);

            return modelAndView;

        } catch (VerificationException exception) {
            modelAndView.addObject("message", exception.getMessage());
            modelAndView.setViewName("index");
            return modelAndView;
        } catch (ResourceNotFoundException exception) {
            modelAndView.addObject("message", exception.getMessage());
            modelAndView.setViewName("login");
            return modelAndView;
        } catch (WrongPasswordException exception) {
            modelAndView.setViewName("login");
            modelAndView.addObject("message", exception.getMessage());
            return modelAndView;
        }

    }



    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ModelAndView register(@RequestParam String name,
                                 @RequestParam String lastName,
                                 @RequestParam Integer age,
                                 @RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String email) {

        if(userService.findByUsername(username) != null) {
            ModelAndView modelAndView = new ModelAndView("register");
            modelAndView.addObject("message", "Username is taken");
            return modelAndView;
        }

        UserEntity userEntity = new UserEntity(name, lastName, age, username, password, email);

        userEntity.setIsVerified(false);

        userEntity.setCode(RandomGenerator.generateNumericString(6));

        userService.register(userEntity);

        mailService.sendEmail(email, "Verification", userEntity.getCode());

        ModelAndView modelAndView = new ModelAndView("verification");


        return modelAndView;
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("login");
    }




    @PostMapping(path = "/verification")
    public ModelAndView verification(@RequestParam String code,
                                     @RequestParam String email) {


        ModelAndView modelAndView = new ModelAndView();

        try {

            UserEntity userEntity = userService.findByEmail(email);

            if(userEntity == null) {
                modelAndView.setViewName("index");
                modelAndView.addObject("message", "User not found!");
                return modelAndView;
            }

            if(!userEntity.getCode().equals(code)) {
                throw new VerificationException("Wrong code");
            }

            userEntity.setIsVerified(true);

            userService.updateUser(userEntity);

            modelAndView.setViewName("login");

            return modelAndView;

        } catch (VerificationException exception) {
            modelAndView.setViewName("index");
            modelAndView.addObject("message", "Verification failed!");
            return modelAndView;
        }
    }





















}