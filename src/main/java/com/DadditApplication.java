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

//            Post post1 = new Post("A dad walks into a bar", "My first joke", dad1);
//
//            List<Category> categories = new ArrayList<>();
//
//            Category category1 = new Category("r-rated");
//            Category category2 = new Category("mild");
//            Category category3 = new Category("mom joke");
//
//            categories.add(category1);
//            categories.add(category2);
//            categories.add(category3);
//
//            List<Vote> votesList = new ArrayList<>();
//
//            Vote vote1 = new Vote(1, dad3, post1);
//            Vote vote2 = new Vote(1, dad2, post1);
//            Vote vote3 = new Vote(1, dad4, post1);
//            Vote vote4 = new Vote(1, dad1, post1);
//
//            votesList.add(vote1);
//            votesList.add(vote2);
//            votesList.add(vote3);
//            votesList.add(vote4);
//
//            post1.setCategories(categories);
//            post1.setVotes(votesList);
//            postRepo.save(post1);
        };
    }
}
