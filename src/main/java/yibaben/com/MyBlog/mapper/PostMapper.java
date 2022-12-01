package yibaben.com.MyBlog.mapper;

import yibaben.com.MyBlog.dto.PostDto;
import yibaben.com.MyBlog.entity.Post;

public class PostMapper {

    // Map PostEntity to PostDto
    public static PostDto mapToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .url(post.getUrl())
                .content(post.getContent())
                .description(post.getDescription())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    // map PostDto to PostEntity
    public static Post mapToPost(PostDto postDto) {
        return Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .url(postDto.getUrl())
                .content(postDto.getContent())
                .description(postDto.getDescription())
                .createdAt(postDto.getCreatedAt())
                .updatedAt(postDto.getUpdatedAt())
                .build();
    }
}
