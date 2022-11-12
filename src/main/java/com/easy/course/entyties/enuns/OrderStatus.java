package com.easy.course.entyties.enuns;

public enum OrderStatus {

	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	private int code;
	
	/*
	 * construtor especial para o tipo enumerado 
	 * 
	 */
	private OrderStatus(int code) {
	this.code = code;
	}
	
	/*
	 * metodo para acesso ao code
	 */
	public int getCode( ) {
		return code;
	}
	
	/*
	 * metodo statico  q recebe o codigo e verifica se existe no orderStatus
	 */
	public static OrderStatus valueOf(int code) {
		for (OrderStatus value : OrderStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrdemStatus code");
	}
}
