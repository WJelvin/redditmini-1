package com;

import com.Daddit.app.models.Vote;
import com.Daddit.app.models.Category;
import com.Daddit.app.models.Dad;
import com.Daddit.app.models.Post;
import com.Daddit.app.repositories.DadRepository;
import com.Daddit.app.repositories.VoteRepository;
import com.Daddit.app.repositories.CategoryRepository;
import com.Daddit.app.repositories.PostRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DadditApplication {

    @Autowired
    private DadRepository dadRepo;
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private VoteRepository voteRepo;
    @Autowired
    private CategoryRepository categoryRepo;

    public static void main(String[] args) {
        SpringApplication.run(DadditApplication.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return args -> {
            
            Dad dad1 = new Dad("daddy1", "daddy", false);
            Dad dad2 = new Dad("daddy2", "daddy", false);
            Dad dad3 = new Dad("daddy3", "daddy", false);
            Dad dad4 = new Dad("BigDaddy", "chef", true);

            dadRepo.save(dad1);
            dadRepo.save(dad2);
            dadRepo.save(dad3);
            dadRepo.save(dad4);
            
            Category category1 = new Category("r-rated");
            Category category2 = new Category("mild");
            Category category3 = new Category("mom joke");
            Category category4 = new Category("holiday");
            
            categoryRepo.save(category1);
            categoryRepo.save(category2);
            categoryRepo.save(category3);
            categoryRepo.save(category4);
            
            List<Category> categories = new ArrayList<>();
            List<Category> categories2 = new ArrayList<>();

            
            categories.add(category1);
            categories.add(category2);
            categories.add(category3);
            categories.add(category4);
            categories2.add(category4);
            

            Post post1 = new Post("A dad walks into a bar", "My first joke",  dad1);
            Post post2 = new Post("A dad walks out of a shop", "My second joke",  dad2);
            
            post1.setCategories(categories);
            post2.setCategories(categories2);

            List<Post> posts = new ArrayList<>();
            List<Post> posts2 = new ArrayList<>();
            postRepo.save(post1);
            postRepo.save(post2);
            posts.add(post1);
            posts.add(post2);
            posts2.add(post1);

            
            
            category1.setPosts(posts2);
            category2.setPosts(posts2);
            category3.setPosts(posts2);
            category4.setPosts(posts);
                       
           
            

            List<Vote> votesList = new ArrayList<>();

            Vote vote1 = new Vote(1, dad3, post1);
            Vote vote2 = new Vote(1, dad2, post1);
            Vote vote3 = new Vote(1, dad4, post1);
            Vote vote4 = new Vote(1, dad1, post1);

            votesList.add(vote1);
            votesList.add(vote2);
            votesList.add(vote3);
            votesList.add(vote4);
            
            List<Vote> votesList2 = new ArrayList<>();

            Vote vote5 = new Vote(1, dad3, post2);
            Vote vote6 = new Vote(1, dad2, post2);
           

            votesList2.add(vote5);
            votesList2.add(vote6);
       
            
            post1.setVotes(votesList);
                     
            
            post2.setVotes(votesList2);
            
            
        };
    }
}
