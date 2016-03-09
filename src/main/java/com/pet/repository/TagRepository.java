package com.pet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pet.domain.Tag;

@Repository
public interface TagRepository extends MongoRepository<Tag, Integer> {

}
