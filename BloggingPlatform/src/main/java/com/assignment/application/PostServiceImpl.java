package com.assignment.application;

import com.assignment.domain.model.Post;
import com.assignment.domain.repository.PostQueryRepositoryImpl;
import com.assignment.domain.repository.PostRepository;
import com.assignment.ui.dto.Filter;
import com.assignment.ui.dto.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostQueryRepositoryImpl postQueryRepository;

    @Override
    public Post createPost(Post post) {
        post.setCreationDate(LocalDate.now());
        post = postRepository.save(post);
        log.info("the post is {}",post);
        return post;
    }

    @Override
    public Post updatePost(Long postId, Post newPost) {
        Post post = getPostById(postId);
        if (newPost.getAuthor() != null)
        post.setAuthor(newPost.getAuthor());
        if (newPost.getContent() != null)
        post.setContent(newPost.getContent());
        if (newPost.getTitle() != null)
        post.setTitle(newPost.getTitle());
        return postRepository.save(post);

    }

    @Override
    public void deletePost(Long postId) {
        getPostById(postId);
        postRepository.deleteById(postId);
    }

    @Override
    public Post getPost(Long postId) {
        return getPostById(postId);
    }

    @Override
    public List<Post> listAllPosts(Page page, Filter filter) {
        Pageable pageable = PageRequest.of(page.getNumber(), page.getPageSize());
        return postQueryRepository.listAllPosts(pageable, filter);
    }

    public Post  getPostById(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isEmpty()) {
            throw new ValidationException(String.format("Post with id %s does not exist", postId));
        }
        return postOptional.get();
    }


}
