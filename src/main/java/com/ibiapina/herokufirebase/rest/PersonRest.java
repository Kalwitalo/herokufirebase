package com.ibiapina.herokufirebase.rest;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.ibiapina.herokufirebase.configuration.Firebase;
import com.ibiapina.herokufirebase.model.Person;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("person")
public class PersonRest {

    private Firestore db;

    public PersonRest() throws IOException {
        this.db = new Firebase().firestore();
    }

    @GetMapping
    public Collection<Person> findAll() throws ExecutionException, InterruptedException {

        Collection<Person> persons = new ArrayList<>();

        // asynchronously retrieve all users
        ApiFuture<QuerySnapshot> query = db.collection("Person").get();

        // query.get() blocks on response
        QuerySnapshot querySnapshot = query.get();
        Collection<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        for (QueryDocumentSnapshot document : documents) {
            persons.add(new Person(document.getString("name")));
        }

        return persons;
    }

    @PostMapping()
    public Person save(@RequestBody Person person) throws IOException, ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("Person").document();
        // Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("name", person.getName());
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);

        // ...
        // result.get() blocks on response
        System.out.println("Update time : " + result.get().getUpdateTime());

        return person;
    }
}
