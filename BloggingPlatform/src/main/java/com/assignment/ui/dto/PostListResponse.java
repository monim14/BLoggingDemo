package com.assignment.ui.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostListResponse {
    private Boolean endOfList = false;
    private List<PostResponse> postResponseList = new ArrayList<>();
}
