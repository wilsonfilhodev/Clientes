package com.will.services.exceptions;

public class ClienteNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ClienteNaoEncontradoException(String mensagem){
		super(mensagem);
	}
	
	public ClienteNaoEncontradoException(String mensagem, Throwable causa){
		super(mensagem, causa);
	}
}
