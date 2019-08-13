package com.wundermancommerce.interviewtests.graph.service;

import com.wundermancommerce.interviewtests.graph.model.Person;
import com.wundermancommerce.interviewtests.graph.model.Relation;
import com.wundermancommerce.interviewtests.graph.model.RelationType;
import com.wundermancommerce.interviewtests.graph.repository.RelationShipRepository;
import com.wundermancommerce.interviewtests.graph.utils.DataReadingUtil;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class RelationShipService {

    private PersonService personService;

    private RelationShipRepository relationShipRepository;

    public RelationShipService(RelationShipRepository relationShipRepository) {
        this.setRelationShipRepository(relationShipRepository);
    }

    public void addRelation(Relation relation) {
        getRelationShipRepository().addRelation(relation);
    }

    public Set<Relation> getAllRelations() {
        return getRelationShipRepository().getAllRelations();
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public RelationShipRepository getRelationShipRepository() {
        return relationShipRepository;
    }

    public void setRelationShipRepository(RelationShipRepository relationShipRepository) {
        this.relationShipRepository = relationShipRepository;
    }

    public void saveRelations(File file) {

        readRelationShips(file).forEach(this::addRelation);
    }

    public List<Relation> readRelationShips(File file) {

        List<Relation> relations = new ArrayList<>();

        Iterable<CSVRecord> records = DataReadingUtil.readData(file);

        for (CSVRecord record : records) {

            if (record.size() > 1) {
                String personEmail = record.get(0);
                String rel = record.get(1);
                String relationTopersonEmail = record.get(2);

                Person person = personService.findByEmail(personEmail);
                Person relatedToPerson = personService.findByEmail(relationTopersonEmail);

                Relation relation = new Relation(person, relatedToPerson, rel.equalsIgnoreCase("family") ? RelationType.FAMILY : RelationType.FRIEND);
                relations.add(relation);
            }
        }
        return relations;
    }

    public List<Relation> findRelationsOf(String personName, RelationType relationType) {
        if (relationType == null) {
            return getAllRelations().stream().filter(r -> personName.equals(r.getPerson().getName()) || personName.equals(r.getRelatedTo().getName())).collect(Collectors.toList());
        }

        return getAllRelations().stream().filter(r -> relationType.equals(r.getRelationType()) && (personName.equals(r.getPerson().getName()) || personName.equals(r.getRelatedTo().getName()))).collect(Collectors.toList());
    }

    private Set<String> findCommnitySize(String personName, RelationType relationType, Set<String> alreadySearched) {


        List<Relation> found = findRelationsOf(personName, relationType);

        if(!found.isEmpty()) {

            alreadySearched.add(personName);

            for (Relation relation : found) {

                if (!alreadySearched.contains(relation.getRelatedTo().getName())) {
                    findCommnitySize(relation.getRelatedTo().getName(), relationType, alreadySearched);
                }

                if (!alreadySearched.contains(relation.getPerson().getName())) {
                    findCommnitySize(relation.getPerson().getName(), relationType, alreadySearched);
                }
            }
        }

        return alreadySearched;
    }

    public Set<String> findFamilyFriend(String name, RelationType relationType) {
        Set<String> community = findCommnitySize(name, relationType, new HashSet<>());
        return community;
    }

}
