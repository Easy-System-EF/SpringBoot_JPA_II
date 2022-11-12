package com.easy.course.entyties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
/*
 * renomeando tabela para evitar conflito (nome protegido do banco de dados
 */
@Table(name = "tb_user")
public class User implements Serializable  {
	private static final long serialVersionUID = 1L;

	/*
	 * Id = indicador da chave do objeto
	 * GeneratedValue = para incrementar chave Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String phone;
	private String email;
	private String password;
	
	/*
	 * Jsonignore -> impede looping usuário chama pedido que chama usuário..., ou seja 
	 * vai mostar o pedido com seu respectivo cliente - lazy loading
	 * onetomany = associação um para muitos
	 * o jpa nã carrega os objetos para muitos, ou seja, no cliente não vai mostrar pedidos
	 * mappedBy = nome do objeto associado
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>();
	
	public User() {	
	}

	public User(Long id, String name, String phone, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
