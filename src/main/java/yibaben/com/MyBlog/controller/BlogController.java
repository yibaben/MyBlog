package yibaben.com.MyBlog.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import yibaben.com.MyBlog.dto.PostDto;
import yibaben.com.MyBlog.services.PostService;

import java.util.List;

@AllArgsConstructor
@Controller
public class BlogController {

    private PostService postService;

    // Hanadler Method to handle Blog Home page http://localhost:8080/
    @GetMapping("/")
    public String blogPosts(Model model) {
        List<PostDto> viewBlogPosts = postService.findAllPost();
        model.addAttribute("viewBlogPosts", viewBlogPosts);
        return "blog/view_posts";
    }
}
