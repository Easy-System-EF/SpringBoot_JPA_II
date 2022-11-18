package com.easy.course.entyties;

import com.easy.course.entyties.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem {

	@EmbeddedId
	/*
	 * classe auxiliar não pode ser nula, mas pode estar vazia
	 */
	private OrderItemPK id = new OrderItemPK();
	private Integer quantity;
	private Double price;
	
	public OrderItem() {
	}

	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	/*
	 * jsonIgnor aq, pq é onde traz os pedidos para alimentar (orderItemPK) o looping do mapeamento lá do pedido(order)
	 * oneToMany
	 * alinhamento dos itens ao pedido
	 */
	@JsonIgnore
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}
	
	public Product getProduct() {
		return id.getProduct();
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
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
