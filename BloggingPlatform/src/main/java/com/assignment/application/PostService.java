package com.assignment.application;

import com.assignment.domain.model.Post;
import com.assignment.ui.dto.Filter;
import com.assignment.ui.dto.Page;

import java.util.List;

public interface PostService {
    Post createPost(Post post);

    Post updatePost(Long postId, Post newPost);

    void  deletePost(Long postId);

    Post getPost(Long postId);

    List<Post> listAllPosts(Page page, Filter filter);
}
