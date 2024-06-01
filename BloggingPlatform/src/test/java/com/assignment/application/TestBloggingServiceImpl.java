package com.assignment.application;
import com.assignment.domain.model.Post;
import com.assignment.domain.repository.PostQueryRepositoryImpl;
import com.assignment.domain.repository.PostRepository;
import com.assignment.ui.dto.Filter;
import com.assignment.ui.dto.PageDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TestBloggingServiceImpl {
    @Mock
    private PostRepository postRepository;

    @Mock
    private PostQueryRepositoryImpl postQueryRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    public void shouldCreatePost() {
        //Given
        Post testPost = new Post();
        testPost.setTitle("Test Post");
        testPost.setAuthor("Mark");
        testPost.setId(1L);

        //When
        when(postRepository.save(any())).thenReturn(testPost);
        when(postRepository.findById(1L)).thenReturn(java.util.Optional.of(testPost));
        Post post = postService.createPost(testPost);

        //Then
        assertThat(postService.getPost(1L),equalTo(testPost));
    }

    @Test
    public void shouldUpdatePost() {
        Post testPost = new Post();
        testPost.setTitle("Test Post");
        testPost.setAuthor("Mark");
        testPost.setId(1L);

        when(postRepository.findById(1L)).thenReturn(java.util.Optional.of(testPost));
        testPost.setAuthor("Watson");
        when(postRepository.save(any())).thenReturn(testPost);


        Post post = postService.updatePost(1L, testPost);
        assertThat(postService.getPost(1L),equalTo(testPost));
    }

    @Test
    public void shouldDeletePost() {
        //Given
        Post testPost = new Post();
        testPost.setTitle("Test Post");
        testPost.setAuthor("Mark");
        testPost.setId(1L);

        //When
        when(postRepository.save(any())).thenReturn(testPost);
        when(postRepository.findById(any())).thenReturn(Optional.of(testPost));
        postService.createPost(testPost);
        postService.deletePost(1L);

        //Then
        verify(postRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void shouldListPosts() {
        //Given
        List<Post> testPosts = new ArrayList<>();

        for(int i=0;i<3;i++) {
        Post testPost = new Post();
        testPost.setTitle("Test Post");
        testPost.setAuthor("Mark");
        testPost.setId((long) i);
        testPosts.add(testPost);
        }

        //When
        when(postQueryRepository.listAllPosts(PageRequest.of(0, 3), new Filter())).thenReturn(testPosts);
        postService.listAllPosts(new PageDto(0, 3), new Filter());

        //Then
        verify(postQueryRepository, times(1)).listAllPosts(any(), any());
        assertThat(postService.listAllPosts(new PageDto(0, 3), new Filter()), equalTo(testPosts));
    }

    @Test
    public void shouldGetPost() {
        //Given
        Post testPost = new Post();
        testPost.setTitle("Test Post");
        testPost.setAuthor("Mark");
        testPost.setId(1L);

        //When
        when(postRepository.findById(1L)).thenReturn(java.util.Optional.of(testPost));
        when(postRepository.save(any())).thenReturn(testPost);

        postService.createPost(testPost);
        postService.getPost(1L);

        //Then
        assertThat(postService.getPost(1L),equalTo(testPost));
    }

}
