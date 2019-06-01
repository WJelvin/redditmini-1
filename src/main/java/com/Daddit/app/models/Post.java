package com.Daddit.app.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    private String content;
    private String headline;
//    @ManyToOne(cascade = CascadeType.ALL)
//    @UniqueConstraint(name = "category")
//    private Category category;
    private String category;
//    @ManyToMany(cascade = CascadeType.PERSIST)
//    private List<Category> category;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Vote> votes = new ArrayList();
    
    private LocalDate created;

    @OneToOne
    private Dad dad;

    public Post() {
    }


    public Post(String content, String headline, String category, Dad dad) {
        this.content = content;
        this.headline = headline;
        this.category = category;
        this.dad = dad;
        this.created = LocalDate.now();
    }
    public Post(String content, String headline, Dad dad) {
        this.content = content;
        this.headline = headline;
        this.dad = dad;
        this.created = LocalDate.now();
    }

    public Post(String content, String headline, String category, List<Vote> votes, Dad dad) {
        this.content = content;
        this.headline = headline;
        this.category = category;
        this.votes = votes;
        this.created = LocalDate.now();
        this.dad = dad;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Dad getDad() {
        return dad;
    }

    public void setDad(Dad dad) {
        this.dad = dad;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

}
