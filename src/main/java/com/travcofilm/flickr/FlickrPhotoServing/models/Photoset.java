package com.travcofilm.flickr.FlickrPhotoServing.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="photoset")
public class Photoset {
    @Id
    @Column(name="id")
    private Long id;
    @Column(name="primary")
    private String primary;
    @Column(name="owner")
    private String owner;
    @Column(name="owner_name")
    private String ownername;
    @Column(name="title")
    private String title;


    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotal() {
        return total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Set<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(Set<Photo> photo) {
        this.photo = photo;
    }

    @Column(name="total")
    private int total;
    @OneToMany
//    @JsonManagedReference
    @JoinColumn(name="photoset_fk")
    private Set<Photo> photo;

}
