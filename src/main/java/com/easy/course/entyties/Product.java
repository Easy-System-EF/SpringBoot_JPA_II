package com.easy.course.entyties;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;

	/*
	 * jpa ignora coleção (sem mapeamento)
	 *
	 *@Transient
	 *
	 * agora de paradigma orientado a objetos para modelo relacional = tabela de associação
	 * mapeamento para transformar as associações em tabela do modelo relacional
	 * 
	 * muitos para muitos - JoinColumns -> pq produto é o proprietario e mostra key de acesso da referencia
	 * joinTable = nome da tabela de associações nova 
	 * joinColumn = chave estrangeira(na tabela nova = referencia)
	 * inverseJoinColumn  = chave etrangeira da coleção associada 
	 */
	@ManyToMany
	@JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_Id"), inverseJoinColumns = @JoinColumn(name = "category_Id"))
	private Set<Category> categories = new HashSet<>();

	/*
	 * proprietario acessando a key d referencia
	 * temos q fazer o get - vida getOrders
	 */
	@OneToMany(mappedBy = "id.product")
	private Set<OrderItem> itens = new HashSet<>();
	
	public Product() {
	}

	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategories() {
		return categories;
	}
	
	/*
	 * metodo q varre os itens de pedido(associado) e associa pedidos ao produto
	 * que esta mapeado com a key de produtos para trazer id.Product
	 * no pedido ja tem a relação com o itens do pedido, com pedido e produto
	 * 
	 * o JsonIgnore aq evita o looping e alinha os produtos na requisição dos pedidos
	 */
	@JsonIgnore
	public Set<Order> getOrders() {
		Set<Order> set = new HashSet<>();
		for (OrderItem oi : itens) {
			set.add(oi.getOrder());
		}			
		return set;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
