package com.easy.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.course.entyties.Category;
import com.easy.course.repositories.CategoryRepository;

/*
 * informando que serviço é um registro componente do spring no mecanismo de gestão de dependencia 
 */
	@Service
	public class CategoryService {

		/*
		 * injeção automática do spring
		 */
		@Autowired
		private CategoryRepository repository;
		
		public List<Category> findAll() {
			return repository.findAll();
		}
		
		public Category findById(Long id) {
			Optional<Category> obj = repository.findById(id);
			return obj.get();
			/*
			 * operação get retorna  o objeto optional
			 */
		}
	}
