package com.travcofilm.flickr.FlickrPhotoServing.dataaccess;

import com.travcofilm.flickr.FlickrPhotoServing.models.Photoset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotosetDAO extends JpaRepository<Photoset, Long> {
    @Modifying
    @Query(value = "INSERT IGNORE INTO `photoset` (`id`, `primary`, `owner`, `owner_name`, `title`, `total`) VALUES (:id, :pri, :own, :ownname, :tit, :tot)", nativeQuery = true)
    void savePhotoset(@Param("id") Long id, @Param("pri") String primary, @Param("own") String owner, @Param("ownname") String ownername, @Param("tit") String title, @Param("tot") int total);

}
