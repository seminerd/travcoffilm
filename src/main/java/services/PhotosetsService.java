package services;
import com.travcofilm.flickr.FlickrPhotoServing.dataaccess.PhotosetDAO;
import com.travcofilm.flickr.FlickrPhotoServing.models.Comment;
import com.travcofilm.flickr.FlickrPhotoServing.models.Photo;
import com.travcofilm.flickr.FlickrPhotoServing.models.Photoset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class PhotosetsService {
    @Autowired
    PhotosetDAO photosetDAO;
    @Autowired
    PhotosService photosService;
    @Autowired
    CommentsService commentsService;

    public List<Photoset> findAll() {
        return photosetDAO.findAll();
    }

    @Transactional
    public void savePhotoset(Photoset photoset) {
        photosetDAO.savePhotoset(photoset.getId(), photoset.getPrimary(), photoset.getOwner(), photoset.getOwnername(), photoset.getTitle(), photoset.getTotal());
        System.out.println(photoset.getId());

        Iterable<Photo> photos = photoset.getPhoto();
        for (Photo photo : photos) {
            photo.formSource();
            photo.setPhotoset(photoset);
            photosService.save(photo);
        }

    }
}
