package com.wundermancommerce.interviewtests.graph.service;

import com.wundermancommerce.interviewtests.graph.model.Person;
import com.wundermancommerce.interviewtests.graph.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @Mock
    PersonRepository personRepository;

    PersonService personService;

    @Before
    public void setUp() throws Exception {

        personService = new PersonService(personRepository);
    }

    /**
     * Test case for
     * Excecise 1
     * Please implement code and data structures that read the files
     *
     * src/test/java/resources/people.csv
     * src/test/java/resources/relationships.csv
     * and use them to build an in-memory data structure that represents the people in the file and their relationships with each other.
     *
     * Test case for
     * Exercise 2 - Validate correct people loaded
     * Write a test to validate that you have loaded the expected number of people.
     *
     */
    @Test
    public void testGetAllPersons() {

        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource("people.csv").getFile());

        List<Person> allPersons = personService.readPersons(file);

        //when(personRepository.getAllPersons()).thenReturn(new HashSet<>(allPersons));

        final int expectedNumberOfPeople = 12;

        Assert.assertEquals(expectedNumberOfPeople, allPersons.size());

    }

    @Test
    public void testPersonBob() {

        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource("people.csv").getFile());

        List<Person> allPersons = personService.readPersons(file);

        when(personRepository.getAllPersons()).thenReturn(new HashSet<>(allPersons));

        String expectedPersonName = "Bob";

        Person personFound = personService.findPerson("Bob");

        Assert.assertEquals(personFound.getName(), expectedPersonName);

    }


}