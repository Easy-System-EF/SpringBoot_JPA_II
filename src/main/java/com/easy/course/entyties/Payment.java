package com.easy.course.entyties;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/*
 *Entity informa nossa entidade
 * table = renomeando tabela para evitar conflito (nome protegido do banco de dados
 */
@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * Id = indicador da chave do objeto
	 * GeneratedValue = para incrementar chave Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant moment;

	/*
	 * um para um e mapId = transferencia de id do independente(order) para dependente(payment)
	 * JsonIgnore para alinhar o pagamento ao pedido
	 */
	@OneToOne
	@MapsId
	@JsonIgnore
	private Order orders;
	
	public Payment() {	
	}

	public Payment(Long id, Instant moment, Order orders) {
		super();
		this.id = id;
		this.moment = moment;
		this.orders = orders;
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

	public Order getOrders() {
		return orders;
	}

	public void setOrders(Order orders) {
		this.orders = orders;
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
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
