package com.pet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.pet.domain.Category;
import com.pet.domain.Pet;
import com.pet.domain.Tag;
import com.pet.repository.CategoryRepository;
import com.pet.repository.PetRepository;
import com.pet.repository.TagRepository;
import com.pet.service.CounterService;

//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
@SpringBootApplication
public class MyPetStoreApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MyPetStoreApplication.class, args);
		
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		
		for(String name:beanNames){
			System.out.println("Pet Store Bean: " + name );
		}
		
		System.out.println("Checking runner bean " + ctx.getBean("runner"));
	}
	
	@Bean
	CommandLineRunner runner(PetRepository petRepository, CategoryRepository categoryRepository,TagRepository tagRepository, CounterService counterService){
		return args -> {
			
			petRepository.deleteAll();
			categoryRepository.deleteAll();
			tagRepository.deleteAll();
			
			List<Category> listCategory = new ArrayList<Category>();
			Category canineCategory = new Category();
			canineCategory.setId(counterService.getNextSequence("category"));
			canineCategory.setCategoryName("Canine");
			listCategory.add(canineCategory);
			
			Category repltileCategory = new Category();
			repltileCategory.setId(counterService.getNextSequence("category"));
			repltileCategory.setCategoryName("Reptiles");
			listCategory.add(repltileCategory);		

			Category waterCategory = new Category();
			waterCategory.setId(counterService.getNextSequence("category"));
			waterCategory.setCategoryName("Fish");
			listCategory.add(waterCategory);		
			
			categoryRepository.save(canineCategory);
			categoryRepository.save(repltileCategory);
			categoryRepository.save(waterCategory);
			
			List<Tag> listTag = new ArrayList<Tag>();
			Tag cityTag = new Tag();
			cityTag.setId(counterService.getNextSequence("tag"));
			cityTag.setTagName("Toronto");
			listTag.add(cityTag);	
			
			Tag stateTag = new Tag();
			stateTag.setId(counterService.getNextSequence("tag"));			
			stateTag.setTagName("Ontario");
			listTag.add(stateTag);

			tagRepository.save(cityTag);
			tagRepository.save(stateTag);	
			
			List<String> dogPhotos = new ArrayList<String>();
			dogPhotos.add("dog");
			dogPhotos.add("dog_1");
			dogPhotos.add("dog_2");
			
			Pet dog = new Pet();
			dog.setId(counterService.getNextSequence("pet"));	
			dog.setPetName("Buster");
			dog.setPhotoUrl(dogPhotos);
			dog.setStatus("Sold");
			dog.setPrice(300);
			dog.setCategories(listCategory);
			dog.setTags(listTag);

			List<String> catPhotos = new ArrayList<String>();
			catPhotos.add("cat");
			catPhotos.add("cat_1");
			catPhotos.add("cat_2");
			catPhotos.add("cat_3");
			
			Pet cat = new Pet();
			cat.setId(counterService.getNextSequence("pet"));	
			cat.setPetName("Purrfect");
			cat.setPhotoUrl(catPhotos);
			cat.setStatus("Available");
			cat.setPrice(800);
			cat.setCategories(listCategory);
			cat.setTags(listTag);

			List<String> lionPhotos = new ArrayList<String>();
			lionPhotos.add("lion");
			lionPhotos.add("lion_1");
			lionPhotos.add("lion_2");
			lionPhotos.add("lion_3");
			
			Pet lion = new Pet();
			lion.setId(counterService.getNextSequence("pet"));	
			lion.setPetName("King!");
			lion.setPhotoUrl(lionPhotos);
			lion.setStatus("Pending");
			lion.setPrice(25);
			lion.setCategories(listCategory);
			lion.setTags(listTag);
			
			petRepository.save(dog);
			petRepository.save(cat);
			petRepository.save(lion);
		};
	}
}
