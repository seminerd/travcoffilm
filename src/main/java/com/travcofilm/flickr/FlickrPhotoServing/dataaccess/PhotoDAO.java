package com.travcofilm.flickr.FlickrPhotoServing.dataaccess;

import com.travcofilm.flickr.FlickrPhotoServing.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoDAO extends JpaRepository<Photo,Long> {

    @Query(value="select * from photo where id=?1",nativeQuery = true)
    Photo findPhotoById(int id);
    @Query(value="select * from photo ",nativeQuery = true)
    List<Photo> findAllPhotos();
}
