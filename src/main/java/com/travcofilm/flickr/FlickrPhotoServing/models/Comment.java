package com.travcofilm.flickr.FlickrPhotoServing.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "content")
    private String content;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "photo_fk")
    private Photo photo;


}
