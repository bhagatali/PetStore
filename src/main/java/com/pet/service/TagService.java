package com.pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.domain.Tag;
import com.pet.repository.TagRepository;

@Service
public class TagService {
	 
	@Autowired
	private TagRepository tagRepository;
	
	public Tag read(Tag tag){
		return tag;
	}	
	
	public Iterable<Tag> readAll(){
		return tagRepository.findAll();
	}
}
