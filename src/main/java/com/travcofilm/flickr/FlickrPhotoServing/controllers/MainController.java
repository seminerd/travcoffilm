package com.travcofilm.flickr.FlickrPhotoServing.controllers;


import com.travcofilm.flickr.FlickrPhotoServing.models.Comment;
import com.travcofilm.flickr.FlickrPhotoServing.models.Photo;
import com.travcofilm.flickr.FlickrPhotoServing.models.Photoset;
import com.travcofilm.flickr.FlickrPhotoServing.models.WrapSet;
import kong.unirest.JacksonObjectMapper;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.CommentsService;
import services.PhotosService;
import services.PhotosetsService;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class MainController {
    @Autowired
    PhotosetsService photosetsService;
    @Autowired
    PhotosService photosService;
    @Autowired
    CommentsService commentsService;

    @GetMapping("/reloadDB")
    @Transactional
    public Photoset getImage() throws UnirestException {
        String html;

        Unirest.config().setObjectMapper(new JacksonObjectMapper());
        WrapSet response = Unirest.get("https://www.flickr.com/services/rest/?method=flickr.photosets.getPhotos&api_key=a5aec4059b3d7a7604bdd1e101ff59de&photoset_id=72157710719229263&user_id=138159876%40N04&format=json&nojsoncallback=1")
                .asObject(WrapSet.class).getBody();
        photosetsService.savePhotoset(response.getPhotoset());
     return response.getPhotoset();
    }
    @GetMapping("/home")
    public List<Photoset> getPhotoset(){
        return photosetsService.findAll();
   }
   @PostMapping("/comment")
    public void postComment(@RequestParam("content") String comment,@RequestParam("photo_id") int photo_id){
        commentsService.post(comment,photo_id);
  }
  @DeleteMapping("/comment")
    public void deleteComment(@RequestParam("content") String comment,@RequestParam("photo_id") int photo_id){

  }
}
