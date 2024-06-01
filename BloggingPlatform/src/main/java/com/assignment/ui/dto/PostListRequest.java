package com.assignment.ui.dto;

import lombok.Data;

@Data
public class PostListRequest {
    private PageDto pageDto;

    private Filter filter = new Filter();
}
