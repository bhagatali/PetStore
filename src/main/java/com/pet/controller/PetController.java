package com.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pet.domain.Pet;
import com.pet.service.PetService;

@RestController
@RequestMapping("/pet")
public class PetController {

	private PetService petService;
	
	@Autowired
	public PetController(PetService petService){
		this.petService=petService;
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public Iterable<Pet> readAll(){
		return petService.readAll();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Pet read(@PathVariable(value="id") Integer id){
		return petService.read(id);
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public Pet create(@RequestBody Pet pet){
		return petService.create(pet);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable(value="id") Integer id){
		petService.delete(id);
	}
}
