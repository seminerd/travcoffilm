package com.travcofilm.flickr.FlickrPhotoServing.dataaccess;

import com.travcofilm.flickr.FlickrPhotoServing.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDAO extends JpaRepository<Comment,Long> {
    @Modifying
    @Query(value = "INSERT INTO `yhuiv2y1d1q6q805`.`comment` (`content`, `photo_fk`) VALUES (:content,:photo_id)",nativeQuery = true)
    void saveComment(@Param("content") String content,@Param("photo_id") int id);
}
