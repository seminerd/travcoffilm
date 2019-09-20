package com.travcofilm.flickr.FlickrPhotoServing.configurations;

import com.travcofilm.flickr.FlickrPhotoServing.utilities.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.travcofilm.flickr.FlickrPhotoServing.services.CommentsService;
import com.travcofilm.flickr.FlickrPhotoServing.services.PhotosService;
import com.travcofilm.flickr.FlickrPhotoServing.services.PhotosetsService;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Bean
    @Scope("singleton")
    public Logger getLogger(){
        return new Logger();
    }

    @Autowired
    Environment env;

    @Bean

    public static PropertySourcesPlaceholderConfigurer getCongig() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    @Bean("prop")
    @Scope("singleton")
    @Primary
    public Properties getProp(){

        Properties prop = new Properties();
        prop.put("api_key",env.getProperty("user.flickr.apikey"));
        prop.put("user_id",env.getProperty("user.flickr.userid"));
        prop.put("photoset_id",env.getProperty("user.flickr.photosetid"));
        return prop;

    }


    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        return dataSource;

    }

}