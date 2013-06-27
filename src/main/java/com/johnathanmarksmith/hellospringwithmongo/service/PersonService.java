package com.johnathanmarksmith.hellospringwithmongo.service;

import com.johnathanmarksmith.hellospringwithmongo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Date:   6/27/13 / 11:47 AM
 * Author: Johnathan Mark Smith
 * Email:  john@johnathanmarksmith.com
 * <p/>
 * Comments:
 *
 *  this i the repository/service for Person object
 */


@Repository
public class PersonService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public static final String COLLECTION = "person";

    /**
     *
     * this will add a new person
     */
    public void addPerson(Person person) {
        if (!mongoTemplate.collectionExists(Person.class)) {
            mongoTemplate.createCollection(Person.class);
        }
        person.setId(UUID.randomUUID().toString());
        mongoTemplate.insert(person, COLLECTION);
    }

    /**
     *
     * this will return a list
     */
    public List<Person> listPerson() {
        return mongoTemplate.findAll(Person.class, COLLECTION);
    }

    /**
     *
     * this will delete a person
     *
     */
    public void deletePerson(Person person) {
        mongoTemplate.remove(person, COLLECTION);
    }

    /**
     *
     * this will update a person
     *
     */
    public void updatePerson(Person person) {
        mongoTemplate.insert(person, COLLECTION);
    }
}
