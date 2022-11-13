package com.easy.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easy.course.entyties.Category;

/*
 * Não precisa do registro de componente pq herda do JPARepository q ja esta registrada como componente
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
