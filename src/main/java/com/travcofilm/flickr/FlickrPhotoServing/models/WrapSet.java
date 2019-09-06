package com.travcofilm.flickr.FlickrPhotoServing.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class WrapSet {
    @Id
    private Long id;
    @OneToOne
    private Photoset photoset;
    private  String stat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Photoset getPhotoset() {
        return photoset;
    }

    public void setPhotoset(Photoset photoset) {
        this.photoset = photoset;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
