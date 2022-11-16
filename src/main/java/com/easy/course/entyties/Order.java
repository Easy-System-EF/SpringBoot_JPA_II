package com.easy.course.entyties;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.easy.course.entyties.enuns.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*
 *Entity informa nossa entidade
 * table = renomeando tabela para evitar conflito (nome protegido do banco de dados
 */
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * Id = indicador da chave do objeto
	 * GeneratedValue = para incrementar chave Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*
	 * anotaçã para formatar data iso8601
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	private Integer orderStatus;

	/*
	 * relação muitos para um
	 * joinColumn = nome da chave estrangeira
	 */
	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;
	
	@OneToMany
	@JoinColumn(name = "order_id")
	/*
	 * JsonIgnore no get do OrderItem
	 */
	private Set<OrderItem> itens = new HashSet<>();
	
	public Order( ) {
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		/*
		 * ele agora recebe um inteiro, entao vamos transformá-lo, para passar o orderStatus(literal)
		 */
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
		/*
		 * convertendo o inteiro para orderStatus (literal) via metodo valueOf
		 */
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
		/*
		 * agora q o orderStatus e inteiro, vamos buscar seu valor com getCode
		 * vamos substituir no construtor 
		 */
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public Set<OrderItem> getItens() {
		return itens;
	}

	public void setItens(Set<OrderItem> itens) {
		this.itens = itens;
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
