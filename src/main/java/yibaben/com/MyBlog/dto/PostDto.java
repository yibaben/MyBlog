package yibaben.com.MyBlog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    @NotEmpty(message = "Post Title field must not be empty")
    private String title;
    private String url;
    @NotEmpty(message = "Post Content field must not be empty")
    private String content;
    @NotEmpty(message = "Post Description field must not be empty")
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
