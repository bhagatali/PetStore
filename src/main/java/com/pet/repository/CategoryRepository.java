package com.pet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pet.domain.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, Integer> {

}
