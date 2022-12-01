package yibaben.com.MyBlog.services;

import yibaben.com.MyBlog.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> findAllPost();

    void createPost(PostDto postDto);

    PostDto findPostById(Long postId);

    void updatePost(PostDto postDto);

    void deletePost(Long postId);

    PostDto findPostByUrl(String postUrl);

    List<PostDto> searchPost(String query);
}
