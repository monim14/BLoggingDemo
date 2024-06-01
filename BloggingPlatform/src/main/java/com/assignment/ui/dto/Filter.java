package com.assignment.ui.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Filter {
    private String author;
    private LocalDate creationDate;
}
