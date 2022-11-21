package com.easy.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.course.entyties.User;
import com.easy.course.repositories.UserRepository;

/*
 * informando que serviço é um registro componente do spring no mecanismo de gestão de dependencia 
 */
@Service
public class UserService {

	/*
	 * injeção automática do spring
	 */
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();
		/*
		 * operação get retorna  o objeto optional
		 */
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
}
