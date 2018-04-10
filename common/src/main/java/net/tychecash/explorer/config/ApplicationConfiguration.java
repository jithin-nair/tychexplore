/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.config;

import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 *
 * @author Jithin
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        String mongoURI = "mongodb://" + "testuser" + ":" + "password" + "@" + "127.0.0.1" + ":" + "27017" + "/" + "test";
        MongoClientURI mongoClientURI = new MongoClientURI(mongoURI);
        return new SimpleMongoDbFactory(mongoClientURI);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }
}
