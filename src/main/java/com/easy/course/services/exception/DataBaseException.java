package com.easy.course.services.exception;

public class DataBaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	/*
	 * dentro do construtor da classe recebendo uma msg, chamamos o construtor da super classe passando a mensagem
	 */
	public DataBaseException(String msg) {
		super(msg);
	}

}
