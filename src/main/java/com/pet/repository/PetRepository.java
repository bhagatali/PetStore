package com.pet.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.pet.domain.Pet;

public interface PetRepository extends MongoRepository<Pet, Integer> {

	Pet findBypetName(String petname);
	Pet findOne(Integer id);
}
