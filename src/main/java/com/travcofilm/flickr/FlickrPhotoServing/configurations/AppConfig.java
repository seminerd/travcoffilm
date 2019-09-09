package com.travcofilm.flickr.FlickrPhotoServing.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import services.CommentsService;
import services.PhotosService;
import services.PhotosetsService;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

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

    @Bean(name = "photosService")
    @Scope(value = "singleton")
    public PhotosService photosService() {
        return new PhotosService();
    }
    @Scope(value = "singleton")
    @Bean(name = "commentsService")
    public CommentsService commentsService(){
        return new CommentsService();
    }
    @Scope(value = "singleton")
    @Bean(name = "photosetsService")
    public PhotosetsService photosetsService(){
        return new PhotosetsService();
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