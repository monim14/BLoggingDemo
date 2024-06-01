package com.assignment.ui.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
public class CreatePostRequest {

@NotNull(message = "Please enter the title also")
private String title;

private String content;

@NotNull(message = "Please provide author name also")
private String author;
}
