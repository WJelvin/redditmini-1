package com.Daddit.app.controllers;

import com.Daddit.app.models.Category;
import com.Daddit.app.models.Dad;
import com.Daddit.app.services.DadService;
import com.Daddit.app.models.Post;
import com.Daddit.app.models.Vote;
import com.Daddit.app.repositories.CategoryRepository;
import com.Daddit.app.services.CategoryService;
import com.Daddit.app.services.PostService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/post")
public class PostController {

    private PostService postService;
    private DadService dadService;
    private CategoryService categoryService;
    private CategoryRepository categoryRepository;

    @Autowired
    public PostController(PostService postService, DadService dadService, CategoryService categoryService, CategoryRepository categoryRepository) {
        this.postService = postService;
        this.dadService = dadService;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/getAll")
    public List<Post> getAllPosts() {
        return postService.findAllPosts();
    }

    @GetMapping("/{dadId}")
    public List<Post> getPostsFromDad(@PathVariable Long dadId) {
        return postService.findPostsFromDad(dadId);
    }

    @GetMapping("/postid/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.findPostById(id).get();
    }

    @GetMapping("/getTop10")
    public List<Post> getTop10Posts() {
        return postService.findtop10Posts();
    }
    
    @GetMapping("/getPostsSortedByLike")
    public List<Post> getPostsSortedByLike() {
        return postService.findPostsSortedByLike();
    }

    @GetMapping("/getAllSortedByDate")
    public List<Post> getAllSortedByDate() {
        return postService.findPostsSortedByDate();
    }

    @PostMapping("/newPost")
    public Post newPost(@RequestBody Map<String, String> body) {

        String headline = body.get("headline");
        String content = body.get("content");
//        Category category = new Category(body.get("category"));
        Long id = Long.parseLong(body.get("id"));
//        List<Category> categories = new ArrayList<>();
//        categories.add(category);
        
//        if (!categoryService.findByName(body.get("category")).isPresent()) {
//            categoryService.addCategory(category);
//        }
        
        Dad dad = dadService.findDadById(id).get();
        Post post = postService.newPost(new Post(content, headline, body.get("category"), dad));
        return post;
    }

    @PostMapping("/deletePost")
    public Post deletePost(@RequestBody Map<String, String> body) {
        postService.deletePostById(Long.parseLong(body.get("id")));

        return new Post();
    }

    @PostMapping("/vote")
   public Post updatePost(@RequestBody Map<String, Long> data) {

       Post post = postService.findPostById(data.get("postId")).get();

       Dad dad = dadService.findDadById(data.get("userId")).get();

       Vote newVote = new Vote(data.get("voteValue").intValue(), dad, post);

       for (Iterator<Vote> iterator = post.getVotes().iterator(); iterator.hasNext();) {
           Vote oldVote = iterator.next();
           if (oldVote.getDad().getId() == newVote.getDad().getId()) {
               iterator.remove();
           }
       }

       post.getVotes().add(newVote);
       return postService.updatePost(post);
   }

    @PostMapping("/search")
    public ResponseEntity<List<Post>> getSearchPosts(@RequestParam String str) {
        List<Post> searchList = postService.findPostsbyString(str);
        if (searchList.isEmpty()) {
            return ResponseEntity.badRequest().body(searchList);
        } else {
            return ResponseEntity.ok(searchList);
        }
    }

    @GetMapping("/getAll/{category}")
    public List<Post> getPostsFromCategory(@PathVariable String category) {
        List<Post> posts = postService.findAllPosts();
        List<Post> filteredPosts = new ArrayList();
        
        filteredPosts = posts.stream().filter(p -> p.getCategory().equals(category)).collect(Collectors.toList());
        
//        for (Post post : posts) {
//            if (post.getCategory().equals(category)) {
//                filteredPosts.add(post);
//            }
//        }
        System.out.println(filteredPosts.get(0).getCategory());
        return filteredPosts;
    }
}
