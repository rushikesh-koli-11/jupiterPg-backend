package com.example.jupiterPg.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.jupiterPg.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "jupiterpg";
    }

    @Override
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(
                "mongodb+srv://rushikeshkoli87400_db_user:bAlWXXFeG3zZJs1I@cluster0.o03z07q.mongodb.net/jupiterpg?appName=Cluster0"
        );
    }
}
