package services;

import com.travcofilm.flickr.FlickrPhotoServing.dataaccess.PhotoDAO;
import com.travcofilm.flickr.FlickrPhotoServing.models.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PhotosService {
    @Autowired
    PhotoDAO photoDAO;

    public Photo getPhotoById(int id) {
        return photoDAO.findPhotoById(id);
    }

    public List<Photo> getAllPhotos() {

        return photoDAO.findAllPhotos();
    }
    public void save(Photo photo){
        photoDAO.save(photo);
    }

    public List<Photo> findAll() {
        return photoDAO.findAll();
    }
}
