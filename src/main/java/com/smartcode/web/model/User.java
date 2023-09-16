package com.smartcode.web.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "last_name")
    private String lastname;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Comment> list;


}