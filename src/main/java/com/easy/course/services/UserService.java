package com.easy.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.easy.course.entyties.User;
import com.easy.course.repositories.UserRepository;
import com.easy.course.services.exception.DataBaseException;
import com.easy.course.services.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
		/*
		 * se o obj não existir, retrna a exceção tratada
		 */
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
//		return obj.get();
		/*
		 * operação get retorna  o objeto optional, se não existir erro 500
		 */
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		/*
		 * estamos lançando exceções da minha camada de serviços	
		 */
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage()); 
		}
	}
	
	/*
	 * referenceById -> traz o obj monitorado sem trazer para atualizações e dps salva
	 */
	public User update(Long id, User obj) {
		try {
			User entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
