package com.easy.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easy.course.entyties.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
