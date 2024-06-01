package com.assignment.ui.controller;

import com.assignment.application.PostService;
import com.assignment.domain.model.Post;
import com.assignment.ui.dto.CreatePostRequest;
import com.assignment.ui.dto.PostListRequest;
import com.assignment.ui.dto.PostListResponse;
import com.assignment.ui.dto.PostResponse;
import com.assignment.ui.mapper.PostsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize(value="hasRole('ROLE_ANONYMOUS')")
@RequestMapping(path = "/post")
public class BloggingPlatformController {
    @Autowired
    private PostsMapper postMapper;

    @Autowired
    private PostService postService;


    @PostMapping
    public PostResponse createPost(@RequestBody @Valid CreatePostRequest request) {
        Post post = postMapper.toPost(request);
        return postMapper.fromPost(postService.createPost(post));
    }

    @GetMapping("/{postId}")
    public PostResponse getPost(@PathVariable Long postId) {
        return postMapper.fromPost(postService.getPost(postId));
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }

    @PatchMapping("/update/{postId}")
    public Post updatePost(@RequestBody @Valid CreatePostRequest update, @PathVariable Long postId) {
        Post post= postMapper.toPost(update);
        return postService.updatePost(postId, post);
    }

    @PostMapping("/list")
    public PostListResponse listPostsPaginated(@RequestBody @Valid PostListRequest request) {
    PostListResponse results = new PostListResponse();
    List<Post> posts = postService.listAllPosts(request.getPageDto(), request.getFilter());
    results.setPostResponseList(postMapper.fromPostList(posts));
    if (posts.size() != request.getPageDto().getPageSize()) {
        results.setEndOfList(true);
    }
    return results;
    }



}
