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
            Category category5 = new Category("food");
            
            categoryRepo.save(category1);
            categoryRepo.save(category2);
            categoryRepo.save(category3);
            categoryRepo.save(category4);
            categoryRepo.save(category5);
            
            List<Category> categories = new ArrayList<>();
            List<Category> categories2 = new ArrayList<>();
            List<Category> categories3 = new ArrayList<>();
            List<Category> categories4 = new ArrayList<>();
            
            categories.add(category1);
            categories.add(category2);
            categories.add(category4);
            
            categories2.add(category4);
            categories3.add(category5);
            categories4.add(category3);

            Post post1 = new Post("It deep ends.", "Is this pool safe for diving?", dad1);
            Post post2 = new Post("No, but April May", "Can February March?", dad2);
            Post post3 = new Post("They’re the wurst.", "I hate jokes about German sausages", dad1);
            Post post4 = new Post("The felines not mutual.", "My wife loves our cats, but they don’t seem to care about her.", dad1);
            
            post1.setCategories(categories);
            post2.setCategories(categories2);
            post3.setCategories(categories3);
            post4.setCategories(categories4);

            List<Post> posts = new ArrayList<>();
            List<Post> posts2 = new ArrayList<>();
            List<Post> posts3 = new ArrayList<>();
            List<Post> posts4 = new ArrayList<>();
           
            category1.setPosts(posts2);
            category2.setPosts(posts2);
            category3.setPosts(posts2);
            category4.setPosts(posts);
            category5.setPosts(posts3);
            category3.setPosts(posts4);
            
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
            
            postRepo.save(post1);
            postRepo.save(post2);
            postRepo.save(post3);
            postRepo.save(post4);
            posts.add(post1);
            posts.add(post2);
            posts2.add(post1);
            posts3.add(post3);
            posts4.add(post4);
            
        };
    }
}
