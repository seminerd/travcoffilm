package com.travcofilm.flickr.FlickrPhotoServing.utilities;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;

import java.time.LocalDate;
@Data
public class Logger {
    volatile String logcontent= " ";
    public void postLogger(){
        this.logcontent += " POST : "+ LocalDate.now() ;
        System.out.println(this.logcontent);
    }
    public void getLogger(){
        this.logcontent += " GET : " +LocalDate.now();
        System.out.println(this.logcontent);
    }


}
