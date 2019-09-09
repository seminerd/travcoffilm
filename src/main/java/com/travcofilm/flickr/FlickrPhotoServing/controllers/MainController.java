package com.travcofilm.flickr.FlickrPhotoServing.controllers;

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
import java.util.Properties;
@CrossOrigin("http://localhost:3000")
@RestController
public class MainController {
    @Autowired
    Properties prop;
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
        WrapSet response =
                Unirest.get("https://www.flickr.com/services/rest/?method=flickr.photosets.getPhotos&api_key="
                        + prop.getProperty("api_key")
                        + "&photoset_id="
                        + prop.getProperty("photoset_id")
                        + "&user_id="
                        + prop.getProperty("user_id")
                        + "&format=json&nojsoncallback=1")
                        .asObject(WrapSet.class)
                        .getBody();
        photosetsService.savePhotoset(response.getPhotoset());
        return response.getPhotoset();
    }

    @GetMapping("/home")
    public List<Photo> getPhotoset() {
        return photosService.findAll();
    }

    @PostMapping("/comment")
    public void postComment(@RequestParam("content") String comment, @RequestParam("photo_id") int photo_id) {
        commentsService.post(comment, photo_id);
    }

    @DeleteMapping("/comment")
    public void deleteComment(@RequestParam("content") String comment, @RequestParam("photo_id") int photo_id) {
        commentsService.deleteCommentByContent(comment, photo_id);

    }
}
