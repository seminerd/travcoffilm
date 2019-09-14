package com.travcofilm.flickr.FlickrPhotoServing.utilities;

import com.travcofilm.flickr.FlickrPhotoServing.models.Photo;

import java.util.List;
import java.util.Random;

public class JsonReturnModifier {
    public static Photo getRandomElement(List<Photo> list)
    {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
}
