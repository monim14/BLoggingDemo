package com.assignment.ui.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PostResponse {
    private String title;
    private String author;
    private String content;
    private Long id;
    private LocalDate creationDate;
}
