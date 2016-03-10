package com.pet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.domain.Category;
import com.pet.domain.Pet;
import com.pet.domain.Tag;
import com.pet.repository.CategoryRepository;
import com.pet.repository.PetRepository;
import com.pet.repository.TagRepository;

@Service
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
		List<Category> userListCategory = pet.getCategories();
		List<Category> saveListCategory = new ArrayList<Category>();
		
		if(userListCategory!=null){
			for(Category category:userListCategory){
				Category petCat = new Category();
				petCat.setId(counterService.getNextSequence("category"));
				petCat.setCategoryName(category.getCategoryName());
				categoryRepository.save(petCat);
				saveListCategory.add(petCat);
			}
			pet.setCategories(saveListCategory);
		}
		
		List<Tag> userListTags = pet.getTags();
		List<Tag> saveListTags = new ArrayList<Tag>();
		
		if(userListTags!=null){
			for(Tag tag:userListTags){
				Tag petTag = new Tag();
				petTag.setId(counterService.getNextSequence("tag"));
				petTag.setTagName(tag.getTagName());
				tagRepository.save(petTag);
				saveListTags.add(petTag);
			}
			pet.setTags(saveListTags);
		}		
		
		pet.setId(counterService.getNextSequence("pet"));
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
