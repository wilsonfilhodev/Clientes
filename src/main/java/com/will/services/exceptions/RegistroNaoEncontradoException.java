package com.will.services.exceptions;

public class RegistroNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RegistroNaoEncontradoException(String mensagem){
		super(mensagem);
	}
	
	public RegistroNaoEncontradoException(String mensagem, Throwable causa){
		super(mensagem, causa);
	}
}
