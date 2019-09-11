package services;

import com.travcofilm.flickr.FlickrPhotoServing.dataaccess.CommentDAO;
import com.travcofilm.flickr.FlickrPhotoServing.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;

@Service
public class CommentsService {
    @Autowired
    CommentDAO commentDAO;
    @Autowired
    PhotosService photosService;

    @Transactional
    public void post(String content, BigInteger photo_id) {
        commentDAO.saveComment(content, photo_id);
    }
    public void deleteCommentByContent(String content,BigInteger id){
        commentDAO.deleteCommentByPhotoIdandContent(content,id);
    }
}
