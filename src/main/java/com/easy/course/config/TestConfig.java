package com.easy.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.easy.course.entyties.User;
import com.easy.course.repositories.UserRepository;

/*
 *  test = anotação p/ especificar profile = (identificação do perfil) aplication.properties
 * commandLineRunner = faz a execução do processo quando iniciada a aplicação  
 */
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	/*
	 * anotação para injeção de dependencia automatica
	 */
	@Autowired
	private UserRepository userRepository;
	
/*
 * inclui valor do Id pq não aceitou null 	
 */
	
	@Override
	public void run(String... arqgs) {
		User u1 = new User(null, "Maria Brown", "988888888", "maria@gmail.com", "123456");
		User u2 = new User(null, "Alex Green", "977777777", "alex@gmail.com", "123456");
		userRepository.saveAll(Arrays.asList(u1, u2));
	}
}
