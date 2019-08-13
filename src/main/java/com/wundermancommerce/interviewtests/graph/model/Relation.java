package com.wundermancommerce.interviewtests.graph.model;

import java.util.Objects;

public class Relation {

    private Person person;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relation relation = (Relation) o;
        return person.equals(relation.person) &&
                relatedTo.equals(relation.relatedTo) &&
                relationType == relation.relationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, relatedTo, relationType);
    }

    public Relation(Person person, Person relatedTo, RelationType relationType) {
        this.person = person;
        this.relatedTo = relatedTo;
        this.relationType = relationType;
    }

    private Person relatedTo;

    private RelationType  relationType;

    public Person getPerson() {
        return person;
    }

    public Person getRelatedTo() {
        return relatedTo;
    }

    public RelationType getRelationType() {
        return relationType;
    }

}
