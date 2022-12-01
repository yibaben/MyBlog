package yibaben.com.MyBlog.serviceImpl;

import org.springframework.stereotype.Service;
import yibaben.com.MyBlog.dto.PostDto;
import yibaben.com.MyBlog.entity.Post;
import yibaben.com.MyBlog.mapper.PostMapper;
import yibaben.com.MyBlog.repository.PostRepository;
import yibaben.com.MyBlog.services.PostService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    // Note: when there is a single constructor, Spring boot will automatically Autowire or inject the dependencies.
    // Therefore, I don't need to use @Autowired Annotation on the constructor base Dependency Injection.
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> findAllPost() {
    // To implement this method, call the findAll() in JpaRepository to find all posts.
    // But the findAll() return a List of Post, therefore I need to convert the List of Post(Entity) to List of PostDto(Dto)
    // Using Stream API with functional interface(lambda expression or method reference).
        List<Post> posts = postRepository.findAll();
    // return posts.stream().map((post -> PostMapper.mapToPostDto(post))).collect(Collectors.toList());
        return posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDto postDto) {
    // First convert the PostDto to Post Entity.
        Post post = PostMapper.mapToPost(postDto);
        postRepository.save(post);
    }

    @Override
    public PostDto findPostById(Long postId) {
        Post post = postRepository.findById(postId).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public void updatePost(PostDto postDto) {
        Post post = PostMapper.mapToPost(postDto);
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PostDto findPostByUrl(String postUrl) {
        Post post = postRepository.findByUrl(postUrl).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<PostDto> searchPost(String query) {
        List<Post> posts = postRepository.searchPosts(query);
        return posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }
}
