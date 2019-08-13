package com.wundermancommerce.interviewtests.graph.model;

import java.util.Objects;

public class Person {

    public Person(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                name.equals(person.name) &&
                email.equals(person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, age);
    }

    String name;
    String email;
    int age;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

}
