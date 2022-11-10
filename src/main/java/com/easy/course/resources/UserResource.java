package com.easy.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.easy.course.entyties.User;
import com.easy.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	/*
	 * injeta dependencia automatica do spring
	 */
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	/*
	 * informando q a requisição vai levar um "ID", com anotation pathVariable
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User u = service.findById(id);
		return ResponseEntity.ok().body(u);
	}
}
