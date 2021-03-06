package com.travcofilm.flickr.FlickrPhotoServing.controllers;

import com.travcofilm.flickr.FlickrPhotoServing.models.Comment;
import com.travcofilm.flickr.FlickrPhotoServing.models.Photo;
import com.travcofilm.flickr.FlickrPhotoServing.models.Photoset;
import com.travcofilm.flickr.FlickrPhotoServing.models.WrapSet;
import com.travcofilm.flickr.FlickrPhotoServing.utilities.JsonReturnModifier;
import com.travcofilm.flickr.FlickrPhotoServing.utilities.Logger;
import kong.unirest.JacksonObjectMapper;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import com.travcofilm.flickr.FlickrPhotoServing.services.CommentsService;
import com.travcofilm.flickr.FlickrPhotoServing.services.PhotosService;
import com.travcofilm.flickr.FlickrPhotoServing.services.PhotosetsService;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@CrossOrigin("*")
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
    @Qualifier("getLogger")
    @Autowired
    Logger logger;

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
        logger.postLogger();
        return response.getPhotoset();

    }

    @GetMapping("/home")
    public List<Photo> getPhotoset() {
        logger.getLogger();

        return photosService.findAll();
    }

    @PostMapping("/comment")
    public Comment postComment(@RequestParam("content") String comment, @RequestParam("photo_id") BigInteger photo_id) {
        logger.postLogger();
        commentsService.post(comment, photo_id);
        return commentsService.findCommentByContentAndPhoto(comment,photo_id);

    }

    @DeleteMapping("/comment")
    public void deleteComment(@RequestParam("content") String comment, @RequestParam("photo_id") BigInteger photo_id) {
        commentsService.deleteCommentByContent(comment, photo_id);

    }
    @GetMapping("/log")
    public Logger getLog(){
        return logger;
    }
}
