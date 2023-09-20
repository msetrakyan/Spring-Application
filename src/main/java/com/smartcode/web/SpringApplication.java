package com.smartcode.web;

import com.smartcode.web.model.User;
import com.smartcode.web.repository.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {

        ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("application-context.xml");









    }
}