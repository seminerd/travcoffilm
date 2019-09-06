package com.travcofilm.flickr.FlickrPhotoServing.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "photo")
public class Photo {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "secret")
    private String secret;
    @Column(name = "server")
    private String server;
    @Column(name = "farm")
    private int farm;
    @Column(name = "title")
    private String title;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "photoset_fk")
    private Photoset photoset;
    @OneToMany
    @JsonManagedReference
    @JoinColumn(name = "photo_fk")
    Set<Comment> comments;
    @Column(name="source")
    private String source;


    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getFarm() {
        return farm;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
    public Photoset getPhotoset() {
        return photoset;
    }

    public void formSource() {
        this.source = getSource();
    }

    public void setPhotoset(Photoset photoset) {
        this.photoset = photoset;
    }
    public String getSource(){
        String url = "https://farm"+this.getFarm()+".staticflickr.com/"+this.getServer()+"/"+this.getId()+"_"+this.getSecret()+"_b.jpg";

        return url;
    }
}
