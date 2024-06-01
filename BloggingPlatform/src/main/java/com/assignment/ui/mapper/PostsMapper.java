package com.assignment.ui.mapper;
import com.assignment.domain.model.Post;
import com.assignment.ui.dto.CreatePostRequest;
import com.assignment.ui.dto.PostResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public abstract class PostsMapper {
   public abstract Post toPost(CreatePostRequest request);
   public abstract PostResponse fromPost(Post post);
   public abstract List<PostResponse> fromPostList(List<Post> posts);
}
