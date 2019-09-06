package services;

import com.travcofilm.flickr.FlickrPhotoServing.dataaccess.CommentDAO;
import com.travcofilm.flickr.FlickrPhotoServing.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentsService {
    @Autowired
    CommentDAO commentDAO;
    @Autowired
    PhotosService photosService;

    @Transactional
    public void post(String content, int photo_id) {
        commentDAO.saveComment(content, photo_id);
    }
}
