package com.pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.domain.Category;
import com.pet.repository.CategoryRepository;

@Service
public class CategoryService {
 
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category read(Category category){
		return category;
	}	
	
	public Iterable<Category> readAll(){
		return categoryRepository.findAll();
	}
}
