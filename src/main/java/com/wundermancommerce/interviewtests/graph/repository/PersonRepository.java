package com.wundermancommerce.interviewtests.graph.repository;

import com.wundermancommerce.interviewtests.graph.model.Person;

import java.util.Set;

public interface PersonRepository {

    void addPerson(Person person);

    Set<Person> getAllPersons();

}
