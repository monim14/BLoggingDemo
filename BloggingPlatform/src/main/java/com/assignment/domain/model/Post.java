package com.assignment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "posts")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name="title")
private String title;

    @Column(name="content")
private String content;

    @Column(name="author")
private String author;

    @Column(name="creation_date")
private LocalDate creationDate;


}