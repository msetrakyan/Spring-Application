package com.smartcode.web.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    private User user;






}
