package com.wundermancommerce.interviewtests.graph.service;

import com.wundermancommerce.interviewtests.graph.model.Person;
import com.wundermancommerce.interviewtests.graph.repository.PersonRepository;
import com.wundermancommerce.interviewtests.graph.utils.DataReadingUtil;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PersonService {


    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void addPerson(Person person)
    {
        personRepository.addPerson(person);
    }

    public Set<Person> getAllPersons()
    {
        return personRepository.getAllPersons();
    }

    public Person findPerson(String name)
    {
        return getAllPersons().stream().filter(p-> name.equals(p.getName())).findAny().get();
    }

    public Person findByEmail(String email){

        return getAllPersons().stream().filter(p-> email.equals(p.getEmail())).findAny().get();
    }

    public void savePersons(File file)
    {
        readPersons(file).forEach(this::addPerson);
    }

    public List<Person> readPersons(File file) {

        List<Person> persons = new ArrayList<>();

        Iterable<CSVRecord> records = DataReadingUtil.readData(file);

        for (CSVRecord record : records) {

            String name = record.get(0);
            String email = record.get(1);
            int age = Integer.valueOf(record.get(2));

            Person person = new Person(name, email, age);
            persons.add(person);
        }
        return persons;
    }

}
