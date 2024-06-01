package com.assignment.ui.mapper;

import com.assignment.domain.model.Post;
import com.assignment.ui.dto.CreatePostRequest;
import com.assignment.ui.dto.PostResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-01T17:42:21+0530",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class PostsMapperImpl extends PostsMapper {

    @Override
    public Post toPost(CreatePostRequest request) {
        if ( request == null ) {
            return null;
        }

        Post post = new Post();

        post.setAuthor( request.getAuthor() );
        post.setContent( request.getContent() );
        post.setTitle( request.getTitle() );

        return post;
    }

    @Override
    public PostResponse fromPost(Post post) {
        if ( post == null ) {
            return null;
        }

        PostResponse postResponse = new PostResponse();

        postResponse.setAuthor( post.getAuthor() );
        postResponse.setContent( post.getContent() );
        postResponse.setCreationDate( post.getCreationDate() );
        postResponse.setId( post.getId() );
        postResponse.setTitle( post.getTitle() );

        return postResponse;
    }

    @Override
    public List<PostResponse> fromPostList(List<Post> posts) {
        if ( posts == null ) {
            return null;
        }

        List<PostResponse> list = new ArrayList<PostResponse>( posts.size() );
        for ( Post post : posts ) {
            list.add( fromPost( post ) );
        }

        return list;
    }
}
