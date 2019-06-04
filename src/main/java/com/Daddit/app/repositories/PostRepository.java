package com.Daddit.app.repositories;

import com.Daddit.app.models.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    
    List<Post> findByOrderByCreatedDesc();
}
