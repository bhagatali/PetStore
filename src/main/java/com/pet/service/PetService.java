package com.pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.domain.Category;
import com.pet.domain.Pet;
import com.pet.repository.CategoryRepository;
import com.pet.repository.PetRepository;
import com.pet.repository.TagRepository;

@Service
@SuppressWarnings("unused")
public class PetService {

	private PetRepository petRepository;
	private CategoryRepository categoryRepository;
	private TagRepository tagRepository;
	private CounterService counterService;
	
	//Controller Based Injection
	@Autowired
	public PetService(PetRepository petRepository, 
			          CategoryRepository categoryRepository,
			          TagRepository tagRepository,
			          CounterService counterService){
		this.petRepository = petRepository;
		this.categoryRepository = categoryRepository;
		this.tagRepository = tagRepository;
		this.counterService = counterService;
	}
	
	public Pet create(Pet pet){
		System.out.println("Value of pet Id Before: " + pet.getId());
		System.out.println("Value for pet Id: " + counterService.getNextSequence("pet"));
		pet.setId(counterService.getNextSequence("pet"));
		System.out.println("Value of pet Id After: " + pet.getId());
		return petRepository.save(pet);
	}
	
	public Pet read(Pet pet){
		return pet;
	}
	
	public Iterable<Pet> readAll(){
		return petRepository.findAll();
	}
	
	public Pet update(Pet pet){
		Pet existingPet = petRepository.findBypetName(pet.getPetName());
		
		if (existingPet == null){
			return null;
		}
		
		existingPet.setPetName(pet.getPetName());
		existingPet.setStatus(pet.getStatus());
		existingPet.setPrice(pet.getPrice());
		
		return petRepository.save(existingPet);		
	}
	
	public Pet read(Integer id){
		return petRepository.findOne(id);
	}
	
	public void delete(Integer id){
		/*
		Pet existingPet = petRepository.findOne(id);
		
		for (Category category : existingPet.getCategories()){
			categoryRepository.delete(category);
		}
		*/
			
		petRepository.delete(id);
	}
}
