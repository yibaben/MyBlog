package yibaben.com.MyBlog.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import yibaben.com.MyBlog.dto.PostDto;
import yibaben.com.MyBlog.services.PostService;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Controller
public class PostController {
// Spring boot uses Interface for Injecting Dependencies to achieve loose coupling
    private PostService postService;

// Create a handler method, GET request and return model and view
    @GetMapping("/admin/posts")
    public String posts(Model model) {
        List<PostDto> postDtoList = postService.findAllPost();
        model.addAttribute("postList", postDtoList);
        return "/admin/posts";
    }

// handler method for newPost request.
    @GetMapping("/admin/posts/newpost")
    public String newPostForm(Model model) {
        PostDto postDto = new PostDto();
        model.addAttribute("newPost", postDto);
        return "/admin/create_post";
    }

// Handler method to handle Post submit request.
    @PostMapping("/admin/posts")
    public String createPost(@Valid @ModelAttribute("newPost") PostDto postDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("newPost", postDto);
            return "/admin/create_post";
        }
        postDto.setUrl(getPostUrl(postDto.getTitle()));
        postService.createPost(postDto);
        return "redirect:/admin/posts";
    }

// Handler Method to get edit post request.
    @GetMapping("/admin/posts/{postId}/edit")
    public String editPostForm(@PathVariable("postId") Long postId, Model model) {
        PostDto postDto = postService.findPostById(postId);
        model.addAttribute("editPost", postDto);
        return "/admin/edit_post";
    }

// handler Method to Submit edit-post form and store in database.
    @PostMapping("/admin/posts/{postId}")
    public String updatePost(@PathVariable("postId") Long postId,
                             @Valid @ModelAttribute("updatedPost") PostDto postDto,
                             BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("updatedPost", postDto);
            return "/admin/edit_post";
        }
        postDto.setId(postId);
        postService.updatePost(postDto);
        return "redirect:/admin/posts";
    }

// Handler method to handle delete post request.
    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
        return "redirect:/admin/posts";
    }

// Handler method to handle view post request.
    @GetMapping("/admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl") String postUrl, Model model) {
        PostDto postDto = postService.findPostByUrl(postUrl);
        model.addAttribute("vwPost", postDto);
        return "/admin/view_post";
    }

// Handler method to handle search post request
// E.G; localhost:8080/admin/posts/search?query=java
    @GetMapping("/admin/posts/search")
    public String searchPosts(@RequestParam(value = "query") String query, Model model) {
        List<PostDto> postDtoList = postService.searchPost(query);
        model.addAttribute("searchPost", postDtoList);
        return "admin/posts";
    }


// Method to get PostUrl.
    public static String getPostUrl(String postTitle) {
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\s+", "-");
        url = url.replaceAll("[A-Za-z0-9]", "-");
        return url;
    }
}
