package com.easy.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easy.course.entyties.Category;
import com.easy.course.services.CategoryService;

	/*
	 * caminho do recurso = users
	 */
	@RestController
	@RequestMapping(value = "/categories")
	public class CategoryResource {

		/*
		 * injeta dependencia automatica do spring
		 */
		@Autowired
		private CategoryService service;
			
		@GetMapping
		public ResponseEntity<List<Category>> findAll() {
			List<Category> list = service.findAll();
			return ResponseEntity.ok().body(list);
		}
		
		/*
		 * informando q a requisição vai levar um "ID", com anotation pathVariable
		 */
		@GetMapping(value = "/{id}")
		public ResponseEntity<Category> findById(@PathVariable Long id) {
			Category c = service.findById(id);
			return ResponseEntity.ok().body(c);
		}
}
