package com.easy.course.services.exception;

/*
 * sub classe do runTimeException (exceção que não é obrigado a tratar)
 */
public class ResourceNotFoundException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		
		/*
		 * construtor da super classe (objeto e id não encontrado) com mensagem e id
		 */
		public ResourceNotFoundException(Object id) {
			super("Resource not found - id " + id);
		}
	
}
