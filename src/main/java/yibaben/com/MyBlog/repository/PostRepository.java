package yibaben.com.MyBlog.repository;

import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import yibaben.com.MyBlog.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByUrl(String postUrl);


    @Query("SELECT p from Post p WHERE " +
            " p.title LIKE CONCAT('%', :query, '%') OR " +
            " p.description LIKE CONCAT('%', :query, '%')")
    List<Post> searchPosts(String query);

}
