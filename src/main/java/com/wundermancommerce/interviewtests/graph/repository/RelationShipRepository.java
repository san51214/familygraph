package com.wundermancommerce.interviewtests.graph.repository;

import com.wundermancommerce.interviewtests.graph.model.Relation;

import java.util.Set;

public interface RelationShipRepository {

    void addRelation(Relation relation);

    Set<Relation> getAllRelations();
}
