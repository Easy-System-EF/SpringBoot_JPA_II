package com.easy.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easy.course.entyties.Order;
import com.easy.course.services.OrderService;


	/*
	 * caminho do recurso = orders
	 */
	@RestController
	@RequestMapping(value = "/orders")
	public class OrderResource {

		/*
		 * injeta dependencia automatica do spring
		 */
		@Autowired
		private OrderService service;
		
		@GetMapping
		public ResponseEntity<List<Order>> findAll() {
			List<Order> list = service.findAll();
			return ResponseEntity.ok().body(list);
		}
		
		/*
		 * informando q a requisição vai levar um "ID", com anotation pathVariable
		 */
		@GetMapping(value = "/{id}")
		public ResponseEntity<Order> findById(@PathVariable Long id) {
			Order u = service.findById(id);
			return ResponseEntity.ok().body(u);
		}
	}
