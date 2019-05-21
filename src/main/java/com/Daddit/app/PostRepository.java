package com.Daddit.app;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /*
    vi kanske behöver querymetoder? 
    
    - findTopPost
    - findTopFiveposts
    - findByDad x
    - findByCategory
    
     */
    
//    @Query(value = "SELECT p FROM Post p WHERE p.dad = (SELECT d FROM Dad d WHERE d.id = ?1)")
//    public List<Post> findByDadId(Long id);

//    @Query(value = "SELECT * FROM Dad WHERE Category = ?1", nativeQuery = true)
//    Optional<Post> findByCategory(Category category);
}
