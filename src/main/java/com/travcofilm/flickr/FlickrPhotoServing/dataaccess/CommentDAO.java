package com.travcofilm.flickr.FlickrPhotoServing.dataaccess;

import com.travcofilm.flickr.FlickrPhotoServing.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface CommentDAO extends JpaRepository<Comment,Long> {
    @Modifying
    @Query(value = "INSERT INTO `yhuiv2y1d1q6q805`.`comment` (`content`, `photo_fk`) VALUES (:content,:photo_id)",nativeQuery = true)
    void saveComment(@Param("content") String content,@Param("photo_id") BigInteger id);
    @Modifying
    @Query(value = "DELETE * FROM `yhuiv2y1d1q6q805`.`comment` WHERE `content` = ?1 AND `photo_fk` = ?2",nativeQuery = true)
    void deleteCommentByPhotoIdandContent(String content,BigInteger photo_id);
    @Query(value="SELECT * FROM `yhuiv2y1d1q6q805`.`comment` WHERE `content` =?1 AND `photo_fk`=?2 ",nativeQuery = true)
    Comment findCommentByContentAndAndPhoto(String content,BigInteger photo_fk);

}
