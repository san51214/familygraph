package com.wundermancommerce.interviewtests.graph.service;

import com.wundermancommerce.interviewtests.graph.model.Person;
import com.wundermancommerce.interviewtests.graph.model.Relation;
import com.wundermancommerce.interviewtests.graph.model.RelationType;
import com.wundermancommerce.interviewtests.graph.repository.PersonRepository;
import com.wundermancommerce.interviewtests.graph.repository.RelationShipRepository;
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
public class RelationShipServiceTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private RelationShipRepository relationShipRepository;

    private RelationShipService relationShipService;
    private PersonService personService;

    @Before
    public void setUp() throws Exception {

        relationShipService = new RelationShipService(relationShipRepository);
        personService = new PersonService(personRepository);

        relationShipService.setPersonService(personService);

        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource("people.csv").getFile());

        List<Person> allPersons = personService.readPersons(file);

        when(personRepository.getAllPersons()).thenReturn(new HashSet<>(allPersons));

        File relationshipFile = new File(classLoader.getResource("relationships.csv").getFile());

        List<Relation> allRelations = relationShipService.readRelationShips(relationshipFile);

        when(relationShipRepository.getAllRelations()).thenReturn(new HashSet<>(allRelations));
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
    public void getAllRelations() {

        Assert.assertEquals(relationShipService.getAllRelations().size(), 16);

    }


    /**
     * Exercise 3 - Validate correct relationships loaded
     * Write a test to validate that the following people have the correct expected number of connections to other people
     *
     * Bob (4 relationships)
     */
    @Test
    public void testBobHas4Relations() {
        Assert.assertEquals(4, relationShipService.findRelationsOf("Bob", null).size());
    }

    /**
     * Jenny (3 relationships)
     */
    @Test
    public void testJennyHas3Relations() {
        Assert.assertEquals(3, relationShipService.findRelationsOf("Jenny", null).size());
    }

    /**
     *  Nigel (2 relationships)
     */

    @Test
    public void testNigelHas2Relations() {
        Assert.assertEquals(2, relationShipService.findRelationsOf("Nigel", null).size());
    }

    /**
     *  Alan (0 relationships)
     */
    @Test
    public void testAlenHas0Relations() {
        Assert.assertEquals(0, relationShipService.findRelationsOf("Alan", null).size());
    }

    /**
     * Exercise 4 - Write a method that calculates the size of the extended family
     * Write a method which, when passed the object representing a particular person, returns an int representing the size of their extended family including themselves. Their extended family includes anyone connected to them by a chain of family relationships of any length, so your solution will need to work for arbitrarily deep extended families. It should not count their friends. Write tests that validate this returns the correct result for the families of:
     *
     * Bob (4 family members)
     *
     */
    @Test
    public void findBobFamilySize() {
        Assert.assertEquals(4, relationShipService.findFamilyFriend("Bob", RelationType.FAMILY).size());
    }

    /**
     * Jenny (4 family members)
     */
    @Test
    public void findJennyFamilySize() {
        Assert.assertEquals(4, relationShipService.findFamilyFriend("Jenny", RelationType.FAMILY).size());
    }

    /**
     * Pete (4 family members)
     */
    @Test
    public void findPeteFamilySize() {
        Assert.assertEquals(4, relationShipService.findFamilyFriend("Pete", RelationType.FAMILY).size());
    }

    /**
     * Joe (2 family members)
     */
    @Test
    public void findJoeFamilySize() {
        Assert.assertEquals(2, relationShipService.findFamilyFriend("Joe", RelationType.FAMILY).size());
    }

    /**
     * Test friend cicle
     * Joe (4 Friends)
     */
    @Test
    public void findJoeFriendsSize() {
        Assert.assertEquals(4, relationShipService.findFamilyFriend("Bob", RelationType.FRIEND).size());
    }

}