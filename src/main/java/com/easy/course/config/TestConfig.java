package com.easy.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.easy.course.entyties.Category;
import com.easy.course.entyties.Order;
import com.easy.course.entyties.Product;
import com.easy.course.entyties.User;
import com.easy.course.entyties.enuns.OrderStatus;
import com.easy.course.repositories.CategoryRepository;
import com.easy.course.repositories.OrderRepository;
import com.easy.course.repositories.ProductRepository;
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

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
/*
 * inclui valor do Id pq não aceitou null 	
 */
	
	@Override
	public void run(String... arqgs) {
		Category cat1 = new Category(null, "Electronics"); 
		Category cat2 = new Category(null, "Books"); 
		Category cat3 = new Category(null, "Computers");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, ""); 
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, ""); 
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, ""); 
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		User u1 = new User(null, "Maria Brown", "988888888", "maria@gmail.com", "123456");
		User u2 = new User(null, "Alex Green", "977777777", "alex@gmail.com", "123456");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1); 
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2); 
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
	}
}
